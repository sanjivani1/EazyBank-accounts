package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String userName);
}
