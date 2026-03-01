# 🏭 ERP Production Control — Industrial SaaS

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![React](https://img.shields.io/badge/React-TypeScript-blue)
![Clean Architecture](https://img.shields.io/badge/Architecture-Clean-blueviolet)
![Status](https://img.shields.io/badge/Status-Production%20Ready-success)

Sistema ERP Industrial Full Stack desenvolvido com foco em **controle de produção**, **BOM (Bill of Materials)** e **Dashboard em tempo real**.

Projeto construído como case real de arquitetura enterprise.

---

# 🎯 Objetivo do Projeto

Simular um ERP industrial semelhante a:

- SAP Manufacturing
- Oracle Netsuite Manufacturing
- TOTVS Produção

Com foco em:

- Escalabilidade
- Arquitetura limpa
- Separação de camadas
- Dashboard realtime

---

# 🧱 Arquitetura

## Backend (Clean Architecture)


Domain
├── Entities
├── Repositories (interfaces)
└── Services

Application
├── DTOs
├── UseCases
└── Mappers

Infrastructure
├── Controllers (REST)
├── Persistence (JPA)
└── Config / Exception Handling


### Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger OpenAPI
- Maven

---

## Frontend (Industrial UI)


pages/
├── Dashboard
├── Products
├── RawMaterials
└── BillOfMaterials

components/
├── UI
└── Dashboard Charts


### Tecnologias

- React + TypeScript
- Vite
- Axios
- Recharts
- Cypress (E2E)

---

# 📦 Funcionalidades

## RF003 — Bill Of Materials (BOM)

✔ Associar matéria-prima ao produto  
✔ CRUD completo  
✔ Inline Edit (nível SAP)  
✔ Validação industrial

---

## RF004 — Capacidade de Produção

✔ Cálculo automático  
✔ Material limitante  
✔ Dashboard realtime

---

## RF005 — Produtos

✔ CRUD completo  
✔ Validações  
✔ Modal profissional

---

## RF006 — Matérias-Primas

✔ Controle de estoque  
✔ Validação de integridade  
✔ Bloqueio quando associado ao BOM

---

## RF007 — Associação BOM

✔ Select inteligente  
✔ Feedback visual (Toast)  
✔ Atualização realtime

---

## RF008 — Dashboard Industrial

✔ KPIs industriais  
✔ Gráfico de capacidade  
✔ Status operacional:

- 🟢 OK
- 🟡 RISCO
- 🔴 PARADO

---

# 🔄 Fluxo ERP


Produto
↓
Matéria-Prima
↓
BOM (Associação)
↓
Cálculo de Capacidade
↓
Dashboard Industrial


---

# 🧪 Testes

## Backend

- Testes unitários
- Testes de integração (SpringBootTest)

## Frontend

- Cypress E2E

Fluxo testado:


Produto → Matéria-Prima → BOM → Dashboard


---

# ▶️ Executar Projeto

## Backend

```bash
cd production-control-api
mvn spring-boot:run

Swagger:

http://localhost:8080/swagger-ui.html
Frontend
cd production-control-front
npm install
npm run dev

App:

http://localhost:5173
Cypress
npx cypress open
📊 Dashboard Industrial

Capacidade por produto

Material limitante

Atualização automática

Indicadores em tempo real

🧠 Decisões Técnicas

Clean Architecture para isolamento de regras de negócio

UseCases independentes da infraestrutura

DTOs para boundary control

Repository Pattern

UI desacoplada por componentes

Dashboard com polling realtime

👨‍💻 Autor

Leandro Gonçalves

Projeto desenvolvido como case Full Stack.

⭐ Resultado

✔ Arquitetura Enterprise
✔ ERP Industrial real
✔ Dashboard SaaS
✔ Projeto Portfólio Senior