package com.configurationstyle;

import org.springframework.stereotype.Component;

@Component
class Manager implements Allocator {
    public Manager() {
        System.out.println("Manager bean created");
    }

    @Override
    public void taskAllocation(String user) {
        System.out.println("Task is allocated by Manager to " + user);
    }
}