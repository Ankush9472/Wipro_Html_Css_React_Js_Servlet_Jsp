function calculateSalary() {

    var name = document.getElementById("empName").value;
    var basic = document.getElementById("basicSalary").value;
    var days = document.getElementById("workingDays").value;
    var bonus = document.getElementById("bonus").value;

    basic = Number(basic);
    days = Number(days);
    bonus = Number(bonus);

    var perDay = basic / 30;
    var earned = perDay * days;
    var gross = earned + bonus;

    var tax = 0;

    if (gross <= 25000) {
        tax = gross * 0.05;
    } else {
        tax = gross * 0.10;
    }

    var net = gross - tax;

    document.getElementById("result").innerHTML =
        "Name: " + name + "<br>" +
        "Gross Salary: ₹" + gross + "<br>" +
        "Tax: ₹" + tax + "<br>" +
        "Net Salary: ₹" + net;
}