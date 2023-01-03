import json
from input import Input
import fileinput

def main():
    with open('inputs/input.json', 'r') as f:
        data = json.load(f)
    inputClass = Input(data)

if __name__ == "__main__":
    main()