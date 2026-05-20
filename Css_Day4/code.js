let employees = [];
let api = "https://jsonplaceholder.typicode.com/users";


function getEmployees() {
    fetch(api)
    .then(res => res.json())                 // fetch api data 
    .then(data => {
        employees = data;
        showData();
    });
}


function showData() {
    let rows = "";

    employees.forEach(emp => {                         // data for displaying in table
        rows += "<tr>";
        rows += "<td>" + emp.name + "</td>";
        rows += "<td>" + emp.email + "</td>";
        rows += "<td>" + (emp.company ? emp.company.name : "N/A") + "</td>";
        rows += "<td><button onclick='editEmployee(" + emp.id + ")'>Edit</button></td>";
        rows += "</tr>";
    });

    document.getElementById("data").innerHTML = rows;
}

function editEmployee(id) {

    let emp = employees.find(e => e.id === Number(id));
                                                                            // edit form
    if (!emp) return;

    document.getElementById("empId").value = emp.id;
     document.getElementById("name").value = emp.name;

    document.getElementById("email").value = emp.email;
     document.getElementById("company").value = emp.company ? emp.company.name : "";
}


function addEmployee() {
 let id = document.getElementById("empId").value;

    let name = document.getElementById("name").value;                 // update and add fu() are same but in update we will have id and in add we will not have id
     let email = document.getElementById("email").value;

    let company = document.getElementById("company").value;

    let newEmp ={
        name: name,
        email: email,
        company: { name: company }
    };

  
    if (id) {
        fetch(api + "/" + id, {                               // updating data of emp
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newEmp)
        })
        .then(res => res.json())
        .then(data => {

            let index = employees.findIndex(e => e.id === Number(id));
            employees[index] = { ...employees[index], ...newEmp };

            showData();

            clearForm();
        });

        return;
    }


    fetch(api, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"           // Adding data to api
        },
        body: JSON.stringify(newEmp)
    })
    .then(res => res.json())
    .then(data => {

        data.id = employees.length + 1;
        employees.push(data);

        showData();

        clearForm();
    });
}

// function deleteEmployee(id) {
//                                                         //Deleting the employee frm api table
//     if (!confirm("Delete this employee?")) return;

//     fetch(api + "/" + id, {
//         method: "DELETE"
//     }).then(() =>{
//         employees=employees.
//     });
// }


function clearForm() {
    document.getElementById("empId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";         // Forform cleanup
    document.getElementById("company").value = "";
}


getEmployees();