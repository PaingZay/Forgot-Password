package com.example.WebAppApi.Service;

import org.springframework.stereotype.Service;

import com.example.WebAppApi.Model.User;
import com.example.WebAppApi.Repository.UserRepository;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository UserRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        return UserRepository.saveAndFlush(user);
    }  
}
