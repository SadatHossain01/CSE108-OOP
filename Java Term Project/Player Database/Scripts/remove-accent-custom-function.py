acceptable = ['/', '?', '=', ';', '(', ')', ' ', ':', '.', '\'', '-', '€', '&']
for cur in range(65, 91):
    acceptable.append(chr(cur))
    acceptable.append(chr(cur).lower())
for cur in range(0, 10):
    acceptable.append(str(cur))
mapping = dict()
for any in acceptable:
    mapping[any] = any
l1 = ['·', 'Í', 'ð', 'ê', 'á', 'å', 'ü', 'Ž', 'Ó', 'ó', 'þ', 'õ', 'ç', 'Â', 'ã', 'ý', 'Þ', 'ï', 'É', 'î', 'é', 'Ü', 'Á',
      'Å', 'ö', 'ø', 'Æ', 'Ð', 'í', 'ú', 'Ö', 'ë', 'ž', 'è', 'Ø', 'š', 'ß', 'Ç', 'ò', 'Š', 'â', 'Ñ', 'æ', 'à', 'ô', 'ä', 'ñ']
l2 = ['.', 'I', 'o', 'e', 'a', 'a', 'u', 'Z', 'O', 'o', 'p', 'o', 'c', 'A', 'a', 'y', 'p', 'i', 'E', 'i', 'e', 'U', 'A',
      'A', 'o', 'o', 'A', 'D', 'i', 'u', 'O', 'e', 'z', 'e', 'O', 's', 'B', 'C', 'o', 'S', 'a', 'N', 'a', 'a', 'o', 'a', 'n']
for idx in range(0, len(l1)):
    mapping[l1[idx]] = l2[idx]


def decodeName(name):
    ans = ''
    for char in name:
        ans += mapping[char]
    return ans
