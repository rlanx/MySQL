package insertdata;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CrudForm extends JFrame implements ActionListener {
    // Define UI components
    JLabel label1, label2, label3, label4;
    JTextField tf1, tf2, tf3, tf4;
    JButton btnAdd, btnEdit, btnDelete;
    JTable table;
    DefaultTableModel model;

    // Database connection details
    String url = "jdbc:mysql://localhost:3306/game";
    String username = "root";
    String password = "";

    CrudForm() {
        // Set up the form
        setTitle("CRUD Form");
        setLayout(new BorderLayout());
        setSize(600, 400);

        // Set up the input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
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

        // Set up the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        buttonPanel.add(btnAdd);
        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(this);
        buttonPanel.add(btnEdit);
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up the table
        String[] columnNames = { "game_id", "game_name", "game_cat", "game_price" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load the data from the database
        loadData();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            // Add a new record to the database
            String game_id = tf1.getText();
            String game_name = tf2.getText();
            String game_cat = tf3.getText();
            String game_price = tf4.getText();
            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO game_tbl VALUES (?, ?, ?, ?)");
                stmt.setString(1, game_id);
                stmt.setString(2, game_name);
                stmt.setString(3, game_cat);
                stmt.setString(4, game_price);
                stmt.executeUpdate();
                conn.close();
                JOptionPane.showMessageDialog(this, "Record added successfully");
                clearFields();
                loadData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == btnEdit) {
            // Update an existing record in the database
            int row = table.getSelectedRow();
            if (row >= 0) {
                String game_id = (String) model.getValueAt(row, 0);
                String game_name = tf2.getText();
                String game_cat = tf3.getText();
                String game_price = tf4.getText();
                try {
                    Connection conn = DriverManager.getConnection(url, username, password);
                    PreparedStatement stmt = conn
                            .prepareStatement("UPDATE game_tbl SET game_name=?, game_cat=?, game_price=? WHERE game_id=?");
                    stmt.setString(1, game_name);
                    stmt.setString(2, game_cat);
                    stmt.setString(3, game_price);
                    stmt.setString(4, game_id);
                    stmt.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Record updated successfully");
                    clearFields();
                    loadData();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a record to edit");
            }
        } else if (e.getSource() == btnDelete) {
            // Delete a record from the database
            int row = table.getSelectedRow();
            if (row >= 0) {
                String game_id = (String) model.getValueAt(row, 0);
                try {
                    Connection conn = DriverManager.getConnection(url, username, password);
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM game_tbl WHERE game_id=?");
                    stmt.setString(1, game_id);
                    stmt.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Record deleted successfully");
                    clearFields();
                    loadData();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a record to delete");
            }
        }
    }

    private void clearFields() {
        // Clear the input fields
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
    }

    private void loadData() {
        // Load the data from the database into the table
        model.setRowCount(0);
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM game_tbl");
            while (rs.next()) {
                String game_id = rs.getString("game_id");
                String game_name = rs.getString("game_name");
                String game_cat = rs.getString("game_cat");
                String game_price = rs.getString("game_price");
                Object[] row = { game_id, game_name, game_cat, game_price };
                model.addRow(row);
            }
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new CrudForm();
    }
}