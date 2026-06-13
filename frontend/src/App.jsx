import Navbar from "./components/Navbar";
import Dashboard from "./pages/Dashboard";

export default function App() {
  return (
    <div className="app">
      <Navbar />
      <main className="main-content">
        <Dashboard />
      </main>
    </div>
  );
}
