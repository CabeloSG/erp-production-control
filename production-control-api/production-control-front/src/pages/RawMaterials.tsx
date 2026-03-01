import { useEffect, useState, useRef } from "react";
import { api } from "../services/api";
import Button from "../components/ui/Button";
import Modal from "../components/ui/Modal";
import Toast from "../components/ui/Toast";

export default function RawMaterials() {

  const [rawMaterials, setRawMaterials] = useState<any[]>([]);
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
    quantityInStock: "" as number | ""
  });

  // =========================
  // LOAD
  // =========================
  async function loadRawMaterials() {
    const response = await api.get("/raw-materials");
    setRawMaterials(response.data);
  }

  // =========================
  // OPEN CREATE
  // =========================
  function openCreateModal() {
    setForm({
      code: "",
      name: "",
      quantityInStock: ""
    });

    setEditingId(null);
    setError("");
    setOpen(true);

    setTimeout(() => codeInputRef.current?.focus(), 100);
  }

  // =========================
  // EDIT
  // =========================
  function editRawMaterial(material: any) {
    setForm({
      code: material.code,
      name: material.name,
      quantityInStock: material.quantityInStock
    });

    setEditingId(material.id);
    setError("");
    setOpen(true);

    setTimeout(() => codeInputRef.current?.focus(), 100);
  }

  // =========================
  // SAVE
  // =========================
  async function saveRawMaterial() {

    if (
      !form.code ||
      !form.name ||
      form.quantityInStock === "" ||
      Number(form.quantityInStock) <= 0
    ) {
      setError("Preencha todos os campos corretamente.");
      return;
    }

    if (Number(form.quantityInStock) < 0) {
      setToast({
        message: "Quantidade não pode ser negativa.",
        type: "error"
      });
      return;
    }

    setLoading(true);

    try {

      const payload = {
        code: form.code,
        name: form.name,
        quantityInStock: Number(form.quantityInStock)
      };

      if (editingId) {
        await api.put(`/raw-materials/${editingId}`, payload);
        setToast({
          message: "Matéria-prima atualizada!",
          type: "success"
        });
      } else {
        await api.post("/raw-materials", payload);
        setToast({
          message: "Matéria-prima criada!",
          type: "success"
        });
      }

      setOpen(false);
      setEditingId(null);
      loadRawMaterials();

    } catch (err: any) {

      if (err.response?.status === 409) {
        setError("Já existe matéria-prima com este código.");
      } else {
        setError("Erro ao salvar.");
      }

    } finally {
      setLoading(false);
    }
  }

  // =========================
  // DELETE
  // =========================
  async function deleteRawMaterial(id: number) {
    try {
      await api.delete(`/raw-materials/${id}`);
      setToast({
        message: "Matéria-prima excluída!",
        type: "success"
      });
      loadRawMaterials();
    } catch (err:any) {

        if (err.response?.status === 409) {
          setToast({
            message:
              "Não é possível excluir. Matéria-prima está associada ao BOM.",
            type: "error"
          });
        } else {
          setToast({
            message: "Erro ao excluir.",
            type: "error"
          });
        }
      }
  }

  useEffect(() => {
    loadRawMaterials();
  }, []);

  // =========================
  // RENDER
  // =========================
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
        <h1>Matérias-Primas</h1>

        <Button onClick={openCreateModal}>
          Nova Matéria-Prima
        </Button>
      </div>

      <div className="table-scroll">
        <table className="table">
          <thead>
            <tr>
              <th>Código</th>
              <th>Nome</th>
              <th>Estoque</th>
              <th style={{ textAlign: "right" }}>Ações</th>
            </tr>
          </thead>

          <tbody>
            {rawMaterials.map((m) => (
              <tr key={m.id}>
                <td>{m.code}</td>
                <td>{m.name}</td>
                <td>{m.quantityInStock}</td>

                <td>
                  <div className="actions">
                    <Button onClick={() => editRawMaterial(m)}>
                      Editar
                    </Button>

                    <Button onClick={() => deleteRawMaterial(m.id)}>
                      Excluir
                    </Button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <Modal open={open} onClose={() => setOpen(false)}>
        <div className="modal-content">

          <h3>
            {editingId
              ? "Editar Matéria-Prima"
              : "Nova Matéria-Prima"}
          </h3>

          {error && (
            <p style={{ color: "red", marginTop: 8 }}>
              {error}
            </p>
          )}

          <div className="modal-form-row">

            <div>
              <span style={{ color: "red" }}>* </span>
              <input
                ref={codeInputRef}
                placeholder="Código"
                value={form.code}
                onChange={e =>
                  setForm({ ...form, code: e.target.value })
                }
              />
            </div>

            <div>
              <span style={{ color: "red" }}>* </span>
              <input
                placeholder="Nome"
                value={form.name}
                onChange={e =>
                  setForm({ ...form, name: e.target.value })
                }
              />
            </div>

            <div style={{ width: 140 }}>
              <span style={{ color: "red" }}>* </span>
              <input
                type="number"
                min={0}
                placeholder="Estoque"
                value={form.quantityInStock}
                onChange={(e) =>
                  setForm({
                    ...form,
                    quantityInStock:
                      e.target.value === ""
                        ? ""
                        : Number(e.target.value)
                  })
                }
              />
            </div>

          </div>

          <div className="modal-actions">

            <Button onClick={saveRawMaterial}>
              {loading
                ? "Salvando..."
                : editingId
                ? "Atualizar"
                : "Salvar"}
            </Button>

            <Button onClick={() => setOpen(false)}>
              Fechar
            </Button>

          </div>

        </div>
      </Modal>

    </div>
  );
}