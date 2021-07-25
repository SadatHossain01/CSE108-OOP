countries = open('../Countries.txt', 'r', encoding='windows-1252')
all_countries = []
for line in countries:
    all = line.strip().split(';')
    all_countries.append(all[1])
countries.close()
all_countries.sort()
print(all_countries,end=' ')
players = open('../FixedPlayerDatabase.txt', 'r', encoding='windows-1252')
for line in players:
    country = line.strip().split(';')[3]
    if country not in all_countries:
        print(country)
players.close()