// Taking input from user
let userName = prompt("Enter user name");
let age = prompt("Enter your age");
let isSubscribed = "true";

// Function to validate form data
function validateForm(userName, age, isSubscribed) {

    // Check original data types
    let userNameType = typeof userName;
    let ageType = typeof age;
    let isSubscribedType = typeof isSubscribed;

    console.log("Before Conversion:");
    console.log("User Name: " + userName + " | Data type: " + userNameType);
    console.log("Age: " + age + " | Data type: " + ageType);
    console.log("isSubscribed: " + isSubscribed + " | Data type: " + isSubscribedType);

    // Conversion
    age = Number(age);
    isSubscribed = (isSubscribed === "true");

    console.log("After Conversion:");
    console.log("User Name: " + userName + " | Data type: " + typeof userName);
    console.log("Age: " + age + " | Data type: " + typeof age);
    console.log("isSubscribed: " + isSubscribed + " | Data type: " + typeof isSubscribed);
}

// Call function
validateForm(userName, age, isSubscribed);