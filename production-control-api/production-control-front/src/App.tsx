import { Routes, Route } from "react-router-dom";
import Sidebar from "./components/layout/Sidebar";
import Header from "./components/layout/Header";

import Dashboard from "./pages/Dashboard";
import Products from "./pages/Products";
import RawMaterials from "./pages/RawMaterials";
import BillOfMaterials from "./pages/BillOfMaterials";

export default function App() {
  return (
    <div className="app-layout">

      <Sidebar />

      <main className="main-content">
        <Header />

        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/products" element={<Products />} />
          <Route path="/raw-materials" element={<RawMaterials />} />
          <Route path="/bill-of-materials" element={<BillOfMaterials />} />
        </Routes>
      </main>

    </div>
  );
}