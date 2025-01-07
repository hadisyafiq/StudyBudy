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
    
    
    @RequestMapping("/edituser")
    public String editUser(@RequestParam("name") String name,
                          @RequestParam("password") String password,
                          @RequestParam("email") String email,
                          @RequestParam("role") String role,
                          HttpSession session) {
        
        boolean isUpdated = userDAO.updateUser(name, password, email, role);
        
        if (isUpdated) {
            // Create a User object with updated information
            User updatedUser = new User();
            updatedUser.setName(name);
            updatedUser.setPassword(password);
            updatedUser.setEmail(email);
            updatedUser.setRole(role);
            
            // Store updated user in session for display
            session.setAttribute("updatedUser", updatedUser);
            return "redirect:/UpdatedUser";
        } else {
            System.out.println("Failed to update the user in database");
            return "redirect:/admin.jsp";
        }
    }
    
    @RequestMapping("/UpdatedUser")
    public String displayUpdatedUser(HttpSession session, Model model) {
        User updatedUser = (User) session.getAttribute("updatedUser");
        
        if (updatedUser != null) {
            model.addAttribute("user", updatedUser);
            // Clear the session attribute after use
            session.removeAttribute("updatedUser");
            
            // Add this line to refresh the users list
            model.addAttribute("users", userDAO.getAllUsers());
           
            return "display";  // This will show display.jsp
        } else {
            return "redirect:/admin.jsp";
        }
    }
}

