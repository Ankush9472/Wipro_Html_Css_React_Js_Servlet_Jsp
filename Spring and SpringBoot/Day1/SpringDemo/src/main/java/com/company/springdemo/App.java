package com.company.springdemo;

class Manager {
    public void taskAllocation(String user) {
        System.out.println("Task is allocated by : Manager to " + user);
    }
}

class TeamLead {
    public void taskAllocation(String user) {
        System.out.println("Task is allocated by : Team Lead to " + user);
    }
}

// Tight Coupling Code in Core Java
/* Why it is tight coupling:
 * - Delegate decides to use Manager (or TeamLead)
 * - If tomorrow you replace Manager with TeamLead, you must change Delegate class
 * - Delegate cannot work without Manager — it depends on a concrete class
 * - Creating concrete objects gives full control to developer which is not abstract
 */
class Delegate {

    // private Manager manager = new Manager();   // hard-coded dependency
    private TeamLead teamlead = new TeamLead();   // manually injecting the object

    public void notifyUser() {
        // manager.taskAllocation("Niti");
        teamlead.taskAllocation("Niti");
    }
}

public class App {
    public static void main(String[] args) {
        Delegate d = new Delegate();   // concrete class
        d.notifyUser();
    }
}