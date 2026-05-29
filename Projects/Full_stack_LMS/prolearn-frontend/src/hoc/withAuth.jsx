import { Navigate } from 'react-router-dom'
import { isLogged, isAdmin } from '../api/auth'

// HOC - Higher Order Component
// Wraps any component to add authentication check
// Usage: withAuth(MyComponent) - any logged in user
//        withAuth(MyComponent, true) - admin only
export default function withAuth(Wrapped, adminOnly = false) {
    return function fun(props) {
        if (!isLogged()) {
            return <Navigate to="/login" replace/>
        }
        if (adminOnly && !isAdmin()) {
            return <Navigate to="/student" replace/>
        }
        return <Wrapped {...props}/>
    }
}
