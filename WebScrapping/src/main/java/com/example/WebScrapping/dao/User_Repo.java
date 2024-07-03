package com.example.WebScrapping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebScrapping.model.User;
import java.util.List;
import java.util.Optional;



@Repository
public interface User_Repo extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	 Optional<User> findByUserid(int userid);
	

}
