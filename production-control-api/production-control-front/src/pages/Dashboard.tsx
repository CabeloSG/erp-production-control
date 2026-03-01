import { useEffect, useState } from "react";
import { api } from "../services/api";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer
} from "recharts";

export default function Dashboard() {

  const [data, setData] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);

  async function loadDashboard() {

    setLoading(true);

    try {

      const products = await api.get("/products");

      const result = await Promise.all(
        products.data.map(async (p: any) => {

          try {

            const cap = await api.get(
              `/products/${p.id}/bill-of-materials/production-capacity`
            );

            return {
              id: p.id,
              name: p.name,
              code: p.code,
              capacity: cap.data.capacity,
              limit: cap.data.limitingRawMaterial?.name || "-"
            };

          } catch {
            return {
              id: p.id,
              name: p.name,
              code: p.code,
              capacity: 0,
              limit: "-"
            };
          }
        })
      );

      setData(result);

    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {

    loadDashboard();

    const interval = setInterval(loadDashboard, 5000);

    return () => clearInterval(interval);

  }, []);

  // ===== KPIs =====
  const totalProducts = data.length;

  const totalCapacity =
    data.reduce((acc, p) => acc + p.capacity, 0);

  const bottlenecks =
    data.filter(p => p.capacity < 20).length;

  const avgCapacity =
    totalProducts > 0
      ? Math.round(totalCapacity / totalProducts)
      : 0;

  return (
    <div className="page-container">

      <h1>Dashboard Industrial</h1>

      {/* ===== KPI GRID RESPONSIVO ===== */}
      <div className="dashboard-grid">

        <Card title="Produtos">{totalProducts}</Card>
        <Card title="Capacidade Total">{totalCapacity}</Card>
        <Card title="Gargalos">{bottlenecks}</Card>
        <Card title="Capacidade Média">{avgCapacity}</Card>

      </div>

      {/* ===== CHART COMPACTO (SAP STYLE) ===== */}
      <div className="chart-container">

        <h3 style={{ color: "white", marginBottom: 10 }}>
          Capacidade por Produto
        </h3>

        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={data}>
            <XAxis dataKey="code" stroke="#fff" />
            <YAxis stroke="#fff" />
            <Tooltip />
            <Bar dataKey="capacity" />
          </BarChart>
        </ResponsiveContainer>

      </div>

      {/* ===== TABELA ===== */}
      <div className="table-scroll">
        <table className="table">
          <thead>
            <tr>
              <th>Produto</th>
              <th>Capacidade</th>
              <th>Limitante</th>
              <th>Status</th>
            </tr>
          </thead>

          <tbody>
            {data.map((d: any) => {

              const status =
                d.capacity <= 0
                  ? "PARADO"
                  : d.capacity < 20
                  ? "RISCO"
                  : "OK";

              const color =
                status === "OK"
                  ? "#16a34a"
                  : status === "RISCO"
                  ? "#f59e0b"
                  : "#dc2626";

              return (
                <tr key={d.id}>
                  <td>{d.code} - {d.name}</td>
                  <td>{d.capacity}</td>
                  <td>{d.limit}</td>
                  <td>
                    <span style={{
                      background: color,
                      color: "white",
                      padding: "4px 10px",
                      borderRadius: 8
                    }}>
                      {status}
                    </span>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>

      {loading && <p>Atualizando dados...</p>}

    </div>
  );
}

function Card({ title, children }: any) {
  return (
    <div className="dashboard-card">

      <div style={{
        opacity: 0.7,
        fontSize: 13
      }}>
        {title}
      </div>

      <div style={{
        fontSize: 28,
        fontWeight: "bold",
        marginTop: 6
      }}>
        {children}
      </div>

    </div>
  );
}