import React from 'react';

export default function Hero() {

  const exploreCourses = () => alert("Showing all courses...");
  const registerHere = () => alert("Registration form would open here...");

  return (
    <div className="max-w-2xl mx-auto mt-12 px-4">
      <div className="bg-white border-2 border-gray-300 rounded-lg p-10 text-center shadow">
        <h2 className="text-4xl font-bold text-blue-600 mb-4">
          Welcome To LMS Portal
        </h2>
        <p className="text-gray-600 text-lg mb-8">
          Learn Java, React, Spring Boot and more.
        </p>

        <div className="flex flex-col sm:flex-row gap-4 justify-center">
          <button 
            onClick={exploreCourses}
            className="bg-green-500 hover:bg-green-600 text-white px-8 py-3 rounded text-lg font-medium"
          >
            Explore Courses
          </button>
          
          <button 
            onClick={registerHere}
            className="bg-yellow-500 hover:bg-yellow-600 text-black px-8 py-3 rounded text-lg font-medium"
          >
            Register Here
          </button>
        </div>
      </div>
    </div>
  );
}