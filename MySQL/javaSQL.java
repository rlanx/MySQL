package MySQL;

import java.sql.*;
import java.util.Scanner;

public class javaSQL {
    public void insertData(String game_id, String game_name, String game_cat, String game_price) {

        try {
            // create a mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/game";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            String sql = " insert into petfarm (game_id, game_name, game_cat, game_price)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, game_id);
            ps.setString(2, game_name);
            ps.setString(3, game_cat);
            ps.setString(4, game_price);
            // ps.setDate(4, new java.sql.Date(birthdate));
            ps.execute();
            System.out.println("Insert finished.");
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.out.println(e);
        }
    }

    public void showData() {
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/game";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            String sql = "SELECT * FROM game_tbl";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Id        : " + rs.getString("game_id"));
                System.out.println("Bleed     : " + rs.getString("game_name"));
                System.out.println("Name      : " + rs.getString("game_cat"));
                System.out.println("Birth Date: " + rs.getString("game_price"));
            }
            System.out.println("End of data.");
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.out.println(e);
        }
    }

    public void updateName(String game_id, String newName) {

        try {
            // create a mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/game";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            String sql = "UPDATE petfarm SET name=?  WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newName);
            ps.setString(2, game_id);
            ps.execute();
            System.out.println("1 record updated.");
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.out.println(e);
        }
    }

    public void deleteData(String game_id) {

        try {
            // create a mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/game";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            String sql = "DELETE FROM petfarm WHERE id =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, game_id);
            ps.execute();
            System.out.println("1 record deleted.");
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Please Choose number:");
        System.out.println("1. Insert data");
        System.out.println("2. Show data");
        System.out.println("3. Update name");
        System.out.println("4. Delete data");
        System.out.println("Other to exit...");
        System.out.println("");
        Scanner getData = new Scanner(System.in);
        int ch = Integer.parseInt(getData.nextLine());

        javaSQL app = new javaSQL();
        switch (ch) {
            case 1:
                String game_id = "10008";
                String game_name = "Ch23i Huaa Huaaa";
                String game_cat = "Jackiya";
                String game_price = "2022-05-13";
                app.insertData(game_id, game_name, game_cat, game_price);
                break;
            case 2:
                app.showData();
                break;
            case 3:
                game_id = "10007";
                String newname = "Fackandy";
                app.updateName(game_id, newname);
                break;
            case 4:
            game_id = "10006";
                app.deleteData(game_id);
            default: {
            }
        }
    }
    
}
