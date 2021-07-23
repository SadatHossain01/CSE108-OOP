players = open('../fcm/players.txt', encoding='utf-8')
positions = set()
for line in players:
    try:
        whole = line.strip().split(';')
        all_positions = whole[3].split(' ')
        for any in all_positions:
            positions.add(any)
    except:
        continue
players.close()
forward = list()
defender = list()
midfielder = list()
print(len(positions))
for position in positions:
    print(position, end=' ')
    if position in ['CF', 'ST'] or position.endswith('W'):
        forward.append(position)
    elif position.endswith('M'):
        midfielder.append(position)
    else:
        defender.append(position)
print()
print(forward)
print(midfielder)
print(defender)

