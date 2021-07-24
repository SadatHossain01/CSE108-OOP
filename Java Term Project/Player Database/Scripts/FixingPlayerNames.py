players = open('../fcm/finalplayerdatabase.txt')
acceptable = ['/', '?', '=', ';', '(', ')', ' ', ':', '.', '\'', '-', '€', '&']
cur = 65
while not cur == 91:
    acceptable.append(chr(cur))
    acceptable.append(chr(cur).lower())
    cur += 1
cur = 0
while not cur == 10:
    acceptable.append(str(cur))
    cur += 1
# print(acceptable)
unknown = set()
for line in players:
    what = line.strip()
    for char in what:
        if char not in acceptable and char not in unknown:
            unknown.add(char)
            # print(what + '     ' + char)
players.close()
mapping = dict()
for any in acceptable:
    mapping[any] = any

# for any in unknown:
#     print(any, end=' ')
# print()

# '·', 'Í', 'ð', 'ê', 'á', 'å', 'ü', 'Ž', 'Ó', 'ó', 'þ', 'õ', 'ç', 'Â', 'ã', 'ý', 'Þ', 'ï', 'É', 'î', 'é', 'Ü', 'Á', 'Å', 'ö', 'ø', 'Æ', 'Ð', 'í', 'ú', 'Ö', 'ë', 'ž', 'è', 'Ø', 'š', 'ß', 'Ç', 'ò', 'Š', 'â', 'Ñ', 'æ', 'à', 'ô', 'ä', 'ñ'
l1 = ['·', 'Í', 'ð', 'ê', 'á', 'å', 'ü', 'Ž', 'Ó', 'ó', 'þ', 'õ', 'ç', 'Â', 'ã', 'ý', 'Þ', 'ï', 'É', 'î', 'é', 'Ü', 'Á', 'Å', 'ö', 'ø', 'Æ', 'Ð', 'í', 'ú', 'Ö', 'ë', 'ž', 'è', 'Ø', 'š', 'ß', 'Ç', 'ò', 'Š', 'â', 'Ñ', 'æ', 'à', 'ô', 'ä', 'ñ']
l2 = ['.', 'I', 'o', 'e', 'a', 'a', 'u', 'Z', 'O', 'o', 'p', 'o', 'c', 'A', 'a', 'y', 'p', 'i', 'E', 'i', 'e', 'U', 'A', 'A', 'o', 'o', 'A', 'D', 'i', 'u', 'O', 'e', 'z', 'e', 'O', 's', 'B', 'C', 'o', 'S', 'a', 'N', 'a', 'a', 'o', 'a', 'n']

for idx in range(0, len(l1)):
    mapping[l1[idx]] = l2[idx]
    # print(l1[idx] + ' ' + l2[idx])

def decodeName(name):
    ans = ''
    for char in name:
        ans +=  mapping[char]
    return ans

players = open('../fcm/finalplayerdatabase.txt', 'r')
new = open('../fcm/FixedPlayerDatabase.txt', 'w')
for line in players:
    all = line.strip().split(';')
    name = all[0]
    decoded = decodeName(name)
    all.insert(1, decoded)
    buffer = ''
    for string in all:
        buffer += string + ';'
    buffer += '0;€0'
    new.write(buffer)
    new.write('\n')
players.close()
new.close()

