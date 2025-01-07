package com.example.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/user_db"; // Update DB name if needed
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    
    static {
        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQL JDBC Driver successfully loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    // Get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Method to get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Method to insert a new user into the database
    public boolean addUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return false;
        }

        String checkQuery = "SELECT COUNT(*) FROM users WHERE name = ?";
        String insertQuery = "INSERT INTO users (name, password, email, role) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {

            checkStmt.setString(1, user.getName());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }

            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, user.getName());
                insertStmt.setString(2, user.getPassword());
                insertStmt.setString(3, user.getEmail());
                insertStmt.setString(4, user.getRole());
                int rowsInserted = insertStmt.executeUpdate();
                return rowsInserted > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a user based on users name, password, email, role
    public boolean deleteUser(String name, String password, String email, String role) {
        String deleteQuery = "DELETE FROM users WHERE name = ? AND password = ? AND email = ? AND role = ?";
        try (Connection connection = getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
            deleteStmt.setString(1, name);
            deleteStmt.setString(2, password);
            deleteStmt.setString(3, email);
            deleteStmt.setString(4, role);
            int rowsDeleted = deleteStmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 // Method to update a user based on users name, password, email, role
    public boolean updateUser(String originalEmail, String name, String password, String email, String role) {
        // Prepare the SQL query to update the user
        String updateQuery = "UPDATE users SET name = ?, password = ?, email = ?, role = ? WHERE email = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateQuery)) {

            // Set parameters for the query
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, email); // Set the new email
            stmt.setString(4, role);
            stmt.setString(5, originalEmail); // Find the user based on originalEmail

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Return true if rows were updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an error
        }
    }
    

}