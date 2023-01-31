package com.example.WebAppApi.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.WebAppApi.Repository.BusinessRepository;



@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessRepository businessRepository;
}