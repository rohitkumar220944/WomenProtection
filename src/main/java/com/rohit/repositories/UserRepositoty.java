package com.rohit.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rohit.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositoty extends JpaRepository<User, Long> {
   // correct return type

	List<User> findByRole(String role);

	User findByEmail(String username);


	User findByEmailAndPassword(String email, String password);
}
