package com.configurationstyle;

public class DelegateXmlBased {

    private final Allocator allocator;

    public DelegateXmlBased(Allocator allocator) {
        this.allocator = allocator;
        System.out.println("Inside delegate constructor - Allocator " 
                           + allocator.getClass().getSimpleName());
    }

    public void notifyUser() {
        allocator.taskAllocation("Ankush");
    }
}