import React from 'react'

function Courses() {
  const arr = ["React", "Java", "Spring Boot", "SQL"];

  return (
    <div style={{ padding: "20px" }}>
      <h1>Courses</h1>
      <ul>
        {arr.map((s, i) => (
          <li key={i}>{s}</li>
        ))}
      </ul>
    </div>
  )
}

export default Courses