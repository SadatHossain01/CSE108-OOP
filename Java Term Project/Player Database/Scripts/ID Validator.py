class Club:
    def __init__(self, name, self_ID, league_ID, country_ID):
        self.name = name
        self.self_ID = self_ID
        self.league_ID = league_ID
        self.country_ID = country_ID


club_list = list()
clubs = open('E:\\1-2\\CSE108\\Offlines Repo\\CSE108-Offlines\\Java Term Project\\Player Database\\clubs.txt',
             encoding='utf-8')
for line in clubs:
    try:
        what = line.strip().split(';')
        club_name = what[1]
        self_ID = what[0]
        league_ID = what[4]
        club_ID = what[5]
        club_list.append(Club(club_name, self_ID, league_ID, club_ID))
    except:
        continue
clubs.close()
countries = open('E:\\1-2\\CSE108\\Offlines Repo\\CSE108-Offlines\\Java Term Project\\Player Database\\countries.txt',
                 encoding='utf-8')
country_ID_list = dict()
for line in countries:
    try:
        what = line.strip().split(';')
        ID = what[0]
        name = what[1]
        country_ID_list[ID] = name
    except:
        continue
countries.close()
leagues = open('E:\\1-2\\CSE108\\Offlines Repo\\CSE108-Offlines\\Java Term Project\\Player Database\\leagues.txt',
               encoding='utf-8')
leagues_ID_list = dict()
for line in leagues:
    try:
        what = line.strip().split(';')
        ID = what[0]
        name = what[1]
        leagues_ID_list[ID] = name
    except:
        continue
leagues.close()
count = 1
for club in club_list:
    try:
        print(club.name + '|' + country_ID_list[club.country_ID] + '|' + leagues_ID_list[club.league_ID])
        count += 1
    except:
        continue
print(count)
