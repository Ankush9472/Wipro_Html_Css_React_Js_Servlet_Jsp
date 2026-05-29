import React from 'react'

function Login() {
  const login = () => {
    localStorage.setItem("loggedIn", true);
    alert("Logged in Succesfully")
  }

  return (
    <div style={{ padding: "20px" }}>
      <button onClick={login}>Login Here!</button>
    </div>
  )
}

export default Login