package com.rectifier.future_light.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rectifier.future_light.model.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, String> {
	
	@Modifying
	@Query("UPDATE Users u SET u.fullname = :fullname, u.email = :email, u.gender = :gender, u.age = :age WHERE u.username = :username")
	void saveAdditionalInfo (
			@Param("username") String username,
			@Param("fullname") String fullname, 
			@Param("email") String email,
			@Param("gender") String gender,
			@Param("age") int age
			);
}
