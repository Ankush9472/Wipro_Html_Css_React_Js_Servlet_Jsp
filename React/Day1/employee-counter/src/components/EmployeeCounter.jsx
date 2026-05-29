import { useState } from "react";
import "./EmployeeCounter.css";

function EmployeeCounter() {

  const [count, setCount] = useState(10);

  const addEmployee = () => {
    setCount(count + 1);
  };

  const removeEmployee = () => {
  
       setCount(count - 1);
    
  };

  return (
    <div className="main-container">

      <div className="counter-card">

     <h1 style={{ color: "black" }}>Employee Count</h1>

        <p style={{ color: "black" }}>The value of counter variable is :</p>

        <h2>{count}</h2>

        <div className="button-group">

          <button onClick={addEmployee}>
            Add <br /> Employee
          </button>

          <button onClick={removeEmployee}>
            Removing <br /> Employee
          </button>

        </div>

      </div>

    </div>
  );
}

export default EmployeeCounter;