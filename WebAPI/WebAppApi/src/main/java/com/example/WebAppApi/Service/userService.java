package com.example.WebAppApi.Service;

import java.util.List;
import java.util.Optional;

import com.example.WebAppApi.Model.User;



public interface UserService {

    User createUser (User user);
    List<User> getUserList ();
    Optional<User> getUserbyId (int id);
}
