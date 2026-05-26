import { Routes, Route } from "react-router-dom";
import JsonLoginForm from "./components/JsonLoginForm";
import CoursesPage from "./components/CoursesPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<JsonLoginForm />} />
      <Route path="/courses" element={<CoursesPage />} />
    </Routes>
  );
}

export default App;