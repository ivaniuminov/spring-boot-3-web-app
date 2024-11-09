package com.iuminov.springboottest.webapp.repository;

import com.iuminov.springboottest.webapp.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
