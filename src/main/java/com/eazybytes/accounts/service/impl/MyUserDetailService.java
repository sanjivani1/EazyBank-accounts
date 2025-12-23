package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.UsersDto;
import com.eazybytes.accounts.entity.UserPrincipal;
import com.eazybytes.accounts.entity.Users;
import com.eazybytes.accounts.mapper.UsersMapper;
import com.eazybytes.accounts.repository.UserDetailsRepository;
import com.eazybytes.accounts.service.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService, IUserDetailService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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
            user.setPassword(encoder.encode(user.getPassword()));
            userDb = userDetailsRepository.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException("User or password already exists");
        }
        return UsersMapper.mapToUsersDto(userDb, new UsersDto());
    }

    @Override
    public String verify(UsersDto usersDto) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(usersDto.getUsername(), usersDto.getPassword()));

        if (authentication.isAuthenticated()){
            return jwtService.generateToken(usersDto.getUsername());
        }
        else return "false";
    }
}
