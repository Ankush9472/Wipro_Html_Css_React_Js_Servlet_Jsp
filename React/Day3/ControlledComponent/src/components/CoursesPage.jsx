import { useLocation, useNavigate } from "react-router-dom";

const CoursesPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const username = location.state?.username || "User";

  const courses = [
    { id: 1, name: "Core Java", desc: "OOPs Based" },
    { id: 2, name: "Spring Boot", desc: "Java framework for developing web app" },
    { id: 3, name: "javascript", desc: "Language for web development" },
    { id: 4, name: "python", desc: "Easy and useful for AI and ML" },
  ];

  const handleLogout = () => {
    navigate("/");
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Welcome, {username}</h2>
      <h3>Available Courses</h3>
      <ul>
        {courses.map((c) => (
          <li key={c.id} style={{ marginBottom: "10px" }}>
            <b>{c.name}</b> - {c.desc}
          </li>
        ))}
      </ul>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default CoursesPage;