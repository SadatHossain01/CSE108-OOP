sports_salaries = open('../sports-salaries-data.txt', 'r', encoding='utf-8')
count = 1


class Player:
    def __init__(self, name, salary):
        self.name = name
        self.salary = salary


players = []

for line in sports_salaries:
    all = line.strip().split(';')
    weekly_salary = all[1][1:]
    weekly_salary = int(weekly_salary.replace(',', ''))
    if weekly_salary >= 200000:
        count += 1
        players.append(Player(all[0], weekly_salary))
        # print(all[0] + '|' + str(weekly_salary))
sports_salaries.close()
players = sorted(players, key=lambda c: c.salary, reverse=True)
print('Total Players: ' + str(count))
for p in players:
    print(p.name + '|' + str(p.salary))
