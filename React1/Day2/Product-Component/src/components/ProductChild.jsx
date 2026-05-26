function ProductChild(props) {

  return (
    <>
      <h2>Child Component</h2>

      <h3>Product Details</h3>

      <p>ID: {props.productData.id}</p>
      <p>Name: {props.productData.name}</p>
      <p>Price: {props.productData.price}</p>
      <p>Brand: {props.productData.brand}</p>
    </>
  );
}

export default ProductChild;