import React from 'react'
import { useParams } from 'react-router-dom'

function CourseDetails() {
  const { id } = useParams();

  return (
    <div className="container">
      <h3>Course Details :</h3>
      <p>Course ID : {id}</p>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
        Harum sit pariatur vel dolorum. Ex aliquam error alias,
        eius sapiente maiores eveniet ab et laudantium omnis enim labore debitis consequuntur nam.</p>
    </div>
  )
}

export default CourseDetails