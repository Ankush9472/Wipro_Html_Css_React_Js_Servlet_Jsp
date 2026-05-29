class Employee {
  id: number;
  name: string;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}

let emp: Employee[] = [];

emp.push(new Employee(1, "Raju"));
emp.push(new Employee(2, "John"));              // adding ele in array
emp.push(new Employee(3, "Priya"));

console.log("After adding:");
for (let i = 0; i < emp.length; i++) {                     //adding here
  console.log(emp[i].id + " " + emp[i].name);
}

for (let i = 0; i < emp.length; i++) {
  if (emp[i].id == 2) {
    emp[i].name = "John Smith";
  }
}

console.log("After update:");
for (let i = 0; i < emp.length; i++) {                // updating 
  console.log(emp[i].id + " " + emp[i].name);
}

for (let i = 0; i < emp.length; i++) {
  if (emp[i].id == 1) {
    emp.splice(i, 1);
  }
}

console.log("After remove:");
for (let i = 0; i < emp.length; i++) {
  console.log(emp[i].id + " " + emp[i].name);
}