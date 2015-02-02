from requests import Session, exceptions
from bs4 import BeautifulSoup
from shutil import copyfileobj, rmtree
import os
from errno import EEXIST
from hashlib import md5
import socket

properDir = '../../../build/'
baseUrl = 'http://bulbapedia.bulbagarden.net/'
wikiUrl = baseUrl+ 'wiki/'
timeout = 15
storePath = "../resources/sprites/pokemon/"
def tohtml(lines):
    f = open(properDir + "output.html", "w", encoding='utf8')
    f.writelines(lines)
    f.close()

session = Session()
def fileIsGood(filePath):
    with open(filePath, 'rb') as f:
        if md5(f.read()).hexdigest() == '6e78c36b63bbb23649a8b8582ae706b0':
            print("file is not good, (got a known error file)")
            return False
    return True
def fetchImage(imgName, folder, url):

    path = storePath + folder.lower()+"/"
    fileName = path + imgName.lower()+".png"
    if os.path.isfile(fileName) and fileIsGood(fileName):
        print(fileName + " already exists, skipping")
        return True

    response = session.get(url, stream=True, timeout=timeout)
    if response.status_code != 200:
        print("error, invalid status code")
        return

    try:
        os.makedirs(path)
    except OSError as exception:
        if exception.errno != EEXIST:
            raise

    with open(
        fileName,
        'wb'
    ) as f:
        response.raw.decode_content = True
        copyfileobj(response.raw, f)
    if not fileIsGood(fileName):
        return False
    return True

# the stupid wikipedia system has a html page between
# the real image and it stores it semi randomly, so we
# have to scrape that
def requestImageUrl(fileName):
    return BeautifulSoup(
        session.get(
            wikiUrl+"File:"+fileName+".png", timeout=timeout
        ).text
    ).img.get('src')

def fetchSprite(imgName, folder, url, afterurl = ""):
    if fetchImage(imgName, folder, requestImageUrl(url+afterurl)):
        return True
    print("suspecting that there is a female and male image")
    if fetchImage(imgName, folder, requestImageUrl(url+"_m" + afterurl)):
        fetchImage(imgName+"_female", folder, requestImageUrl(url+"_f" + afterurl))
        return True
    return False


def getName(i):
    return i.find_all('td')[2].a.text.replace("\u2640","").replace("\u2642","t")

def fetchSprites(generation, name, id):
    front = "Spr_{}_{}".format(generation, id)
    back = "Spr_b_{}_{}".format(generation, id)
    if not fetchSprite("front", name, front):
        print("suspecting this generation has not this pokemon, upping the gneration")
        fetchSprites("5b", name, id)
        return
    fetchSprite("back", name, back)
    fetchSprite("shiny_front", name, front,"_s")
    fetchSprite("shiny_back", name, back,"_s")

def handleIteration(i, generation = "4d"):
    nextPage = i.a.get('href')

    name =getName(i)
    id = i.find_all('td')[0].text.strip()

    try:
        print("handeling " + name)
    except UnicodeEncodeError as err:
        print(("handeling " + name).encode("utf8"))

    fetchImage("small", name, i.img.get('src'))
    fetchImage("big", name, requestImageUrl(id+name))
    fetchSprites(generation, name, id)

def resetCurrentAndRetry(i):
        # clear the working tree so the existing file check is cirumvented,
        # if a file is half loaded it will be now fully loaded
        path = storePath+getName(i).lower()
        if os.path.isdir(path):
            rmtree(path)
        handleIterationIgnoringConnectionBullshit(i)
def handleIterationIgnoringConnectionBullshit(i):
    path = storePath+getName(i).lower()
    try:
        if os.path.isdir(path):
            print("exists already: '{}'".format(path))
            for f in os.listdir(path):
                if fileIsGood("{}/{}".format(path,f)):
                    continue
                print("one of the files is worng, trying a different generation")
                rmtree(path)
                handleIteration(i,"5b")
                return
            return
        handleIteration(i)
    except exceptions.ConnectionError as connectionError:
        print("got a connection error, trying again")
        resetCurrentAndRetry(i)
    except socket.timeout as time:
        print("had a socket timeout")
        resetCurrentAndRetry(i)
    except exceptions.Timeout as time:
        print("had a requests timeout")
        resetCurrentAndRetry(i)

target = wikiUrl+'List_of_Pok%C3%A9mon_by_base_stats_(Generation_II-V)'
listTable = BeautifulSoup( session.get(
                target, timeout=timeout
            ).text.encode('utf8')).find('table').find_all('tr')

childsIter = iter(listTable)
next(childsIter) #ignore head row

for i in childsIter:
    handleIterationIgnoringConnectionBullshit(i)

