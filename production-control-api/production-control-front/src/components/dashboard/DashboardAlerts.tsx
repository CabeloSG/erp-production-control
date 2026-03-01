type Props = {
  capacity: number;
  limitName?: string;
};

export default function DashboardAlerts({ capacity, limitName }: Props) {

  if (capacity > 10) return null;

  return (
    <div style={{
      marginTop:20,
      background:"#fee2e2",
      border:"1px solid #ef4444",
      padding:16,
      borderRadius:10,
      color:"#991b1b"
    }}>
      ⚠️ Produção limitada por: <b>{limitName}</b>
      <br />
      Capacidade atual: <b>{capacity}</b>
    </div>
  );
}