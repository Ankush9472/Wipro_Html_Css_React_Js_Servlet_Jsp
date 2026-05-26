import React from 'react';

export default function Navbar() {
  return (
    <nav className="bg-blue-700 text-white shadow-md sticky top-0 z-50">
      <div className="max-w-5xl mx-auto px-6">
        <div className="flex justify-between items-center h-16">
          
          <div className="flex items-center gap-3">
            <div className="w-9 h-9 bg-white text-blue-700 rounded-lg flex items-center justify-center font-bold text-2xl">
              L
            </div>
            <h1 className="text-2xl font-bold">Your LMS Learing Portal</h1>
          </div>

          <div className="hidden md:flex items-center gap-8 text-base">
            <a href="#" className="hover:bg-blue-600 px-4 py-2 rounded transition">Home</a>
            <a href="#" className="hover:bg-blue-600 px-4 py-2 rounded transition">Courses</a>
            <a href="#" className="hover:bg-blue-600 px-4 py-2 rounded transition">Students</a>
            <a href="#" className="hover:bg-blue-600 px-4 py-2 rounded transition">About</a>
            <a href="#" className="hover:bg-blue-600 px-4 py-2 rounded transition">Contact</a>
          </div>

          <div className="flex items-center gap-3">
            <button className="px-6 py-2 hover:bg-blue-600 rounded transition">Login</button>
            <button className="bg-white text-blue-700 px-6 py-2 rounded font-medium hover:bg-gray-100">
              Register
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
}