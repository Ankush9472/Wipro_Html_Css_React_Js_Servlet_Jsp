 // traditional way for incrementing the salary 
     /*   const salaries = [45000,67000 ,36000];
        document.writeln(salaries[0]);
        const newsalaries =[]; //new empty array to store the new salaries after increment
        for(let i=0; i<salaries.length ;i++)
        {
            newsalaries.push(salaries[i] + 1000)
        }
        document.writeln("New Salary using traditional method :" + newsalaries);
        // to filter the salaries greater than 50000
        const highlypaidemployees = [];
        for(let i=0 ; i<salaries.length ; i++)
        {
            if(salaries[i] > 20000)
            highlypaidemployees.push(salaries[i]);
        }
        document.writeln("Highly paid employees using traditional method :" + highlypaidemployees);
        
        // to calculate total salary of employees using traditional method
        let totalSalary = 0;
         for(let i=0 ; i<salaries.length ; i++)
         {
            totalSalary += salaries[i];
         }
         document.writeln("Total salary of employees using traditional method :" + totalSalary);


*/


         const salaries = [45000, 67000, 36000,2000,19000];


const incrementSalary = (arr) => arr.map(salary => salary + 1000);                // salary increasing using map

const newSalary = incrementSalary(salaries);
document.writeln("New Salary using map fn(): " + newSalary);


const HighSalary = (arr) => arr.filter(salary => salary > 20000);      // Filtering high paying jobs using filter fn();
const highSalEmp = HighSalary(salaries);                        // creating new variable to store data
document.writeln("Highly paid employees using filter fn(): " + highSalEmp);


const calculateTotalSalary = (arr) => 
    arr.reduce((total, salary) => total + salary, 0);                  // Calculating total Salary using reduce fn()

 
const totalSalary = calculateTotalSalary(salaries);
document.writeln("Total salary using total fn(): " + totalSalary);