type Props = {
  totalProducts: number;
  totalRawMaterials: number;
};

export default function DashboardKPIs({
  totalProducts,
  totalRawMaterials,
}: Props) {

  return (
    <div className="card-grid">

      <div className="card">
        <h3>Produtos</h3>
        <p style={{ fontSize: 28 }}>{totalProducts}</p>
      </div>

      <div className="card">
        <h3>Matérias-Primas</h3>
        <p style={{ fontSize: 28 }}>{totalRawMaterials}</p>
      </div>

    </div>
  );
}