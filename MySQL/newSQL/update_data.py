from pyconnector import connect_to_database
from select_data import select

def update():
    print("======= Your Data =======")
    select()
    print("=========================")
    mydb = connect_to_database()
    mycursor = mydb.cursor()
    update_query = "UPDATE game_tbl SET game_name = %s WHERE game_name = %s"

    old_game_name = input("Enter the old game name : ")
    game_name = input("Enter the new game name : ")
    
    update_confirmation = input("Do you want to update a record? (y/n) : ")

    if update_confirmation.lower() == "y":
        values = (game_name, old_game_name)
        mycursor.execute(update_query, values)
        mydb.commit()
        print(mycursor.rowcount, "record(s) affected")
    else:
        print("Update operation cancelled.")