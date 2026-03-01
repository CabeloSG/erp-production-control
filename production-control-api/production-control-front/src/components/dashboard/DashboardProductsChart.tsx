import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from "recharts";

type Props = {
  products: any[];
};

export default function DashboardProductsChart({ products }: Props) {

  const data = products.map(p => ({
    code: p.code,
    name: p.name,
    value: p.value
  }));

  return (
    <div className="card" style={{ height: 320 }}>
      <h3 style={{ marginBottom: 16 }}>
        Produtos Cadastrados
      </h3>

      <ResponsiveContainer width="100%" height="85%">
        <BarChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="code" />
          <YAxis />
          <Tooltip
            formatter={(value: any, name: any, props: any) => [
              `R$ ${value}`,
              `Valor`
            ]}
            labelFormatter={(label: any, payload: any) => {
              if (payload && payload.length > 0) {
                const p = payload[0].payload;
                return `${p.code} - ${p.name}`;
              }
              return label;
            }}
          />
          <Bar dataKey="value" fill="#2563eb" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
}