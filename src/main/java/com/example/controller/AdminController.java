package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    
    private UserDAO userDAO = new UserDAO();  // Handle DB operations

    @RequestMapping("/adduser")
    public String getUser(Model model) {
        model.addAttribute("user", new User()); // Add an empty User object to bind the form data
        return "adduser";  // Returns the view "adduser.jsp"
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("role") String role, 
                           Model model) {
        // Create a User object from form data
        User user = new User(name, password, email, role);

        // Save the user to the database using UserDAO
        boolean isSaved = userDAO.addUser(user);

        // Check the result and pass a message to the model
        if (isSaved) {
            model.addAttribute("message", "User added successfully!");
        } else {
            model.addAttribute("message", "Failed to add user.");
        }
        
        // Fetch all users from the database to display
        model.addAttribute("users", userDAO.getAllUsers());

        // Redirect or forward to display.jsp
        return "display";  // Send the result message to display.jsp
        }
    
    
    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("name") String name, 
                            @RequestParam("password") String password, 
                            @RequestParam("email") String email, 
                            @RequestParam("role") String role, 
                            Model model) {
        boolean isDeleted = userDAO.deleteUser(name, password, email, role);
        
        if (isDeleted) {
            model.addAttribute("message", "User deleted successfully!");
        } else {
            model.addAttribute("message", "Failed to delete user.");
        }
        
        // Add this line to refresh the users list
        model.addAttribute("users", userDAO.getAllUsers());
        
        return "display";
    }
    
    
    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String editUser(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("role") String role,
                           @RequestParam("originalEmail") String originalEmail,
                           HttpSession session) {
        
        // Call the updateUser method in the DAO to update the user in the database
        boolean isUpdated = userDAO.updateUser(originalEmail, name, password, email, role);

        if (isUpdated) {
            // If update is successful, create a User object with updated information
            User updatedUser = new User(name, password, email, role);

            // Store the updated user in session for display on the next page
            session.setAttribute("updatedUser", updatedUser);

            // Redirect to the UpdatedUser page to display the updated user information
            return "redirect:/UpdatedUser";
        } else {
            // If update failed, log the error and redirect back to the admin page
            System.out.println("Failed to update the user in the database");
            return "redirect:/admin.jsp";
        }
    }
    
    @RequestMapping("/UpdatedUser")
    public String displayUpdatedUser(HttpSession session, Model model) {
        // Get the updated user object from the session
        User updatedUser = (User) session.getAttribute("updatedUser");

        if (updatedUser != null) {
            // Add the updated user to the model to be displayed in the view
            model.addAttribute("user", updatedUser);

            // Clear the session attribute after it's used
            session.removeAttribute("updatedUser");

            // You can add additional logic here to refresh the list of users, if necessary
            model.addAttribute("users", userDAO.getAllUsers()); // Optional: refresh the users list
            
            // Return the view name for displaying the updated user
            return "display";  // This will show the updated user in the display.jsp page
        } else {
            // If the updated user is not found, redirect to the admin page
            return "redirect:/admin.jsp";
        }
    }
}