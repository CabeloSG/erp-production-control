import { useEffect, useState, useRef } from "react";
import { api } from "../services/api";
import Button from "../components/ui/Button";
import Modal from "../components/ui/Modal";
import Toast from "../components/ui/Toast";

export default function Products() {

  const [products, setProducts] = useState<any[]>([]);
  const [open, setOpen] = useState(false);
  const [loading, setLoading] = useState(false);

  const [toast, setToast] = useState<{
    message: string;
    type: "success" | "error";
  } | null>(null);

  const [editingId, setEditingId] = useState<number | null>(null);
  const codeInputRef = useRef<HTMLInputElement>(null);

  const [error, setError] = useState("");

  const [form, setForm] = useState({
    code: "",
    name: "",
    value: "" as number | ""
  });

  async function loadProducts() {
    const response = await api.get("/products");
    setProducts(response.data);
  }

  function openCreateModal() {
    setForm({
      code: "",
      name: "",
      value: ""
    });

    setEditingId(null);
    setError("");
    setOpen(true);

    setTimeout(() => codeInputRef.current?.focus(), 100);
  }

  function editProduct(product: any) {
    setForm({
      code: product.code,
      name: product.name,
      value: product.value
    });

    setEditingId(product.id);
    setError("");
    setOpen(true);

    setTimeout(() => codeInputRef.current?.focus(), 100);
  }

  async function saveProduct() {

    if (!form.code || !form.name || !form.value) {
      setError("Preencha todos os campos corretamente.");
      return;
    }

    if (Number(form.value) <= 0) {
      setError("Valor deve ser maior que zero.");
      return;
    }

    setLoading(true);

    try {

      if (editingId) {
        await api.put(`/products/${editingId}`, {
          ...form,
          value: Number(form.value)
        });

        setToast({
          message: "Produto atualizado!",
          type: "success"
        });

      } else {

        await api.post("/products", {
          ...form,
          value: Number(form.value)
        });

        setToast({
          message: "Produto criado!",
          type: "success"
        });
      }

      setOpen(false);
      setEditingId(null);
      loadProducts();

    } catch (err: any) {

      if (err.response?.status === 409) {
        setError("Já existe produto com este código.");
      } else {
        setError("Erro ao salvar.");
      }

    } finally {
      setLoading(false);
    }
  }

  async function deleteProduct(id:number) {
    try {
      await api.delete(`/products/${id}`);
      setToast({ message:"Produto excluído!", type:"success" });
      loadProducts();
    } catch {
      setToast({
        message:"Não foi possível excluir (produto possui BOM).",
        type:"error"
      });
    }
  }

  useEffect(() => {
    loadProducts();
  }, []);

  return (
    <div className="page-container">

      {toast && (
        <Toast
          message={toast.message}
          type={toast.type}
          onClose={() => setToast(null)}
        />
      )}

      <div className="page-header">
        <h1>Produtos</h1>

        <Button onClick={openCreateModal}>
          Novo Produto
        </Button>
      </div>

      <div className="table-scroll">
        <table className="table">
          <thead>
            <tr>
              <th>Código</th>
              <th>Nome</th>
              <th>Valor</th>
              <th style={{textAlign:"right"}}>Ações</th>
            </tr>
          </thead>

          <tbody>
            {products.map(p => (
              <tr key={p.id}>
                <td>{p.code}</td>
                <td>{p.name}</td>
                <td>{p.value}</td>

                <td>
                  <div className="actions">
                    <Button onClick={()=>editProduct(p)}>
                      Editar
                    </Button>

                    <Button onClick={()=>deleteProduct(p.id)}>
                      Excluir
                    </Button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <Modal open={open} onClose={()=>setOpen(false)}>

        <h3>{editingId ? "Editar Produto" : "Novo Produto"}</h3>

        {error && (
          <p style={{color:"red", marginTop:8}}>
            {error}
          </p>
        )}

        <div className="modal-form-row">

          <div>
            <span style={{color:"red"}}>* </span>
            <input
              ref={codeInputRef}
              placeholder="Código"
              value={form.code}
              onChange={e =>
                setForm({...form, code:e.target.value})
              }
            />
          </div>

          <div>
            <span style={{color:"red"}}>* </span>
            <input
              placeholder="Nome"
              value={form.name}
              onChange={e =>
                setForm({...form, name:e.target.value})
              }
            />
          </div>

          <div style={{width:120}}>
            <span style={{color:"red"}}>* </span>
            <input
              type="number"
              min={0}
              placeholder="Valor"
              value={form.value}
              onChange={e =>
                setForm({
                  ...form,
                  value:
                    e.target.value === ""
                      ? ""
                      : Number(e.target.value)
                })
              }
            />
          </div>

        </div>

        <div className="modal-actions">
          <Button onClick={saveProduct}>
            {loading
              ? "Salvando..."
              : editingId
              ? "Atualizar"
              : "Salvar"}
          </Button>

          <Button onClick={()=>setOpen(false)}>
            Fechar
          </Button>
        </div>

      </Modal>

    </div>
  );
}