acceptable = ['/', '?', '=', ';', '(', ')', ' ', ':', '.', '\'', '-', '€', '&']
for cur in range(65, 91):
    acceptable.append(chr(cur))
    acceptable.append(chr(cur).lower())
for cur in range(0, 10):
    acceptable.append(chr(cur))
mapping = dict()
for any in acceptable:
    mapping[any] = any
l1 = ['ó', 'ü', 'á', 'ê', 'è', '·', 'ș', 'ş', 'Ś', 'ę', 'ö', 'Ö', 'å', 'ø', 'æ', 'ą', 'ú', 'ı', 'ň', 'â', 'ğ', 'ä', 'é',
      'ł', 'ã', 'ç', 'Ç', 'í', 'ñ', 'ń', 'î', 'É']
l2 = ['o', 'u', 'a', 'e', 'e', '.', 's', 's', 'S', 'e', 'o', 'O', 'a', 'o', 'a', 'a', 'u', 'i', 'n', 'a', 'g', 'a', 'e',
      'l', 'a', 'c', 'C', 'i', 'n', 'n', 'i', 'E']
for idx in range(0, len(l1)):
    mapping[l1[idx]] = l2[idx]


def decodeName(name):
    ans = ''
    for char in name:
        ans += mapping[char]
    return ans
