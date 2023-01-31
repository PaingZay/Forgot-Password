package com.example.WebAppClient.Service;

import java.util.List;

import com.example.WebAppClient.Model.User;

public interface UserService {

    List<User> getUserList ();
    User getUserbyId (int id);
}
