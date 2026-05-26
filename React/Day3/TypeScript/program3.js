"use strict";
var Role;
(function (Role) {
    Role[Role["Admin"] = 0] = "Admin";
    Role[Role["User"] = 1] = "User";
    Role[Role["GuestUser"] = 2] = "GuestUser";
})(Role || (Role = {}));
let userRole = Role.Admin;
console.log("Role:", userRole);
let marks = [45, 67, 34, 78];
console.log("Marks:", marks);
let user = [101, "Niti"];
console.log("User:", user);
let bookcode = [1001, "Java"];
bookcode.push(1002, ".Net");
bookcode.pop();
console.log("Bookcode:", bookcode);
