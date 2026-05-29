document.getElementById("productForm").addEventListener("submit", function(e) {
    e.preventDefault(); // prevents page reload

    let productName = document.getElementById("productName").value;
    let price = Number(document.getElementById("price").value);

    let finalPrice = price;

    if (price > 1000) {
        finalPrice = price - (price * 0.10);
    }

    else
    {
        finalPrice=price;
    }

    document.getElementById("result").innerText ="Product: " + productName +  "\nOriginal Price: " + price + "\nFinal Price: " + finalPrice;
});