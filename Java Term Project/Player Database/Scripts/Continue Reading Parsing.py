import threading

from bs4 import BeautifulSoup
import requests


def parse(ID):
    f1 = open('../Database/Club Database.txt', 'a')
    f2 = open('../Database/League Database.txt', 'a')
    if ID == 1:
        URL = 'https://soccerprime.com/category/salary/'
    else:
        URL = 'https://soccerprime.com/category/salary/page/' + str(ID)
    html_text = requests.get(URL).text
    soup = BeautifulSoup(html_text, 'lxml')
    body = soup.findAll('a', class_='button reverse')
    for var in body:
        Link = (var['href'])
        Club_Name = var['aria-label'].split(':')[1].strip()
        Buffer = Club_Name + ';' + Link + '\n'
        Header = BeautifulSoup(requests.get(Link).text, 'lxml').select_one('th:nth-of-type(2)').text
        if Header == 'Average Annual Salary':
            f1.write(Buffer)
        else:
            f2.write(Buffer)
        print(Buffer, end='')
    f1.close()
    f2.close()


Threads = []
for ID in range(1, 22):
    t = threading.Thread(target=parse, args=(ID,))
    t.start()
    Threads.append(t)
for var in Threads:
    var.join()
