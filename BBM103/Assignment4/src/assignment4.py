import sys
# every c means column and every r means row
def print_on_the_screen(): 
    for row in range(len(matrix)):
        print(" ".join(matrix[row]))
    print(f"\nYour score is: {score}")

def gravity():
    for c in range(len(matrix[0])):
        index = len(matrix) - 1
        for r in range(len(matrix) - 1, - 1, - 1):
            if matrix[r][c] != " ":
                ball = matrix[r][c]
                matrix[r][c] = " "
                matrix[index][c] = ball
                index -= 1
    delete_rows()
    delete_columns()

def delete_columns():
    try:
        start, finish = -1, len(matrix[0]) - 1
        while start < finish:
            start += 1
            if matrix[-1][start] == " ":
                finish -= 1
                for r in range(len(matrix)):
                    del matrix[r][start]
                start -= 1
    except IndexError:
        print() #FOR BLANK MATRIX

def delete_rows():
    start, finish = -1, len(matrix) -1
    while start < finish:
        start += 1
        if len(set(matrix[start])) == 1 and matrix[start][0] == " ":
            finish -= 1
            del matrix[start]
            start -= 1
    
def take_input(): 
    global x, y, search
    while True:
        user_input = input("Please enter a row and column number: ")
        user_inp_list= user_input.split()
        x, y = int(user_inp_list[1]), int(user_inp_list[0])
        if x > len(matrix[0]) - 1 or x < 0 or y < 0 or y > len(matrix) - 1:
            print("Please enter a valid size!")
        else:
            if matrix[y][x] == " ":
                print("Please enter a valid size!")
            else:
                search = matrix[y][x]
                break

def click_explode(x, y):
    global score
    if search == "X":
        matrix[y][x] = " "
        for row in range(len(matrix)):
            score += func_dict[matrix[row][x]]
            if matrix[row][x] == "X":
                click_explode(x,row)
            matrix[row][x] = " "
        for column in range(len(matrix[y])):
            score += func_dict[matrix[y][column]]
            if matrix[y][column] == "X":
                click_explode(column, y)
            matrix[y][column] = " "
    else:
        if x < len(matrix[0]) - 1:
            if search == matrix[y][x+1]:
                score += func_dict[matrix[y][x]]
                score += func_dict[matrix[y][x+1]]
                matrix[y][x+1] = " "
                matrix[y][x] = " "
                click_explode(x+1, y)

        if y < len(matrix) - 1:
            if search == matrix[y+1][x]:
                score += func_dict[matrix[y][x]]
                score += func_dict[matrix[y+1][x]]
                matrix[y+1][x] = " "
                matrix[y][x] = " "
                click_explode(x, y+1)
        
        if x > 0:
            if search == matrix[y][x-1]:
                score += func_dict[matrix[y][x]]
                score += func_dict[matrix[y][x-1]]
                matrix[y][x-1] = " "
                matrix[y][x] = " "
                click_explode(x-1, y)
        
        if y > 0:
            if search == matrix[y-1][x]: 
                score += func_dict[matrix[y][x]]
                score += func_dict[matrix[y-1][x]] 
                matrix[y-1][x] = " "
                matrix[y][x] = " "
                click_explode(x, y-1)

def game_over_check():
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            if matrix[i][j] == "X":
                return True

    for r in range(len(matrix)):
        if len(matrix[0]) > 1:
            for c in range(len(matrix[0]) - 1):
                if matrix[r][c] == matrix[r][c+1] and matrix[r][c] != " ":
                    return True
                    
    if len(matrix) > 1:
        for c in range(len(matrix[0])):
            for r in range(len(matrix) - 1):
                if matrix[r][c] == matrix[r+1][c] and matrix[r][c] != " ":
                    return True
                    
    return False

def main():
    print_on_the_screen()
    while game_over_check() == True:
        print()
        take_input()
        click_explode(x, y)
        gravity()
        print()
        print_on_the_screen()
    print("\nGame Over")

score = 0
func_dict = {"B":9, "G": 8, "W": 7, "Y": 6, "R": 5, "P": 4, "O": 3, "D": 2, "F": 1, " ":0, "X":0}
inputfile = open(sys.argv[1], "r")
matrix = [row.split() for row in inputfile.read().split("\n")]
inputfile.close()

main()