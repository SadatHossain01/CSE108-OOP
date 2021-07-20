from bs4 import BeautifulSoup
import requests

URL = 'https://soccerprime.com/olympique-de-marseille-player-salaries/'
html_text = requests.get(URL).text
soup = BeautifulSoup(html_text, 'lxml')
All = soup.findAll('tr')
_ = 1
for var in All:
    body = var.text
    if '€' not in body:
        continue
    all = body.split('€')
    Name = all[0]
    Weekly = all[1]
    Annual = all[2]
    print(Name, Weekly, Annual)
