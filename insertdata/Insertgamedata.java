package insertdata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Insertgamedata extends JFrame implements ActionListener {
    JLabel label1, label2, label3, label4;
    JTextField tf1, tf2, tf3, tf4;
    JButton btn;

    Insertgamedata() {

        setTitle("Insert Game Data");
        setLayout(new BorderLayout());
        setSize(400, 200);

        // Set up the input fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        label1 = new JLabel("Game ID:");
        tf1 = new JTextField(10);
        inputPanel.add(label1);
        inputPanel.add(tf1);

        label2 = new JLabel("Game Name:");
        tf2 = new JTextField(10);
        inputPanel.add(label2);
        inputPanel.add(tf2);

        label3 = new JLabel("Game Category:");
        tf3 = new JTextField(10);
        inputPanel.add(label3);
        inputPanel.add(tf3);

        label4 = new JLabel("Game Price:");
        tf4 = new JTextField(10);
        inputPanel.add(label4);
        inputPanel.add(tf4);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btn = new JButton("Add");
        btn.addActionListener(this);
        buttonPanel.add(btn);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        String game_id = tf1.getText();
        String game_name = tf2.getText();
        String game_cat = tf3.getText();
        String game_price = tf4.getText();

        String url = "jdbc:mysql://localhost/game";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");

            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO game_tbl (game_id, game_name, game_cat, game_price) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, game_id);
            pstmt.setString(2, game_name);
            pstmt.setString(3, game_cat);
            pstmt.setString(4, game_price);
            int rowsInserted = pstmt.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(this, "Record added successfully");
            clearFields();

            if (rowsInserted > 0) {
                System.out.println("A new game list was inserted successfully!");
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Database connection failed!");
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        // Clear the input fields
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
    }

    

    public static void main(String[] args) {
        new Insertgamedata();
    }
}
