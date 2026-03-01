# 🏭 ERP Production Control

Sistema ERP industrial desenvolvido como teste técnico.

## 📌 Objetivo

Gerenciar:

- Produtos
- Matérias-primas
- Bill Of Materials (BOM)
- Capacidade de produção em tempo real

---

## 🧠 Arquitetura

### Backend
- Java 17
- Spring Boot
- Clean Architecture (Use Cases)
- Repository Pattern
- DTO Pattern
- MySQL

### Frontend
- React + TypeScript
- Dashboard Realtime
- UX estilo SAP
- Toast feedback
- Inline Edit

---

## 🔁 Fluxo Industrial

---

## ⚙️ Funcionalidades (RF)

### RF005 — Produtos
✔ CRUD completo

### RF006 — Matérias-Primas
✔ CRUD completo  
✔ Bloqueio por integridade referencial

### RF007 — BOM
✔ Associação produto ↔ matéria-prima  
✔ Edição inline  
✔ Cálculo de capacidade

### RF008 — Dashboard
✔ KPIs em tempo real  
✔ Gráfico de capacidade  
✔ Status operacional

---

## 🧪 Testes

### Backend
- Unit tests (JUnit)

### Frontend
- Cypress E2E
- Fluxo completo ERP

---

## ▶️ Como executar

### Backend

mvn spreing-boot:rum

### Frontende

npm install
npm run dev

### Rodar testes E2E

npx cypress open
---




