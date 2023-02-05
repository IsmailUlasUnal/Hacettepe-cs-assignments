import sys

friendsinput = open(sys.argv[1], encoding="utf-8")
a = friendsinput.read().split("\n") 
dict_user = {} 
setdel = {''} #for deleting "" from my set, it avoids some bugs when user gives multiple spaces
#This code creates a dictionary that keys are string type user and values are set type friends
for i in a:
    i = i.replace(":", " ")
    i = i.split(" ")
    dict_user[i[0]] = set(i[1:]) - setdel    
friendsinput.close()

def Existuser(user1, user2, cmd): #Error, mentioned in PDF
    if user2 == None:
        if user1 not in dict_user:
            f.write(f"ERROR: Wrong input type! for '{cmd}'!--There is no user named '{user1}'!!\n")
    else:
        if i[1] not in dict_user and i[2] not in dict_user:
            f.write(f"ERROR: Wrong input type! for '{cmd}'! -- No user named! '{user1}' and '{user2}' found\n")
        elif user1 not in dict_user:
            f.write(f"ERROR: Wrong input type! for '{cmd}'! -- There is no user named '{user1}'\n")
        elif user2 not in dict_user: 
            f.write(f"ERROR: Wrong input type! for '{cmd}'! -- There is no user named '{user2}'\n")

def new_user(user):
    if user in dict_user:
        f.write(f"ERROR: Wrong input type! for 'ANU’! -- This user already exists!!\n")
    else:
        dict_user[user] = set([])
        f.write(f"User '{user}' has been added to the social network successfully\n")

def del_user(user):
    if user in dict_user:
        for values in dict_user.values():
            if user in values:
                values.remove(user)
        del dict_user[user]
        f.write(f"User '{user}' and his/her all relations have been deleted successfully\n")

def add_friend(user1, user2):
    if user1 in dict_user and user2 in dict_user:
        if user2 in dict_user[user1]: 
            f.write(f"ERROR: A relation between '{user1}' and '{user2}' already exists!!\n")
        else:
            dict_user[user1].add(user2)
            dict_user[user2].add(user1)
            f.write(f"Relation between '{user1}' and '{user2}' has been added successfully\n")

def del_friend(user1, user2):
    if user1 in dict_user and user2 in dict_user:
        if user1 not in dict_user[user2]:
            f.write(f"ERROR: No relation between '{user1}' and '{user2}' found!!\n")
        else:
            dict_user[user1].remove(user2)
            dict_user[user2].remove(user1)
            f.write(f"Relation between '{user1}' and '{user2}' has been deleted successfully\n")

def count_friend(user):
    if user in dict_user:
        count = len(dict_user[user])
        f.write(f"User '{user}' has {count} friends\n")

def possible_friends(user1, max_distance):
    if user1 in dict_user:
        if int(max_distance) > 3 or int(max_distance) < 1:
            f.write(f"ERROR: Maximum distance is out of range!!\n")
        else:
            possible, MaxD= dict_user[user1], int(max_distance)
            if len(dict_user[user1]) == 0:
                f.write(f"ERROR: '{user1}' has no friends.\n")
            else: 
                while MaxD > 1:
                    MaxD -= 1
                    for key in possible:
                        possible = dict_user[key] | possible
                    possible.remove(user1)
                possible = "{" + str(sorted(possible))[1:-1] + "}"
                f.write(f"User '{user1}' have {len(possible)} possible friends when maximum distance is {max_distance}\n")
                f.write(f"These possible friends: {possible}\n")

def suggest_friend(user, MD_input):
    if user in dict_user:
        if int(MD_input) > 4 or int(MD_input) < 1:
            f.write(f"Error: Mutually Degree cannot be less than 2 or greater than 3\n")
        elif len(dict_user[user]) == 0:##Ekstra Error
            f.write(f"ERROR: '{user}' has no friends.\n")
        else:
            listSF = []
            for j in dict_user[user]:
                listSF = listSF + list(dict_user[j])
            dictSF = {i:listSF.count(i) for i in listSF}
            del dictSF[user]
            MD2, MD3 = [], []
            for key, value in dictSF.items():
                if value == 2:
                    MD2.append(key)
                elif value == 3:
                    MD3.append(key)
            ### IMPORTANT = THIS PART IS FOR NOT SUGGESTING ALREADY EXIST FRIENDS. BUT IT DIDN'T MENTIONED IN PDF CLEARLY SO IM NOT SURE ABOUT THIS
            MD2 = set(MD2) - dict_user[user]
            MD3 = set(MD3) - dict_user[user]

            #After this part I added some extra error's and posibilities but it makes my code much longer
            MD2, MD3 = sorted(MD2), sorted(MD3)
            if int(MD_input) == 2:
                if MD2 == [] and MD3 == []:
                    f.write(f"ERROR: '{user}' has no 2 and 3 mutual friends :(\n")
                else:
                    f.write(f"Suggestion List for '{user}' (when MD is 2):\n")
                    if sorted(MD2) == []:
                        f.write(f"'{user}' has no 2 mutual friends\n")
                        for j in sorted(MD3):
                            f.write(f"'{user}' has 3 mutual friends with '{j}'\n")
                        f.write(f"The suggested friends for '{user}': {str(MD3)[1:-1]}\n")
                    else:
                        for j in sorted(MD2):
                            f.write(f"'{user}' has 2 mutual friends with '{j}'\n")
                        if MD3 == 0:
                            f.write(f"The suggested friends for '{user}': {str(MD2)[1:-1]}\n")
                        else:
                            for j in sorted(MD3):
                                f.write(f"'{user}' has 3 mutual friends with '{j}'\n")
                            MD2 = MD2 + MD3
                            MD2 = sorted(MD2)
                            f.write(f"The suggested friends for '{user}': {str(MD2)[1:-1]}\n")                    
            elif int(MD_input) == 3:
                if MD3 == []:
                    f.write(f"ERROR: '{user}' has no 3 mutual friends\n")
                else:
                    f.write(f"Suggestion List for '{user}' (when MD is 3):\n")
                    for j in sorted(MD3):
                        f.write(f"'{user}' has 3 mutual friends with '{j}'\n")
                    f.write(f"The suggested friends for '{user}': {str(MD3)[1:-1]}\n")

commandinput = open(sys.argv[2], encoding="utf-8")
b = commandinput.read().split("\n")
with open("output.txt", "w") as f:
    for i in b:
        i = i.split(" ")
        if i[0] == "ANU":
            new_user(i[1])
        elif i[0] == "DEU": 
            Existuser(i[1], None, "DEU")
            del_user(i[1])
        elif i[0] == "ANF":
            Existuser(i[1], i[2], "ANF")
            add_friend(i[1], i[2])
        elif i[0] == "DEF":
            Existuser(i[1], i[2], "DEF")
            del_friend(i[1], i[2])        
        elif i[0] == "CF":
            Existuser(i[1], None, "CF")
            count_friend(i[1])
        elif i[0] == "FPF":
            Existuser(i[1], None, "FPF")
            possible_friends(i[1], i[2])
        elif i[0] == "SF": 
            Existuser(i[1], None, "SF")
            suggest_friend(i[1], i[2])
        else: #extra error
            i = " ".join(i)
            f.write(f"ERROR: '{i}' is a wrong command.\n")
commandinput.close()