import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import ProductChild from "./components/ProductChild";

function App() {

  // Product Object
  const product = {
    id: 1,
    name: "Laptop",
    price: 50000,
    brand: "HP"
  };

   const product2 = {
    id: 2,
    name: "Smart-Phone",
    price: 20000,
    brand: "Samsung"
  };

  return (
    <>
      <HeaderComponent />

      <h2>Parent Component</h2>

      <ProductChild productData={product} />
      <ProductChild productData={product2}/>

      <FooterComponent />
    </>
  );
}

export default App;