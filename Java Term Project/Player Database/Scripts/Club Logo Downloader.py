from bs4 import BeautifulSoup
import requests


def downloadImage(url, file_path, file_name):
    response = requests.get(url)
    full_path = file_path + file_name + '.png'
    file = open(full_path, 'wb')
    file.write(response.content)
    file.close()

end = 5700
threads = list()
for ID in range(1, 2):
    URL = 'https://en.soccerwiki.org/squad.php?clubid=' + str(ID)
    try:
        html_text = requests.get(URL).text
        soup = BeautifulSoup(html_text, 'lxml')
        club_name_sample = soup.find('head').text.strip()
        idx = club_name_sample.find(' football club')
        club_name = club_name_sample[0:idx].strip()
        logo_link = 'https:' + soup.select_one('center > img')['src']
        print(club_name, logo_link)
    except:
        continue
