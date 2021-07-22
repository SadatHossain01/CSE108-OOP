import requests
from bs4 import BeautifulSoup


def parse_club(link):
    root = BeautifulSoup(requests.get(link).content, 'html.parser')
    print(root.text)

parse_club('https://www.fifacm.com/teams')
