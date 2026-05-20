What is JavaScript ?
JavaScript is a high-level, interpreted programming language that allows you to make web pages
 interactive and dynamic. 
 Alongside HTML and CSS, it is one of the core technologies of web development.
	• HTML → Builds the structure of a webpage (headings, paragraphs, buttons).
	• CSS → Adds style and design (colors, fonts, layouts).
	• JavaScript → Adds behavior and interactivity (clicks, animations, live updates).
Who Created JavaScript?
	• Originally named Mocha, then LiveScript, and finally renamed to JavaScript.
	• Amazingly, the first version was developed in just 10 days.
	• Today, JavaScript is maintained by ECMA International under the standard ECMAScript (ES).
Latest versions (ES6 and beyond) introduced features like let, const, arrow functions, promises, and classes — making JavaScript more powerful and modern.
Why Use JavaScript?
JavaScript is used everywhere — from simple web pages to large-scale applications.
Here’s what you can do with it:
	1. Interactive Web Pages – Respond to user actions like clicks, hover, or form submissions.
	2. DOM Manipulation – Dynamically change HTML content or CSS styles.
	3. Web Applications – Build apps like Gmail, Facebook, or e-commerce websites.
	4. Server-Side Development – Use Node.js for backend tasks like APIs and databases.
	5. Games and Animations – Create browser games and animations.
Event Handling – React to keyboard, mouse, or page events.

Javascript keywords &  reserved words
break, continue, if else, throw
void , class , for , try , cons, let , var 

identifiers : names you are giving to a variable ,
             function , classes etc.

const  - fixed value

student = []

variable names use camelCase, for eg rollNo
to declare the variable we can either let or var
 
 generally we prefer to use let keyword to declare a variable

 let var1  =10;
 const pi = 3.14;


 a company wants to automate monthly salary calculation for employees based on their working days, basic pay and bonus
 . create a script file to 
 a) calculate gross salary
 b) apply tax deduction 
 c) display employee net salary 

DOM  stands for document object model
The DOM is a programming interface that allows js to access , change add or remove html elements on a webpage
HTML page is converted into a tree structure that java script can control

HTML is a static (all alone we cannot change by itself)

________________________________________________________________________________

Functional Programming and Pure Functions.

What is pure functions  ---    where we focus on what to do rather then how to do 
traditional functions --- like for , foreach --
 here we were focussing on what to do but also on how to do (logic)


Pure functions are like : - map(), reduce() and filter()

map() -> when we want to transform each element in an array and it return a new array.

filter() -- when we want to select specific elements that meet a condition or when we want to extract the data 

reduce() -- when we want to return a single value after accumulating array values that we have or we want to 
summarize or to get aggregate data  (summation =sum(a1 :d10) this is aggregate function to get total sum in excel)

== We want to calculate the total sum of 10 natural numbers  sum += sum +n
(sum ,p) = sum + p.price , 0


Syntaxes :

map()
array.map((element , index(operation) ,  array) => {return newValue});

for eg: 

let number = [3,6,7,8]

let doubling = number.map(num  => num*2);

step 1 :  3  3*2  [6]
          6  6*2  [6,12]
		  7  7*2  [6,12,14]
		  8  8*2  [6,12,14,16]
Note *  it will return the array of same size

filter()
array.filter((element , index(operation) ,  array) => {return condition});

let number = [1,2,3,4,5]
let even = number.filter(num => num%2 == 0)

steps : 1  false  []
        2  true   [2]
		3  false  [2]
		4  true   [2,4] 
		5  false  [2,4]

Note*  it will return filtered array (size may vary,  may be less in size or equal)

reduce()
array.reduce((accumulator, currentvalue/element , index , array) =>{return updatedaccumulator;},initialValue);

number.reduce((sum ,num ) => sum + num ,0);

steps   0  1  0+1  1
        1  2  1+2  3
		3  3  3+3  6
		6  4  6+4  10
        10 5  10+5 15

Note *  It will return the updated single value 
________________________________________________________________________________

Data is of 2 type : structured data & unstructured data 
data will imported or exported in different formats (csv , xml , excel  ,mysql ,json)
restful application ( json data / csv , xml )  soap web services (xml) wsdl 

JSON -- It is a lightweight data- interchange format used to store and exchange data between client and Server
though there are different other sources too -- xml , csv ,mysql

format of json   :   "key":"stringvalue"  or    "key":numbervalue

JSON.parse(jsonString) -- converts json string to js object -- mainly we use when receiving the data from a server
JSON.stringify(obj) -- converts js object to json string -- mainly used when sending data to a server.



Modern approach syntaxes 
1 . Normal Function
	function add(a,b) {return a+b ;}

2.  Function based expression
    const addition = function(a,b){return a+b};

3.  Arrow Function 
    const addition = (a,b) => a+b ; 

interpolation :
let a= 20;
let b=30;
console.log('sum of two number i.e. ${a} and ${b}  is : ${a+b}');


<body>
<p id="value"></p>
</body>

<script>
let product ="Laptop"
let price = 40000;

document.getElementByID("value").innerHtml = '<h2>${product}</h2><b> Price : Rupees ${price} <b>';

</script>




Asynchronous / Synchronous Based:

Sysnchronous --  when one execution will get completed then the other execution will take place --- Blocker 
               line by line , if there is any blocker then in that case the rest of the code will not work  

			   it is slow in process

			   XMLHttpRequest(sync)

Asynchronous -- It's a non-blocker , next code runs immediately ---
We can implement asynchronous using
fetch() , or using new Promise(resolve , reject) and fetch , async/await , XMLHttpRequest(async)

basically fetch() will return a Promise Object and 
		 .then() will be called on that Promise object when it's fulfilled ( means data is received) 
		 .catch() will run if the Promise is rejected(due to wrong api , network error)


fetch is a built-in api that return a promise object for http calls and new Promise() is used to manually create and then 
returning an object


before using arrow function we were using a normal function 


function show()            jsonData.forEach(function(product ,index){ document.write(product , index)  })
{

	return "sdsflj";
}

(a,b) => a+b;
() => console.log("djffsd");
jsonData.forEach((product, index) => {
            const li = document.createElement("li");
            li.textContent = `ID: ${index} | Item: ${product.item} | Price: ₹${product.price}`;
            ul.appendChild(li);
        });



























