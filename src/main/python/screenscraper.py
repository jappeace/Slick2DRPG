from requests import Session
from bs4 import BeautifulSoup
from shutil import copyfileobj
from os import makedirs
from errno import EEXIST

properDir = '../../../build/'
baseUrl = 'http://bulbapedia.bulbagarden.net/'
wikiUrl = baseUrl+ 'wiki/'

def tohtml(lines):
    f = open(properDir + "output.html", "w", encoding='utf8')
    f.writelines(lines)
    f.close()

session = Session()
def getImage(imgName, folder, url):

    response = session.get(url, stream=True)
    if response.status_code != 200:
        print("error, invalid status code")
        return

    path = "../resources/sprites/pokemon/"+ folder.lower()+"/"
    try:
        makedirs(path)
    except OSError as exception:
        if exception.errno != EEXIST:
            raise

    with open(
        path + imgName.lower()+".png",
        'wb'
    ) as f:
        response.raw.decode_content = True
        copyfileobj(response.raw, f)

# the stupid wikipedia system has a html page between
# the real image and it stores it semi randomly, so we
# have to scrape that
def requestImageUrl(fileName):
    return BeautifulSoup(
        session.get(
            wikiUrl+"File:"+fileName+".png"
        ).text
    ).img.get('src')

class Pokemon:
    name = ""
    id = ""

def handleIteration(i):
    nextPage = i.a.get('href')
    pokemon = Pokemon()
    pokemon.name = i.find_all('td')[2].a.text
    pokemon.id = i.find_all('td')[0].text.strip()

    getImage("small", pokemon.name, i.img.get('src'))
    getImage("front", pokemon.name, requestImageUrl("Spr_4d_"+pokemon.id))
    getImage("back", pokemon.name, requestImageUrl("Spr_b_4d_"+pokemon.id))
    getImage("shiny_front", pokemon.name, requestImageUrl("Spr_4d_"+pokemon.id+"_s"))
    getImage("shiny_back", pokemon.name, requestImageUrl("Spr_b_4d_"+pokemon.id+"_s"))
    getImage("big", pokemon.name, requestImageUrl(pokemon.id+pokemon.name))

#target = 'http://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_base_stats_(Generation_II-V)'

#listTable = BeautifulSoup( session.get(target).text.encode('utf8')).find('table').find_all('tr')
# using a file is a speedup in testing but needs to be replaced in final
# the final version because the site can change and I want those changes
# also I don't want to commit the crappy html
targetFile = open(properDir + 'target.html', 'r', encoding='utf8')
page = targetFile.read()
targetFile.close()

listTable = BeautifulSoup(page).find('table').find_all('tr')


childsIter = iter(listTable)
next(childsIter)
handleIteration(next(childsIter))

