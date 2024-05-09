package com.example.thesisbackend.service.user.manage;

import com.example.thesisbackend.pojo.User;

import java.util.List;

public interface GetUsersService {
    List<User> getAllUsers(int authority);

}
