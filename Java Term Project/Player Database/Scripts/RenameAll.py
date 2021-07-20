import pathlib

directory = 'E:\\1-2\\CSE108\\Offlines Repo\\CSE108-Offlines\\Java Term Project\\Part 4 (Database based)\\src\\Assets\\Image\\Flags'

for path in pathlib.Path(directory).iterdir():
    if path.is_file():
        old_name = path.stem
        old_extension = path.suffix
        directory = path.parent
        new_name = old_name.split('-')[0].capitalize() + old_extension
        try:
            path.rename(pathlib.Path(directory, new_name))
        except:
            continue
