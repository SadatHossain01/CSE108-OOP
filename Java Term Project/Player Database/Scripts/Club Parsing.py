from bs4 import BeautifulSoup
import requests

ClubID = 140
URL = 'https://en.soccerwiki.org/squad.php?clubid=' + str(ClubID)
html_text = requests.get(URL).text
soup = BeautifulSoup(html_text, 'lxml')
ClubName = soup.find('h1').text.split('›')[1].strip()
LeagueName = soup.select_one('table > tr:nth-of-type(7) > td').text.strip()
print(ClubName, LeagueName)