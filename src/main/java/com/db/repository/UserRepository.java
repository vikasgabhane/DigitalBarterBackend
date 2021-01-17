package com.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByEmailIdAndPassword(String EmailId,String Password);
	public User findByUserName(String userName);
	public User findByEmailId(String emailId);

}
