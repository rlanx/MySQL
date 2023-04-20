from pyconnector import connect_to_database
mydb = connect_to_database()
mycursor = mydb.cursor()

def insert():
    print("======= Insert your game detail =======")
    game_id = input("ID : ")
    game_name = input("Name : ")
    game_cat = input("Category : ")
    game_price = input("Price : ")

    insert_query = "INSERT INTO game_tbl (game_id, game_name, game_cat, game_price) VALUES (%s, %s, %s, %s)"
    insert_values = (game_id, game_name, game_cat, game_price)
    insert_confirmation = input("Do you want to insert a new record? (y/n) : ")
    
    if insert_confirmation.lower() == "y":
        mycursor.execute(insert_query, insert_values)
        mydb.commit()
        print(mycursor.rowcount, "record inserted.")
    else:
        print("Insert operation cancelled.")
