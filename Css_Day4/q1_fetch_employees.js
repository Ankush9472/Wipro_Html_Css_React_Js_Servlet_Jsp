// Week 4 - Day 3
// Q1: Fetch data from a JSON endpoint and display in console

// using the dummy employees API
const url = "https://dummy.restapiexample.com/api/v1/employees";

// fetch returns a promise so we use .then()
fetch(url)
    .then((response) => {
        // first check if request was ok
        if (!response.ok) {
            throw new Error("Something went wrong! Status: " + response.status);
        }
        return response.json();   // convert response to json
    })
    .then((data) => {
        console.log("Data received from API:");
        console.log(data);

        // also loop through and print each employee nicely
        // (the actual employee list is inside data.data)
        console.log("\n----- Employee List -----");
        data.data.forEach((emp, i) => {
            console.log(`${i + 1}. ${emp.employee_name} - Age: ${emp.employee_age} - Salary: ${emp.employee_salary}`);
        });
    })
    .catch((error) => {
        console.log("Error fetching data:", error);
    });


// ---- alternate version using async/await (commented out) ----

// async function getEmployees() {
//     try {
//         let res = await fetch(url);
//         let data = await res.json();
//         console.log(data);
//     } catch (err) {
//         console.log("Error:", err);
//     }
// }
// getEmployees();
