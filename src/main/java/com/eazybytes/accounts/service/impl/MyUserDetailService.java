package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.UsersDto;
import com.eazybytes.accounts.entity.UserPrincipal;
import com.eazybytes.accounts.entity.Users;
import com.eazybytes.accounts.mapper.UsersMapper;
import com.eazybytes.accounts.repository.UserDetailsRepository;
import com.eazybytes.accounts.service.IUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService, IUserDetailService {

    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user1 = userDetailsRepository.findByUsername(username);
        if(user1 == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user1);
    }
    @Override
    public UsersDto registerUser(UsersDto usersDto) {
        Users user = UsersMapper.mapToUsers(usersDto, new Users());
        Users userDb = null;
        try{
            userDb = userDetailsRepository.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException("User or password already exists");
        }

        return UsersMapper.mapToUsersDto(userDb, new UsersDto());
    }
}
