package com.example.WebAppApi.Service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.WebAppApi.Model.User;
import com.example.WebAppApi.Repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository UserRepository;

    @Override
    public User createUser(User user) {
        return UserRepository.saveAndFlush(user);
    }  

    @Override
    public List<User> getUserList() {
        return UserRepository.findAll();
    }

    @Override 
    public Optional<User> getUserbyId (int id) {
        return UserRepository.findById(id);
    }
}
