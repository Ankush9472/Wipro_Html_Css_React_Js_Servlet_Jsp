import { useRef, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const JsonLoginForm = () => {
  const usernameRef = useRef();
  const passwordRef = useRef();

  const [users, setUsers] = useState([]);
  const [errors, setErrors] = useState({});
  const [status, setStatus] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:3001/users")
      .then((res) => res.json())
      .then((data) => setUsers(data))
      .catch((err) => console.log("Error fetching users:", err));
  }, []);

  const validate = () => {
    let isValid = true;
    const errors = {};

    const username = usernameRef.current.value;
    const password = passwordRef.current.value;

    if (!username || !/^[a-zA-Z0-9]+$/.test(username)) {
      isValid = false;
      errors.username = "Enter valid username";
    }

    const passPattern =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*]).{8,}$/;

    if (!password || !passPattern.test(password)) {
      isValid = false;
      errors.password = "Password pattern incorrect";
    }

    setErrors(errors);
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validate()) {
      setStatus("Validation failed");
      return;
    }

    const username = usernameRef.current.value;
    const password = passwordRef.current.value;

    const found = users.find(
      (u) => u.username === username && u.password === password
    );

    if (found) {
      setStatus("Successfully Loggined");
      navigate("/courses", { state: { username: found.username } });
    } else {
      setStatus("Wrong password ");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Login Form (JSON API)</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "10px" }}>
          <label>Username: </label>
          <input type="text" ref={usernameRef} />
          {errors.username && (
            <span style={{ color: "red", marginLeft: "10px" }}>
              {errors.username}
            </span>
          )}
        </div>
        <div style={{ marginBottom: "10px" }}>
          <label>Password: </label>
          <input type="password" ref={passwordRef} />
          {errors.password && (
            <span style={{ color: "red", marginLeft: "10px" }}>
              {errors.password}
            </span>
          )}
        </div>
        <button type="submit">Login</button>
      </form>
      {status && <p>{status}</p>}
    </div>
  );
};

export default JsonLoginForm;