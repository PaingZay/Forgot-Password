package com.example.WebAppApi.Service;

import org.springframework.stereotype.Service;

import com.example.WebAppApi.Model.User;
import com.example.WebAppApi.Repository.userRepository;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;


@Service
public class userServiceImpl implements userService {

    @Resource
    private userRepository UserRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        return UserRepository.saveAndFlush(user);
    }  
}
