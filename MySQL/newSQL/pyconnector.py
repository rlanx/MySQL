import mysql.connector

def connect_to_database():
    # Create a connection object and connect to the database
    mydb = mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="game"
    )

    # Return the connection object
    return mydb