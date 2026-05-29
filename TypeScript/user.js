"use strict";
class User {
    user = "";
    password = "";
    input(u, p) {
        this.user = u;
        this.password = p;
    }
    output() {
        console.log("Username: " + this.user);
        console.log("Password: " + this.password);
    }
}
let u1 = new User();
u1.input("admin", "Admin@123");
u1.output();
