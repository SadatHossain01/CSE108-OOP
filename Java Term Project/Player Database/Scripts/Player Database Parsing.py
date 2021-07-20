from bs4 import BeautifulSoup
import requests

for ID in range(1, 100000):
    Property = []
    if ID % 100 == 1:
        f1 = open('Club Database.txt', 'a')
        f2 = open('League Database.txt', 'a')
    try:
        URL = 'https://en.soccerwiki.org/player.php?pid=' + str(ID)
        html_text = requests.get(URL).text
        soup = BeautifulSoup(html_text, 'lxml')
        body = soup.findAll('td', class_='left')
        Name = body[0].text.strip()
        Club = body[1].text.strip()
        Age = body[2].text.strip()
        DoB = body[3].text.strip()
        Country = body[4].text.strip()
        Height = body[5].text.strip()
        Weight = body[6].text.strip()
        Number = body[11].text.strip()
        Image_Link = 'https:' + soup.select_one('div.cBox > img')['src']
        Position = soup.select_one('div#playerPositionTable > table > tr:nth-of-type(1) > td').text
        Preferred_Foot = soup.select_one('div#playerPositionTable > table > tr:nth-of-type(3) > td').text
        Property.extend([Name, Club, Age, DoB, Country, Height, Weight, Number, Image_Link, Position, Preferred_Foot])
        print(Property)
    except:
        if ID % 100 == 0:
            f1.close()
            f2.close()
        continue

    for _ in range(11):
        f1.write(Property[_])
        if _ < 10:
            f1.write(';')

    f1.write('\n')

    for _ in range(11):
        f2.write(Property[_])
        if _ < 10:
            f2.write(';')

    f2.write('\n')

    if ID % 100 == 0:
        f1.close()
        f2.close()

f1.close()
f2.close()
