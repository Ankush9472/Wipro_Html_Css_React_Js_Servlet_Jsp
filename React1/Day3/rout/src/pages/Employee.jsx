import React from 'react'

function Employee() {
  const arr = [
    { id: 1, n: "John", r: "Developer" },
    { id: 2, n: "Jane", r: "Designer" },
    { id: 3, n: "Mike", r: "Manager" }
  ];

  return (
    <div style={{ padding: "20px" }}>
      <h1>Employees</h1>
      <ul>
        {arr.map(x => (
          <li key={x.id}>{x.n} - {x.r}</li>
        ))}
      </ul>
    </div>
  )
}

export default Employee