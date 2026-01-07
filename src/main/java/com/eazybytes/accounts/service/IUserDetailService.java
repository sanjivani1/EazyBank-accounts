package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.UsersDto;
import com.eazybytes.accounts.entity.Users;

public interface IUserDetailService {

    UsersDto registerUser(UsersDto usersDto);

    String verify(UsersDto usersDto);
}
