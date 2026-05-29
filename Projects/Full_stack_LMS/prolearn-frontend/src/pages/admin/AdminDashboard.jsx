import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { mock } from '../../api/mock'
import { getUser } from '../../api/auth'

function AdminDashboard() {
    const [s, setS] = useState({ totalStudents: 0, totalCourses: 0, totalEnrollments: 0 })
    const [arr, setArr] = useState([])
    const [nums, setNums] = useState([])
    const u = getUser()

    useEffect(() => {
        run()
    }, [])

    const run = async () => {
        try {
            const a = await mock.stats()
            setS(a)
            const b = await mock.listCourses()
            setArr(b.slice(0, 5))
            const c = await mock.listStudents()
            setNums(c.slice(0, 5))
        } catch (e) {
            console.log(e)
        }
    }

    return (
        <div>
            <div className="page-header">
                <div>
                    <div className="page-title">Welcome, {u.fullName}</div>
                    <div className="page-subtitle">Admin control panel - manage your LMS</div>
                </div>
            </div>

            <div className="stats">
                <div className="stat-box">
                    <div className="stat-num">{s.totalStudents}</div>
                    <div className="stat-label">Total Students</div>
                </div>
                <div className="stat-box orange">
                    <div className="stat-num">{s.totalCourses}</div>
                    <div className="stat-label">Total Courses</div>
                </div>
                <div className="stat-box blue">
                    <div className="stat-num">{s.totalEnrollments}</div>
                    <div className="stat-label">Active Enrollments</div>
                </div>
            </div>

            <div className="box">
                <div className="box-title">Quick Actions</div>
                <Link to="/admin/students" className="btn btn-primary" style={{ marginRight: 8 }}>
                    Manage Students
                </Link>
                <Link to="/admin/courses" className="btn btn-orange" style={{ marginRight: 8 }}>
                    Manage Courses
                </Link>
                <Link to="/admin/enrollments" className="btn btn-primary">
                    Manage Enrollments
                </Link>
            </div>

            <div className="box">
                <div className="box-title">Recent Courses</div>
                {arr.length === 0 ? (
                    <div className="empty">No courses found</div>
                ) : (
                    <table className="tbl">
                        <thead>
                        <tr>
                            <th>Code</th>
                            <th>Title</th>
                            <th>Instructor</th>
                            <th>Category</th>
                            <th>Students</th>
                        </tr>
                        </thead>
                        <tbody>
                        {arr.map((c) => (
                            <tr key={c.id}>
                                <td><strong>{c.code}</strong></td>
                                <td>{c.title}</td>
                                <td>{c.instructor}</td>
                                <td><span className="tag tag-orange">{c.category}</span></td>
                                <td>{c.studentCount}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>

            <div className="box">
                <div className="box-title">Recent Students</div>
                {nums.length === 0 ? (
                    <div className="empty">No students found</div>
                ) : (
                    <table className="tbl">
                        <thead>
                        <tr>
                            <th>Roll No</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>City</th>
                            <th>Enrolled In</th>
                        </tr>
                        </thead>
                        <tbody>
                        {nums.map((st) => (
                            <tr key={st.id}>
                                <td><strong>{st.rollNumber}</strong></td>
                                <td>{st.name}</td>
                                <td>{st.email}</td>
                                <td>{st.city}</td>
                                <td><span className="tag tag-green">{st.courses?.length || 0} courses</span></td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    )
}

import withAuth from '../../hoc/withAuth'
import withLayout from '../../hoc/withLayout'

export default withAuth(withLayout(AdminDashboard), true)