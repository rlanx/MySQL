from pyconnector import connect_to_database
from select_data import select
from insert_data import insert
from update_data import update
from delt_data import delete

mydb = connect_to_database()

def select_func():
    while True:
        insert_choose = str(input("-------- What do you want to do?? Please Choose Number -------- \n (1) Insert\n (2) Update\n (3) View Informationt\n (4) Delete\n (5) Exit\n ==> "))
        if insert_choose == "1":
            insert()
        elif insert_choose == "2":
            update()
        elif insert_choose == "3":
            select()
        elif insert_choose == "4":
            delete()
        elif insert_choose == "5":
            print("Exiting program...")
            break
        else:
            print("Error")
        
select_func()

    
