clubs = open('../fcm/clubs.txt', encoding='utf-8')
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
for line in clubs:
    what = line.strip()
    for char in what:
        if char not in acceptable and char not in unknown:
            unknown.add(char)
            # print(what + '     ' + char)
clubs.close()
mapping = dict()
for any in acceptable:
    mapping[any] = any
print(unknown)
print(acceptable)
# 'æ', 'ı', 'Ś', 'Ç', 'ã', 'Ö', 'ş', 'ø', 'å', '·', 'é', 'ğ', 'í', 'î', 'ä', 'ň', 'ö', 'è', 'ą', 'É', 'ł', 'ü', 'ñ', 'ș', 'â', 'ń', 'ê', 'ę', 'á', 'ó', 'ú', 'ç'

l1 = ['ó', 'ü', 'á', 'ê', 'è', '·', 'ș', 'ş', 'Ś', 'ę', 'ö', 'Ö', 'å', 'ø', 'æ', 'ą', 'ú', 'ı', 'ň', 'â', 'ğ', 'ä', 'é', 'ł', 'ã', 'ç', 'Ç', 'í', 'ñ', 'ń', 'î', 'É']
l2 = ['o', 'u', 'a', 'e', 'e', '.', 's', 's', 'S', 'e', 'o', 'O', 'a', 'o', 'a', 'a', 'u', 'i', 'n', 'a', 'g', 'a', 'e', 'l', 'a', 'c', 'C', 'i', 'n', 'n', 'i', 'E']

for idx in range(0, len(l1)):
    mapping[l1[idx]] = l2[idx]
    # print(l1[idx] + ' ' + l2[idx])
print(mapping)
# for any in unknown:
#     if any not in mapping:
#         print(any, end=' ')
# print()


def decodeName(name):
    ans = ''
    for char in name:
        ans += mapping[char]
    return ans


clubs = open('../fcm/clubs.txt', encoding='utf-8')
new = open('../fcm/FixedClubDatabase.txt', 'w', encoding='utf-8')
for line in clubs:
    all = line.strip().split(';')
    name = all[1]
    decoded = decodeName(name)
    print(name + '     ' + decoded)
    all.insert(2, decoded)
    buffer = ''
    for _ in range(1, len(all)):
        buffer += all[_] + ';'
    # for string in all:
    #     buffer += string + ';'
    new.write(buffer)
    new.write('\n')
clubs.close()
new.close()
