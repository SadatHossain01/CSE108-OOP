import pycountry

clubs = open('../fcm/FixedClubDatabase.txt', encoding='utf-8')
for line in clubs:
    all = line.strip().split(';')
    my_unaccented = all[1]
    py_unaccented = pycountry.remove_accents(all[0])
    if not my_unaccented == py_unaccented:
        print('My: ' + my_unaccented + '; Python\'s: ' + py_unaccented)
clubs.close()

print()
print()
print()

players = open('../fcm/FixedPlayerDatabase.txt')
for line in players:
    all = line.strip().split(';')
    my_unaccented = all[1]
    py_unaccented = pycountry.remove_accents(all[0])
    if not my_unaccented == py_unaccented:
        print('My: ' + my_unaccented + '; Python\'s: ' + py_unaccented)
players.close()