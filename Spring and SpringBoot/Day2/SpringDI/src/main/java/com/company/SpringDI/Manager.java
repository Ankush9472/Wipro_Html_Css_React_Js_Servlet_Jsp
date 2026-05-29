package com.company.SpringDI;

import org.springframework.stereotype.Service;

import com.wipro.SpringDependencyInjection.Allocator;

@Service
public class Manager implements Allocator {

    public Manager() {
        System.out.println("Manager bean created");
    }

    @Override
    public void taskAllocation(String user) {   // matches interface
        System.out.println("Task is allocated by Manager to " + user);
    }
}