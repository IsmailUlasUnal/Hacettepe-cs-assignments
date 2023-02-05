print("<-----RULES----->\n1. BRUSH DOWN\n2. BRUSH UP\n3. VEHICE ROTATES RIGHT\n4. VEHICE ROTATES LEFT\n5. MOVE UP TO X\n6. JUMP\n7. REVERSE DIRECTION\n8. VIEW THE MATRIX\n0. EXIT\nPlease enter the commands with a plus sign (+) between them.")

# If a number between 0-8 is not entered in the commands, it will give an error message
right_input = False
while right_input == False: 
    a = input().split("+") 
    a[0] = a[0].replace('"', "") 
    N = int(a[0])
    if N > 0:
        right_input = True
        for i in a[1:]:
            if i[:2] == "5_":
                if int(i[2:]) > 0:
                    continue
                else:
                    right_input = False
                    print("You entered an Incorrect command. Please try again!")  
                    break
            elif int(i) == 5:
                right_input = False
                print("You entered an Incorrect command. Please try again!")
                break
            elif int(i) == 0:
                break
            elif 0 < int(i) <= 8:
                continue
            else:
                right_input = False
                print("You entered an Incorrect command. Please try again!")
                break
    elif N == 0:
        break
    else:
        right_input = False
        print("You entered an Incorrect command. Please try again!")
        continue

matrix = []
row = []
for j in range(N+2):
    if j == 0 or j == N+1: 
        matrix.append(["+" for i in range(N+2)])
    else:
        for i in range(N+2):
            if i == 0 or i == N+1:
                row.append("+")
            else: 
                row.append(" ")
        matrix.append(row)
        row = []

matrix[1][1] = matrix[1][1] + "v"
brush_above = 1 #when it is 0 it means that brush is down and it will print.
direction = 1 #right=1, down=2, left=3, up=4 

def positiony():
    positiony = 0
    for i in matrix:
        if " v" in i:
            break
        elif "*v" in i:
            break
        else:
            positiony += 1
    return int(positiony)

def positionx():
    positionx = 0
    for i in matrix[positiony()]:
        if " v" in i:
            break
        elif "*v" in i:
            break
        else: 
            positionx += 1
    return int(positionx)

def newdirection(x):
    global direction
    direction += x
    if direction > 4:
        direction -= 4

def moveright(x): 
    cx = positionx()
    cy = positiony()
    if brush_above == 0:
        for i in range(x+1):
            k = 0
            while cx + i - k > N:
                k += N
            if i == 0:
                matrix[cy][cx] = matrix[cy][cx][:-1]
            elif i == x:
                matrix[cy][cx + i - k] = "*v"
                k = 0
            else:
                matrix[cy][cx + i - k] = "*"
    else:
        k = 0
        while cx + x - k > N:
            k += N
        matrix[cy][cx] = matrix[cy][cx][:-1]
        matrix[cy][cx + int(x) - k] = matrix[cy][cx + int(x) - k] + "v"
        k = 0

def moveleft(x):
    cx = positionx()
    cy = positiony()
    if brush_above == 0:
        for i in range(x+1):
            k = 0
            while cx - i + k < 1:
                k += N
            if i == 0:
                matrix[cy][cx] = matrix[cy][cx][:-1]
            elif i == x:
                matrix[cy][cx - int(x) + k] = "*v"
                k = 0
            else:
                matrix[cy][cx - i + k] = "*"
    else:
        k = 0
        while cx - x + k < 1:
            k += N
        matrix[cy][cx] = matrix[cy][cx][:-1]
        matrix[cy][cx - int(x) + k] = matrix[cy][cx - int(x) + k] + "v"
        k = 0

def movedown(x):
    cx = positionx()
    cy = positiony()
    if brush_above == 0:
        for i in range(x+1):
            k = 0
            while cy + i - k > N:
                k += N
            if i == 0:
                matrix[cy][cx] = matrix[cy][cx][:-1]
            elif i == x:
                matrix[cy + i - k][cx] = "*v"
                k = 0
            else:
                matrix[cy + i - k][cx] = "*"

    else:
        k = 0
        while cy + x - k > N:
            k += N
        matrix[cy][cx] = matrix[cy][cx][:-1]
        matrix[cy + int(x) - k][cx] = matrix[cy + int(x) - k][cx] + "v"
        k = 0

def moveup(x):
    cx = positionx()
    cy = positiony()
    if brush_above == 0:
        for i in range(x+1):
            k = 0
            while cy - i + k < 1:
                k += N
            if i == 0:
                matrix[cy][cx] = matrix[cy][cx][:-1]
            elif i == x:
                matrix[cy - int(x) + k][cx] = "*v"
                k = 0
            else:
                matrix[cy - i + k][cx] = "*"

    else:
        k = 0
        while cy - x + k < 1:
            k += N
        matrix[cy][cx] = matrix[cy][cx][:-1]
        matrix[cy - int(x) + k][cx] = matrix[cy - int(x) + k][cx] + "v"
        k = 0

def move(x): 
    if x > 0:
        cx = positionx()
        cy = positiony()
        if direction == 1:   
            moveright(x)
            
        elif direction == 2:  
            movedown(x)

        elif direction == 3:  
            moveleft(x)

        else:                  
            moveup(x)

for i in a[1:]:
    if int(i) == 1:
        brush_above = 0
        matrix[positiony()][positionx()] = "*v"

    elif int(i) == 2:
        brush_above = 1

    elif int(i) == 3: 
        newdirection(1)

    elif int(i) == 4: 
        newdirection(3)
            
    elif int(i) == 6: 
        brush_above = 1
        move(3)

    elif int(i) == 7:
        newdirection(2)

    elif int(i) == 8:
        cy = positiony()
        cx = positionx()
        matrix[positiony()][positionx()] = matrix[positiony()][positionx()][:-1]
        for j in range(len(matrix)): 
            print("".join(matrix[j]))
        matrix[cy][cx] = matrix[cy][cx] + "v"

    elif int(i) == 0:
        break

    elif str(i)[:2] == "5_":
        asdf = i[2:]
        move(int(asdf))