import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

import Login from './pages/Login'
import AdminDashboard from './pages/admin/AdminDashboard'
import Students from './pages/admin/Students'
import Courses from './pages/admin/Courses'
import Enrollments from './pages/admin/Enrollments'
import StudentHome from './pages/student/StudentHome'
import BrowseCourses from './pages/student/BrowseCourses'
import Learners from './pages/student/Learners'

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/login" replace/>}/>
                <Route path="/login" element={<Login/>}/>

                {/* Admin routes - HOC handles auth + layout */}
                <Route path="/admin" element={<AdminDashboard/>}/>
                <Route path="/admin/students" element={<Students/>}/>
                <Route path="/admin/courses" element={<Courses/>}/>
                <Route path="/admin/enrollments" element={<Enrollments/>}/>

                {/* Student routes - HOC handles auth + layout */}
                <Route path="/student" element={<StudentHome/>}/>
                <Route path="/student/courses" element={<BrowseCourses/>}/>
                <Route path="/student/learners" element={<Learners/>}/>

                <Route path="*" element={<Navigate to="/login" replace/>}/>
            </Routes>
        </BrowserRouter>
    )
}
