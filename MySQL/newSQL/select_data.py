from pyconnector import connect_to_database
mydb = connect_to_database()
mycursor = mydb.cursor()
def select():
    select_query = "SELECT * FROM game_tbl"
    mycursor.execute(select_query)
    myresult = mycursor.fetchall()
    print("======= Your Data =======")
    for x in myresult:
        print(x)
    print("=========================")
        

    