package com.example.WebScrapping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebScrapping.dao.User_Repo;
import com.example.WebScrapping.model.User;

@Service
public class User_service {
	
	@Autowired
	User_Repo repo;

	public boolean login(String email, String password) {
		// TODO Auto-generated method 
		
		User user=repo.findByEmail(email);
		

		if(user!=null && password.equals(user.getPassword()))
		{
		return true;
		}
		
		return false;
	}
	
	public User getUser(String email)
	{
		User user=repo.findByEmail(email);
		return user;
	}
	
	public User getUserId(String email) {
        User user = repo.findByEmail(email);
        return user;
    }
	
	
	@Transactional
    public void updateUser(User updatedUser) {
        Optional<User> optionalUser = repo.findByUserid(updatedUser.getUserid());
         
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (existingUser != null) {
                existingUser.setName(updatedUser.getName());
                existingUser.setCodechef_username(updatedUser.getCodechef_username());
                existingUser.setCodeforces_username(updatedUser.getCodeforces_username());
                existingUser.setLeetcode_username(updatedUser.getLeetcode_username());

                // Debugging log to check user details before saving
                System.out.println("Updating User: " + existingUser);

                repo.save(existingUser);
            }
        } else {
            // Debugging log if user not found
            System.out.println("User not found with ID: " + updatedUser.getUserid());
        }
    }
	
	public boolean emailExists(String email) {
        return repo.findByEmail(email) != null;
    }

}
