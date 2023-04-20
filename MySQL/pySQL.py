import mysql.connector
mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="game",
)

mycursor = mydb.cursor()

# INSERT DATA TO DATABASE
insert_query = "INSERT INTO game_tbl (game_id, game_name, game_cat, game_price) VALUES (%s, %s, %s, %s)"
insert_values = ("G04", "Dead By Daylight", "Survival", "369.00")
insert_confirmation = input("Do you want to insert a new record? (y/n) : ")
if insert_confirmation.lower() == "y":
    mycursor.execute(insert_query, insert_values)
    mydb.commit()
    print(mycursor.rowcount, "record inserted.")
else:
    print("Insert operation cancelled.")

# SELECT DATA FROM DATABASE
select_query = "SELECT * FROM game_tbl"
select_confirmation = input("Do you want to select all records? (y/n) : ")
if select_confirmation.lower() == "y":
    mycursor.execute(select_query)
    myresult = mycursor.fetchall()
    for x in myresult:
        print(x)
else:
    print("Select operation cancelled.")

# UPDATE DATA TO DATABASE
update_query = "UPDATE game_tbl SET game_name = 'PUBG' WHERE game_name = 'Apex'"
update_confirmation = input("Do you want to update a record? (y/n) : ")
if update_confirmation.lower() == "y":
    mycursor.execute(update_query)
    mydb.commit()
    print(mycursor.rowcount, "record(s) updated")
else:
    print("Update operation cancelled.")

# DELETE DATA FROM DATABASE
delete_query = "DELETE FROM game_tbl WHERE game_name = 'Apex'"
delete_confirmation = input("Do you want to delete a record? (y/n) : ")
if delete_confirmation.lower() == "y":
    mycursor.execute(delete_query)
    mydb.commit()
    print(mycursor.rowcount, "record(s) deleted")
else:
    print("Delete operation cancelled.")