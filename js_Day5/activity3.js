const cart = [
  { id: 1, name: "Laptop", price: 60000, qty: 2, category: "Electronics" },
  { id: 2, name: "Mouse", price: 500, qty: 5, category: "Electronics" },
  { id: 3, name: "Keyboard", price: 1000, qty: 3, category: "Electronics" },
  { id: 4, name: "Monitor", price: 15000, qty: 2, category: "Electronics" }
];

const productNames = cart.map((item) => {
  return item.name;
});

console.log("Product Names:");                                // Using Map fn() to print all names
productNames.forEach((name) => {
  console.log(name);
});



                                                           // Finding all price of prduct using reducr fn()

const totalPrice = cart.reduce((total, item) => {
  const itemTotal = item.price * item.qty;
  return total + itemTotal;
}, 0);

             
