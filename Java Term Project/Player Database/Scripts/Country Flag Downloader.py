from bs4 import BeautifulSoup
import requests

def downloadImage(url, file_path, file_name):
    response = requests.get(url)
    full_path = file_path + file_name + '.png'
    file = open(full_path, 'wb')
    file.write(response.content)
    file.close()

URL = 'https://en.soccerwiki.org/wiki.php?action=countries'
html_text = requests.get(URL).text
soup = BeautifulSoup(html_text, 'lxml')
countries = soup.findAll('a')
hasStarted = False
for var in countries:
    CountryName = var.text
    if CountryName == 'Argentina':
        hasStarted = True
    if CountryName == 'Terms of Use':
        break
    if hasStarted:
        PageLink = 'https://en.soccerwiki.org/' + var['href']
        buffer = CountryName + '|' + PageLink
        print(buffer)