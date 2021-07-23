clubs = open('../Database/clubs.txt', encoding='utf-8')
club_map = dict()

for line in clubs:
    whole = line.strip().split(';')
    ID = whole[0]
    club_name = whole[1]
    club_map[ID] = club_name

clubs.close()

countries = open('../Database/countries.txt', encoding='utf-8')
country_map = dict()

for line in countries:
    whole = line.strip().split(';')
    ID = whole[0]
    country_name = whole[1]
    country_map[ID] = country_name

countries.close()

players = open('../Database/players.txt', encoding='utf-8')

AllPlayerDatabase = open('../Database/AllPlayerDatabase.txt', 'a')
count = 0
for line in players:
    buffer = ''
    try:
        whole = line.strip().split(';')
        name = whole[1]
        club = club_map[whole[3]]
        age = whole[5]
        DoB = whole[6]
        country = country_map[whole[4]]
        height = whole[7]
        weight = whole[8]
        number = whole[9]
        image = whole[14]
        position = whole[10]
        preferred_foot = whole[11]
        salary = whole[12]
        buffer = name + ';' + club + ';' + country + ';' + age + ';' + DoB + ';' + number + ';' + position + ';' + height + ';' + weight + ';' + preferred_foot + ';' + salary + ';' + image
        print(str(count) + ' ' + buffer)
        count += 1
        AllPlayerDatabase.write(buffer)
        AllPlayerDatabase.write('\n')
    except:
        continue

players.close()
AllPlayerDatabase.close()
