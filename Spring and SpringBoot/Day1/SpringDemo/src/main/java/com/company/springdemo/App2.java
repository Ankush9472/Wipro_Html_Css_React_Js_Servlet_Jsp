package com.company.springdemo;

interface Allocator {
    void taskAllocation(String user);
}

class Manager1 implements Allocator {
    public void taskAllocation(String user) {
        System.out.println("Task is allocated by : Manager to " + user);
    }
}

class TeamLead1 implements Allocator {
    public void taskAllocation(String user) {
        System.out.println("Task is allocated by : Team Lead to " + user);
    }
}

// Handling the Tight Coupling in Core Java by using Interface to make it Loose Coupling
class Delegate1 {

    // Here delegate depends upon interface only
    private Allocator allocator;   // depends on abstraction

    // Dependency provided from outside (constructor injection)
    // Makes Delegate1 independent of concrete classes (Manager1, TeamLead1)
    public Delegate1(Allocator allocator) {
        super();
        this.allocator = allocator;
    }

    public void notifyUser() {
        allocator.taskAllocation("Niti");
    }
}

public class App2 {
    public static void main(String[] args) {
        Delegate1 delegate = new Delegate1(new Manager1());     // pass Manager1
        delegate.notifyUser();

        Delegate1 delegate2 = new Delegate1(new TeamLead1());   // pass TeamLead1
        delegate2.notifyUser();
    }
}

// Still NOT fully loose coupling — developer has to manually wire dependencies
// (deciding which implementation to use). Code change is still required.