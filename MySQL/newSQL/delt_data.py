from pyconnector import connect_to_database
mydb = connect_to_database()
mycursor = mydb.cursor()
from select_data import select

def delete():
    print("======= Your Data =======")
    select()
    print("=========================")
    delete_query = "DELETE FROM game_tbl WHERE game_name = %s"
    game_name = str(input("What game do you want to delete ( Press game name ) : "))
    values = (game_name,)
    delete_confirmation = input("Do you want to delete a record? (y/n) : ")

    if delete_confirmation.lower() == "y":
        mycursor.execute(delete_query, values)
        mydb.commit()
        print(mycursor.rowcount, "record(s) deleted")
    else:
        print("Delete operation cancelled.")
