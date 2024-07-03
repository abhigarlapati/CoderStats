package com.example.WebScrapping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

import com.example.WebScrapping.dao.User_Repo;
import com.example.WebScrapping.model.User;
import com.example.WebScrapping.model.Contest;

import com.example.WebScrapping.service.Scrapping_Service;
import com.example.WebScrapping.service.User_service;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class WebScrapping_Controller {
	
	
	@Autowired
	User_Repo userdao;
	
	@Autowired
	User_service user_service;
	
	@Autowired
	Scrapping_Service ss;
	
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/")
	public String welcome(Model model)
	{
		model.addAttribute("user",new User());
		return "register";
	}
	public String getLoggedInUserEmail() {
        return (String) httpSession.getAttribute("loggedInUser");
    }
	
	@PostMapping("/logout")
	public String logout()
	{
		httpSession.setAttribute("loggedInUser", null);
		return "redirect:/login";
	}




	@GetMapping("/login")
	public String login(Model model)
	{
	return "login";
	}

	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
	
	@GetMapping("/updateprofile")
	public String updateprofile(Model model) {
	    User user = user_service.getUser(getLoggedInUserEmail());
	    if(user!=null)
	    {
	    model.addAttribute("user", user);
	    return "updateprofile";
	    }
	    else {
	    	return "login";
	    }
	}
	

	@GetMapping("/index")
	public String index(Model model) throws IOException {
	    User user = user_service.getUser(getLoggedInUserEmail());
	    
	    if(user!=null)
	    {
	    
	    model.addAttribute("name",user.getName());

	    String codechefurl = "https://www.codechef.com/users/";
	    if (user.getCodechef_username() != null) {
	        String codechefusername = user.getCodechef_username();
	        String fullcodechefurl = codechefurl + codechefusername;

	        String codechefRating = ss.CodechefRating(fullcodechefurl);
	        String codechefContests = ss.CodechefCC(fullcodechefurl);
	        String codechefSolved = ss.CodechefPSC(fullcodechefurl);

	        model.addAttribute("link1", fullcodechefurl);
	        model.addAttribute("codechefRating", codechefRating);
	        model.addAttribute("codechefContests", codechefContests);
	        model.addAttribute("codechefSolved", codechefSolved);
	    }

	    String codeforecesurl = "https://codeforces.com/profile/";
	    if (user.getCodeforces_username() != null) {
	        String codeforcesusername = user.getCodeforces_username();
	        String fullcodeforcesurl = codeforecesurl + codeforcesusername;

	        String codeforcesRating = ss.CodeForecesRating(fullcodeforcesurl);
	        String codeforcesSolved = ss.CodeForcesSolvedCount(fullcodeforcesurl);
	        String codeforcesContest = ss.CodeForcesContestParticipated(codeforcesusername);

	        model.addAttribute("link2", fullcodeforcesurl);
	        model.addAttribute("codeforcesRating", codeforcesRating);
	        model.addAttribute("codeforcesContests", codeforcesContest);
	        model.addAttribute("codeforcesSolved", codeforcesSolved);
	    }

	    String leetcodeapi = "https://alfa-leetcode-api.onrender.com/";
	    if (user.getLeetcode_username() != null) {
	        String username = user.getLeetcode_username();
	        String fulleetocodeurl = leetcodeapi + username;

	        String leetcoderating = ss.LeetCodeRating(username);
	        String leetcodesolvedcount = ss.LeetCodeSolvedCount(username);
	        String leetcodecontestcount = ss.LeetCodeContestParticipated(username);

	        model.addAttribute("link3", fulleetocodeurl);
	        model.addAttribute("leetcodeRating", leetcoderating);
	        model.addAttribute("leetcodeContests", leetcodecontestcount);
	        model.addAttribute("leetcodeSolved", leetcodesolvedcount);
	    }

	    return "index";
	    }
	    else {
	    	return "login";
	    }
	}
	


	@PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		
		if (user_service.emailExists(user.getEmail())) {
			bindingResult.rejectValue("email", "error.user", "Email already exists , Use anyone in email format");
            return "register";
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userdao.save(user);
        return "redirect:/login";
    }
	 

	@PostMapping("/login")
	public String loginuser(@RequestParam("email") String email, @RequestParam("password") String password)
	{
		

	if(user_service.login(email,password))
	{
		httpSession.setAttribute("loggedInUser", email);
	return "redirect:/index";
	}
	
	System.out.println("Error");

	return "redirect:/login";
	}
	
	 @PostMapping("/update")
	    public String update(@ModelAttribute("user") User updatedUser) {
		 
		 if(updatedUser!=null)
		 {
	        // Debugging log to check if user is received correctly
	        System.out.println("Received User for Update: " + updatedUser);

	        user_service.updateUser(updatedUser);
	        return "redirect:/index";
		 }
		 else {
			 return "login";
		 }
	    }
	 
	 
	        
	    



}
