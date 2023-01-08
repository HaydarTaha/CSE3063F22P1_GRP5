import json
from input import Input


def main():
    with open('inputs/input.json', 'r') as f:
        data = json.load(f)
    input = Input(data)
    input.create_objects()
    input.start_simulation_with_inputs()

if __name__ == "__main__":
    main()
