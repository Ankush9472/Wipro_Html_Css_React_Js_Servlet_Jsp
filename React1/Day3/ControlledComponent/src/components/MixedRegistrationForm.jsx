import { useRef, useState } from "react";

const MixedRegistrationForm = () => {
  const firstNameRef = useRef();
  const lastNameRef = useRef();
  const emailRef = useRef();
  const passwordRef = useRef();

  const [errors, setErrors] = useState({});
  const [status, setStatus] = useState("");

  const validate = () => {
    let isValid = true;
    const errors = {};

    const firstName = firstNameRef.current.value;
    const lastName = lastNameRef.current.value;
    const email = emailRef.current.value;
    const password = passwordRef.current.value;

    if (!firstName || !/^[a-zA-Z]+$/.test(firstName)) {
      isValid = false;
      errors.firstName = "Enter valid name";
    }

    if (!lastName || !/^[a-zA-Z]+$/.test(lastName)) {
      isValid = false;
      errors.lastName = "Enter valid name";
    }

    const emailPattern =
      /^[a-z][a-zA-Z0-9_]*(\.[a-zA-Z][a-zA-Z0-9_]*)*@([a-z][a-zA-Z0-9-]*)\.[a-z]+(\.[a-z]+)?$/;

    if (!email || !emailPattern.test(email)) {
      isValid = false;
      errors.email = "add @gmail.com in last";
    }

    const passPattern =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*]).{8,}$/;

    if (!password || !passPattern.test(password)) {
      isValid = false;
      errors.password = "USe Uppercase, lowerCase, number, special char in password";
    }

    setErrors(errors);
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      setStatus("Registration successful");
      firstNameRef.current.value = "";
      lastNameRef.current.value = "";
      emailRef.current.value = "";
      passwordRef.current.value = "";
    } else {
      setStatus("Registration failed");
    }
  };

  return (
    <div>
      <h2>Mixed Registration Form</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>First Name: </label>
          <input type="text" ref={firstNameRef} />
          {errors.firstName && <span style={{ color: "red" }}>{errors.firstName}</span>}
        </div>
        <div>
          <label>Last Name: </label>
          <input type="text" ref={lastNameRef} />
          {errors.lastName && <span style={{ color: "red" }}>{errors.lastName}</span>}
        </div>
        <div>
          <label>Email: </label>
          <input type="text" ref={emailRef} />
          {errors.email && <span style={{ color: "red" }}>{errors.email}</span>}
        </div>
        <div>
          <label>Password: </label>
          <input type="password" ref={passwordRef} />
          {errors.password && <span style={{ color: "red" }}>{errors.password}</span>}
        </div>
        <button type="submit">Register</button>
      </form>
      {status && <p>{status}</p>}
    </div>
  );
};

export default MixedRegistrationForm;