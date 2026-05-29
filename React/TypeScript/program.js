"use strict";
class Student {
    name;
    age;
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
    display() {
        console.log(`Student Name: ${this.name}, Age: ${this.age}`);
    }
}
let obj = new Student("Ankush", 23);
obj.display();
