package com.test.nti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nti.repositories.ClassRepository;

@Service
public class ClassService {
    
    @Autowired
    private ClassRepository classRepository;

    
}
