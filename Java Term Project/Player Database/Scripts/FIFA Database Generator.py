clubs = open('../fcm/clubs.txt', encoding='utf-8')
club_map = dict()
club_map['0'] = 'Free Agent'

for line in clubs:
    whole = line.strip().split(';')
    ID = whole[0]
    club_name = whole[1]
    club_map[ID] = club_name

clubs.close()

countries = open('../fcm/countries.txt', encoding='utf-8')
country_map = dict()

for line in countries:
    whole = line.strip().split(';')
    ID = whole[0]
    country_name = whole[1]
    country_map[ID] = country_name

countries.close()

players = open('../fcm/players.txt', encoding='utf-8')

AllPlayerDatabase = open('../fcm/finalplayerdatabase.txt', 'a')
count = 0
for line in players:
    buffer = ''
    try:
        whole = line.strip().split(';')
        name = whole[1]
        club = club_map[whole[5]]
        age = whole[2]
        value = whole[11]
        country = country_map[whole[6]]
        height = whole[7]
        weight = whole[8]
        number = whole[9]
        image = whole[12]
        position = whole[3]
        preferred_foot = whole[4]
        salary = whole[10]
        buffer = name + ';' + club + ';' + country + ';' + age + ';' + value + ';' + number + ';' + position + ';' + height + ';' + weight + ';' + preferred_foot + ';' + salary + ';' + image
        print(str(count) + ' ' + buffer)
        count += 1
        AllPlayerDatabase.write(buffer)
        AllPlayerDatabase.write('\n')
    except:
        continue

players.close()
AllPlayerDatabase.close()
