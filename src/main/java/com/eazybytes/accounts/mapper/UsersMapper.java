package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.UsersDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Users;

public class UsersMapper {

    public static UsersDto mapToUsersDto(Users users, UsersDto usersDto) {
        usersDto.setUsername(users.getUsername());
        usersDto.setPassword(users.getPassword());
        return usersDto;
    }

    public static Users mapToUsers(UsersDto usersDto, Users users) {
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
        return users;
    }
}
