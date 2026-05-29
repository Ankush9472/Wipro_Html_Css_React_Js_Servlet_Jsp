let userMap = new Map<number, String>();
userMap.set(101, "Ankush");
userMap.set(102, "Jiya");
userMap.set(103, "Kamal");
console.log(userMap.get(101));

let items = ["laptop", "mouse", "Keyboard"];
for (let item of items) {
  console.log("The values in an array " + item);
}

interface users {
  id: number;
  name: string;
  salary: number;
  display(): number;
}

let userData: users = {
  id: 101,
  name: "Ankush",
  salary: 65757,
  display(): number {
    return this.salary + this.salary * 0.01;
  }
};

console.log("id", userData.id);
console.log("name", userData.name);
console.log("salary", userData.salary);
console.log("increment", userData.display());

interface salary {
  basicsalary: number;
  display(): void;
}

class Hr implements salary {
  constructor(public basicsalary: number) {}

  display() {
    console.log("Salary", this.basicsalary);
  }
}

let obj1 = new Hr(6575);
obj1.display();