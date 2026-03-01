import { NavLink } from "react-router-dom";

export default function Sidebar() {
  return (
    <div className="sidebar">
      <h2>ERP</h2>

      <NavLink to="/" end>
        Dashboard
      </NavLink>

      <NavLink to="/products">
        Produtos
      </NavLink>

      <NavLink to="/raw-materials">
        Matérias-Primas
      </NavLink>

      <NavLink to="/bill-of-materials">
        Associação (BOM)
      </NavLink>
    </div>
  );
}