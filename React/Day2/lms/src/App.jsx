import Navbar from './components/Navbar';
import Hero from './components/Hero';
import Courses from './components/Courses';
import Footer from './components/Footer';

function App() {
  return (
    <div className="min-h-screen bg-gray-100">
      <Navbar />
      <Hero />
      <Courses />
      <Footer />
    </div>
  );
}

export default App;