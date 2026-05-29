enum Role {
  Admin,
  User,
  GuestUser
}

let userRole: Role = Role.Admin;
console.log("Role:", userRole);

let marks: number[] = [45, 67, 34, 78];
console.log("Marks:", marks);

let user: [number, string] = [101, "Niti"];
console.log("User:", user);

let bookcode: [number, string] = [1001, "Java"];
bookcode.push(1002, ".Net");
bookcode.pop();
console.log("Bookcode:", bookcode);