from bs4 import BeautifulSoup
import requests
import threading


def downloadImage(url, file_path, file_name):
    response = requests.get(url)
    full_path = file_path + file_name + '.png'
    file = open(full_path, 'wb')
    file.write(response.content)
    file.close()

f = open('E:\\1-2\\CSE108\\Offlines Repo\\CSE108-Offlines\\Java Term Project\\Player Database\\Club Logo\\Club ImageLink.txt', 'a')

def parse_ID(low, high):
    for ID in range(low, high+1):
        try:
            URL = 'https://en.soccerwiki.org/squad.php?clubid=' + str(ID)
            html_text = requests.get(URL).text
            soup = BeautifulSoup(html_text, 'lxml')
            club_name_sample = soup.find('head').text.strip()
            idx = club_name_sample.find(' football club')
            club_name = club_name_sample[0:idx].strip()
            logo_link = 'https:' + soup.select_one('center > img')['src']
            downloadImage(logo_link, 'E:\\1-2\\CSE108\\Offlines Repo\\CSE108-Offlines\\Java Term Project\\Player Database\\Club Logo\\', club_name)
            f.write(club_name + '|' + logo_link + '\n')
        except:
            continue
        finally:
            print(ID)

end = 5700
threads = list()
for ID in range(1, end//50+1):
    t = threading.Thread(target=parse_ID, args=(ID*50 - 49, ID*50))
    t.start()
    threads.append(t)
for var in threads:
    var.join()
threads.clear()
f.close()
