import { useEffect, useRef, useState } from "react";
import { api } from "../services/api";
import Button from "../components/ui/Button";

export default function BillOfMaterials() {

  const [products, setProducts] = useState<any[]>([]);
  const [materials, setMaterials] = useState<any[]>([]);
  const [bomList, setBomList] = useState<any[]>([]);

  const [productId, setProductId] = useState<number>();
  const [rawMaterialId, setRawMaterialId] = useState<number>();
  const [quantityRequired, setQuantityRequired] = useState<number | "">("");

  const [capacity, setCapacity] = useState(0);
  const [limitName, setLimitName] = useState("-");

  const [editingId, setEditingId] = useState<number | null>(null);
  const [editQuantity, setEditQuantity] = useState<number>(0);

  // TOAST PROFISSIONAL
  const [toast, setToast] = useState<{
    message: string;
    type: "success" | "error";
  } | null>(null);

  const productRef = useRef<HTMLSelectElement>(null);

  async function loadInitial() {
    const p = await api.get("/products");
    const r = await api.get("/raw-materials");

    setProducts(p.data);
    setMaterials(r.data);
  }

  async function loadBOM(id:number) {

    const response =
      await api.get(`/products/${id}/bill-of-materials`);

    setBomList(response.data);

    const cap = await api.get(
      `/products/${id}/bill-of-materials/production-capacity`
    );

    setCapacity(cap.data?.capacity ?? 0);
    setLimitName(cap.data?.limitingRawMaterial?.name || "-");
  }

  // =========================
  // TOAST
  // =========================
  function showToast(
    message:string,
    type:"success"|"error"
  ){
    setToast({ message, type });

    setTimeout(() => {
      setToast(null);
    }, 2500);
  }

  // =========================
  // ASSOCIATE
  // =========================
  async function associate() {

    if (!productId || !rawMaterialId || !quantityRequired) {
      showToast("Preencha os campos obrigatórios", "error");
      return;
    }

    if(Number(quantityRequired) <= 0){
        showToast("Quantidade deve ser mair que zero", "error");
        return;
    }

    await api.post(`/products/${productId}/bill-of-materials`, {
      rawMaterialId,
      quantity: Number(quantityRequired)
    });

    showToast("Associação criada com sucesso", "success");

    setQuantityRequired("");
    setRawMaterialId(undefined);

    loadBOM(productId);
  }

  async function updateItem(id:number) {

      if(editQuantity <= 0){
          showToast("Quantidade deve ser mair que zero", "error");
          return;
      }

    await api.put(`/products/${productId}/bill-of-materials/${id}`, {
      quantityRequired: editQuantity
    });

    setEditingId(null);

    if (productId) {
      loadBOM(productId);
    }

    showToast("Quantidade atualizada", "success");
  }

  async function deleteItem(id:number) {
    await api.delete(`/products/${productId}/bill-of-materials/${id}`);

    if (productId) loadBOM(productId);

    showToast("Associação removida", "success");
  }

  useEffect(() => {
    loadInitial();
  }, []);

  useEffect(() => {
    productRef.current?.focus();
  }, []);

  useEffect(() => {
    if (productId) loadBOM(productId);
    else {
      setBomList([]);
      setCapacity(0);
      setLimitName("-");
    }
  }, [productId]);

  return (
    <>
      <h1>Bill Of Materials</h1>

      {/* TOAST COLORIDO */}
      {toast && (
        <div style={{
          position:"fixed",
          top:20,
          right:20,
          background:
            toast.type === "success"
              ? "#16a34a"
              : "#dc2626",
          color:"white",
          padding:"10px 16px",
          borderRadius:8,
          zIndex:9999,
          boxShadow:"0 4px 12px rgba(0,0,0,0.2)"
        }}>
          {toast.message}
        </div>
      )}

      <div style={{display:"flex",gap:10,marginBottom:20}}>

        <div>
          <span style={{color:"red"}}>* </span>
          <select
            ref={productRef}
            value={productId ?? ""}
            onChange={e =>
              setProductId(
                e.target.value ? Number(e.target.value) : undefined
              )
            }
          >
            <option value="">Selecione o produto</option>
            {products.map(p=>(
              <option key={p.id} value={p.id}>
                {p.code} - {p.name}
              </option>
            ))}
          </select>
        </div>

        <div>
          <span style={{color:"red"}}>* </span>
          <select
            value={rawMaterialId ?? ""}
            onChange={e =>
              setRawMaterialId(
                e.target.value ? Number(e.target.value) : undefined
              )
            }
          >
            <option value="">Selecione o material</option>
            {materials.map(r=>(
              <option key={r.id} value={r.id}>
                {r.code} - {r.name}
              </option>
            ))}
          </select>
        </div>

        <div>
          <span style={{color:"red"}}>* </span>
          <input
            type="number"
            min={1}
            step={1}
            value={quantityRequired}
            onChange={e =>
              setQuantityRequired(
                e.target.value === "" ? "" : Number(e.target.value)
              )
            }
            style={{width:100}}
          />
        </div>

        <Button onClick={associate}>Associar</Button>
      </div>

      {productId && (
        <>
          <h3>Capacidade de Produção</h3>
          <p>Material limitante: {limitName}</p>
          <p>Capacidade: {capacity} unidades</p>
        </>
      )}

      <div className="table-scroll">

      <table className="table">
        <thead>
          <tr>
            <th>Produto</th>
            <th>Matéria-Prima</th>
            <th>Quantidade</th>
            <th>Ações</th>
          </tr>
        </thead>

        <tbody>
          {bomList.map((b:any, index:number)=>{

            const rowId = b.id ?? index; // fallback seguro

            return (
              <tr key={rowId}>
                <td>
                  {products.find(p=>p.id===productId)?.name}
                </td>

                <td>
                  {b.rawMaterialCode} - {b.rawMaterialName}
                </td>

                <td>
                  {editingId===rowId ? (
                    <input
                      type="number"
                      min={0}
                      step={1}
                      value={editQuantity}
                      onChange={e=>{
                        const value = e.target.value;

                        setEditQuantity(
                          value === "" ? 0 : Number(value)
                        );
                      }}
                      style={{width:90}}
                    />
                  ) : (
                    b.quantityRequired
                  )}
                </td>

                <td>
                  <div style={{display:"flex",gap:8}}>

                    {editingId===rowId ? (
                      <Button onClick={()=>updateItem(rowId)}>
                        Salvar
                      </Button>
                    ) : (
                      <Button onClick={()=>{
                        setEditingId(rowId);
                        setEditQuantity(
                          Number(b.quantityRequired) || 0
                        );
                      }}>
                        Editar
                      </Button>
                    )}

                    <Button onClick={()=>deleteItem(rowId)}>
                      Excluir
                    </Button>

                  </div>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
      </div>
    </>
  );
}