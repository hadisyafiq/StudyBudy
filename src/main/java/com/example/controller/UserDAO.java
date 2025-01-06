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
        List<User> users = new ArrayList<>(); // Corrected variable name
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
                users.add(user); // Add user to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

 // Method to insert a new user to the database
    public boolean addUser(User user) {
        // Validate input: name must not be null or empty
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return false; // Return false if the name is null or empty
        }

        // SQL query to check if a user with the same name already exists
        String checkQuery = "SELECT COUNT(*) FROM users WHERE name = ?";
        String insertQuery = "INSERT INTO users (name, password, email, role) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {

            // Check if the name already exists in the database
            checkStmt.setString(1, user.getName());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // Return false if a duplicate name exists
                }
            }

            // Insert the user if the name does not exist
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, user.getName());
                insertStmt.setString(2, user.getPassword());
                insertStmt.setString(3, user.getEmail());
                insertStmt.setString(4, user.getRole());
                int rowsInserted = insertStmt.executeUpdate();
                return rowsInserted > 0; // Return true if the user was added successfully
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}