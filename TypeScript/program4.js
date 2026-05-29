"use strict";
let userMap = new Map();
userMap.set(101, "Ankush");
userMap.set(102, "Jiya");
userMap.set(103, "Kamal");
console.log(userMap.get(101));
let items = ["laptop", "mouse", "Keyboard"];
for (let item of items) {
    console.log("The values in an array " + item);
}
let userData = {
    id: 101,
    name: "Ankush",
    salary: 65757,
    display() {
        return this.salary + this.salary * 0.01;
    }
};
console.log("id", userData.id);
console.log("name", userData.name);
console.log("salary", userData.salary);
console.log("increment", userData.display());
class Hr {
    basicsalary;
    constructor(basicsalary) {
        this.basicsalary = basicsalary;
    }
    display() {
        console.log("Salary", this.basicsalary);
    }
}
let obj1 = new Hr(6575);
obj1.display();
