clubs = open('../FixedClubDatabase.txt', 'r', encoding='windows-1252')
login = open('../LoginCredentials.txt', 'w', encoding='utf-8')
for line in clubs:
    all = line.strip().split(';')
    main = all[0]
    alt = all[1]
    password = alt.lower()
    buffer = main + ';' + alt + ';' + password
    login.write(buffer + '\n')
    print(buffer)
clubs.close()
login.close()