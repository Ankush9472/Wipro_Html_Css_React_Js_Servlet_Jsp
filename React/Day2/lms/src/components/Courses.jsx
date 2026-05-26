import React from 'react';

export default function Courses() {
  return (
    <div className="max-w-2xl mx-auto mt-10 px-4">
      <div className="bg-white border-2 border-gray-300 rounded-lg p-8 shadow">
        <h3 className="text-2xl font-bold text-center mb-6">Popular Courses</h3>
        
        <ul className="space-y-3 text-lg">
          <li className="bg-gray-50 p-4 border rounded">• Java Full Course</li>
          <li className="bg-gray-50 p-4 border rounded">• React JS Basics</li>
          <li className="bg-gray-50 p-4 border rounded">• Spring Boot</li>
          <li className="bg-gray-50 p-4 border rounded">• Python for Beginners</li>
        </ul>
      </div>
    </div>
  );
}