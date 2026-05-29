import React from 'react'
import { Link } from 'react-router-dom'

function Courses() {
  return (
    <div className="container">
      <h2>Courses</h2>
      <ul>
        <li><Link to='/courses/1'>React Course</Link></li>
        <li><Link to='/courses/2'>Java Course</Link></li>
      </ul>
    </div>
  )
}

export default Courses