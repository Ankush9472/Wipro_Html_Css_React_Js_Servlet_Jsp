import { Link, NavLink, useNavigate } from 'react-router-dom'
import { doLogout, getUser, isAdmin } from '../api/auth'

// HOC - wraps a component with the top navbar + page layout
export default function withLayout(Wrapped) {
    return function fun(props) {
        const nav = useNavigate()
        const u = getUser()
        const admin = isAdmin()

        const m1 = () => {
            doLogout()
            nav('/login')
        }

        return (
            <div>
                <div className="topbar">
                    <Link to={admin ? '/admin' : '/student'} className="topbar-logo">
                        <span className="logo-circle">P</span>
                        <span>ProLearn</span>
                    </Link>

                    <nav className="topbar-nav">
                        {admin ? (
                            <>
                                <NavLink to="/admin" end>Dashboard</NavLink>
                                <NavLink to="/admin/students">Students</NavLink>
                                <NavLink to="/admin/courses">Courses</NavLink>
                                <NavLink to="/admin/enrollments">Enrollments</NavLink>
                            </>
                        ) : (
                            <>
                                <NavLink to="/student" end>Home</NavLink>
                                <NavLink to="/student/courses">Browse Courses</NavLink>
                                <NavLink to="/student/learners">All Learners</NavLink>
                            </>
                        )}
                    </nav>

                    <div className="topbar-right">
                        <div className="topbar-user">
                            <div className="topbar-user-name">{u.fullName}</div>
                            <div className="topbar-user-role">{u.role}</div>
                        </div>
                        <button className="btn-logout" onClick={m1}>Logout</button>
                    </div>
                </div>

                <div className="page">
                    <Wrapped {...props}/>
                </div>

                <div className="footer">
                    © 2026 ProLearn Education Pvt. Ltd. | Made in India
                </div>
            </div>
        )
    }
}
