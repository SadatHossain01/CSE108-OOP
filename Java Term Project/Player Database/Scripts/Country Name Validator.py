import pathlib
directory = 'E:\\1-2\\CSE108\\Offlines Repo\\CSE108-Offlines\\Java Term Project\\Part 4 (Database based)\\src\\Assets\\Image\\Flags'

available_flags = list()

for path in pathlib.Path(directory).iterdir():
    if path.is_file():
        old_name = path.stem
        available_flags.append(old_name)

print(available_flags)
print(len(available_flags))

with open('E:\\1-2\\CSE108\\Offlines Repo\\CSE108-Offlines\\Java Term Project\\Player Database\\Country List.txt', 'r') as f:
    for line in f:
        wanted_country = line.strip()
        if wanted_country not in available_flags:
            print(wanted_country)

