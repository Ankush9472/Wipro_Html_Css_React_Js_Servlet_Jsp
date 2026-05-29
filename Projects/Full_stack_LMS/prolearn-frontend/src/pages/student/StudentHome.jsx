import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { mock } from '../../api/mock'
import { getUser } from '../../api/auth'

function StudentHome() {
    const [allCourses, setAllCourses] = useState([])
    const [myCourses, setMyCourses] = useState([])
    const [learners, setLearners] = useState([])
    const u = getUser()

    useEffect(() => {
        run()
    }, [])

    const run = async () => {
        try {
            const a = await mock.listCourses()
            setAllCourses(a)
            const b = await mock.listStudents()
            setLearners(b)
            const c = await mock.myCourses()
            setMyCourses(c)
        } catch (e) {
            console.log(e)
        }
    }

    let cnt = 0
    for (const x of learners) {
        cnt += x.courses?.length || 0
    }

    return (
        <div>
            <div className="page-header">
                <div>
                    <div className="page-title">HII, {u.fullName}</div>
                    <div className="page-subtitle">
                        Welcome to ProLearn{u.email ? ' · ' + u.email : ''}
                    </div>
                </div>
            </div>

            {myCourses.length === 0 && (
                <div className="alert alert-error">
                    <strong>You are not enrolled in any course yet.</strong> Please contact your admin to enroll you. If your email is not registered, ask admin to add you first.
                </div>
            )}

            <div className="stats">
                <div className="stat-box">
                    <div className="stat-num">{myCourses.length}</div>
                    <div className="stat-label">My Courses</div>
                </div>
                <div className="stat-box orange">
                    <div className="stat-num">{allCourses.length}</div>
                    <div className="stat-label">Total Available</div>
                </div>
                <div className="stat-box blue">
                    <div className="stat-num">{learners.length}</div>
                    <div className="stat-label">Total Learners</div>
                </div>
            </div>

            <div className="box">
                <div className="box-title">My Enrolled Courses</div>
                {myCourses.length === 0 ? (
                    <div className="empty">
                        <p>No courses enrolled yet.</p>
                        <Link to="/student/courses" className="btn btn-orange" style={{ marginTop: 10 }}>
                            Browse Available Courses
                        </Link>
                    </div>
                ) : (
                    <>
                        <table className="tbl">
                            <thead>
                            <tr>
                                <th>Code</th>
                                <th>Title</th>
                                <th>Category</th>
                                <th>Instructor</th>
                                <th>Duration</th>
                                <th>Access</th>
                            </tr>
                            </thead>
                            <tbody>
                            {myCourses.map((c) => (
                                <tr key={c.id}>
                                    <td><strong>{c.code}</strong></td>
                                    <td>{c.title}</td>
                                    <td><span className="tag tag-orange">{c.category}</span></td>
                                    <td>{c.instructor}</td>
                                    <td>{c.duration}</td>
                                    <td>
                                        {c.courseLink ? (
                                            <a href={c.courseLink} target="_blank" rel="noreferrer"
                                               className="btn btn-orange btn-small">
                                                Watch Now ↗
                                            </a>
                                        ) : (
                                            <span style={{ color: '#999', fontSize: 12 }}>No link</span>
                                        )}
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                        <div style={{ textAlign: 'center', marginTop: 14 }}>
                            <Link to="/student/courses" className="btn btn-orange">Browse All Courses →</Link>
                        </div>
                    </>
                )}
            </div>
        </div>
    )
}

import withAuth from '../../hoc/withAuth'
import withLayout from '../../hoc/withLayout'

export default withAuth(withLayout(StudentHome))