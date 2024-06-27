package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.entity.Transaction;
import com.cydeo.entity.User;
import com.cydeo.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
