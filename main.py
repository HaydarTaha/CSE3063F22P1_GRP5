import json
from input import Input


def main():
    with open('inputs/input.json', 'r') as f:
        data = json.load(f)
    inputClass = Input(data)
    inputClass.create_objects()


if __name__ == "__main__":
    main()
