import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer
} from "recharts";

type Props = {
  rawMaterials: any[];
};

export default function DashboardRawMaterialsChart({
  rawMaterials
}: Props) {

  const data = rawMaterials.map(r => ({
    name: r.name,
    stock: r.quantityInStock
  }));

  return (
    <div className="card">
      <h3>Estoque Matérias-Primas</h3>

      <ResponsiveContainer width="100%" height={220}>
        <BarChart data={data}>
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Bar dataKey="stock" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
}