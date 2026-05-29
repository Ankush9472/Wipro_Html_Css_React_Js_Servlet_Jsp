import './App.css'
import { Link } from 'react-router-dom'
import AnimatedRoutes from './component/AnimatedRoutes'

function App() {
  return (
    <>
      <nav style={{ padding: "10px", background: "#eee" }}>
        <Link to="/">Home</Link> |{" "}
        <Link to="/login">Login</Link> |{" "}
        <Link to="/admin">Admin</Link> |{" "}
        <Link to="/employees">Employees</Link> |{" "}
        <Link to="/courses">Courses</Link>
      </nav>
      <AnimatedRoutes />
    </>
  )
}

export default App