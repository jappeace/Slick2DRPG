from requests import get, codes, post, exceptions, Session
from json import loads
from bs4 import BeautifulSoup

properDir = '../../../build/'
def tohtml(lines):
    f = open(properDir + "output.html", "w", encoding='utf8')
    f.writelines(lines)
    f.close()

#target = 'http://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_base_stats_(Generation_II-V)'
#session = Session()

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
for child in childsIter:
    print(child.encode('utf8'))

