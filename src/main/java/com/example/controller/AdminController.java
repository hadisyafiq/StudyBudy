package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    
    private UserDAO userDAO = new UserDAO();  // handle DB operations

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
                           Model model) { // Add Model as a parameter here
        // Create a User object from form data
        User user = new User(name, password, email, role);

        // Sauserve the user to the database using UserDAO
        boolean isSaved = userDAO.addUser(user);  // Assuming addUser returns a boolean indicating success

        // Check the result and pass a message to the model
        if (isSaved) {
            model.addAttribute("message", "User added successfully!");
        } else {
            model.addAttribute("message", "Failed to add user.");
        }
        
        // Fetch all users from the database to display
        model.addAttribute("users", userDAO.getAllUsers()); // Assuming getAllUsers returns a list of users

        // Redirect or forward to display.jsp
        return "display";  // Send the result message to display.jsp
    }
    
    @RequestMapping("/deleteuser")
	public String deleteuser(@RequestParam("del") int index, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<UserDAO> userList = (List<UserDAO>) session.getAttribute("userList");
		if (userList != null && index < userList.size()) {
			userList.remove(index);
		}
		
		return "redirect:/display.jsp";
	}
    
 
   
}