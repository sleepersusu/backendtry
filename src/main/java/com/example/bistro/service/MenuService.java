package com.example.bistro.service;

import com.example.bistro.model.MenuRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepositoryDao menuRepository;

}
