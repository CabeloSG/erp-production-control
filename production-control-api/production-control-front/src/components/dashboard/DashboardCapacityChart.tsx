type Props = {
  capacity: number;
};

export default function DashboardCapacityChart({ capacity }: Props) {

  const width = Math.min(capacity * 10, 100);

  return (
    <div className="card" style={{ marginTop:20 }}>
      <h3>Capacidade de Produção</h3>

      <div style={{
        background:"#e5e7eb",
        height:24,
        borderRadius:8,
        marginTop:12
      }}>
        <div style={{
          width:`${width}%`,
          background:"#2563eb",
          height:"100%",
          borderRadius:8
        }} />
      </div>

      <p style={{ marginTop:10 }}>
        {capacity} unidades possíveis
      </p>
    </div>
  );
}