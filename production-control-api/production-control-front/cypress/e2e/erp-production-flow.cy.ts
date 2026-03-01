describe("ERP Production Flow", () => {

  it("Fluxo completo ERP", () => {

    // =========================
    // PRODUTO
    // =========================
    cy.visit("/products");

    cy.contains("Novo Produto").click();

    cy.get("input[placeholder='Código']")
      .clear()
      .type("P999");

    cy.get("input[placeholder='Nome']")
      .clear()
      .type("Produto Teste");

    cy.get("input[placeholder='Valor']")
      .clear()
      .type("100");

    cy.contains("Salvar").click();

    cy.contains("Produto Teste");

    // =========================
    // MATÉRIA PRIMA
    // =========================
    cy.visit("/raw-materials");

    cy.contains("Nova Matéria-Prima").click();

    cy.get("input[placeholder='Código']")
      .clear()
      .type("RM999");

    cy.get("input[placeholder='Nome']")
      .clear()
      .type("Aço Teste");

    cy.get("input[placeholder='Estoque']")
      .clear()
      .type("100");

    cy.contains("Salvar").click();

    cy.contains("Aço Teste");

    // =========================
    // BOM (ASSOCIAÇÃO)
    // =========================
    cy.visit("/bill-of-materials");

    // seleciona produto pelo TEXTO REAL
    cy.get("select").first()
      .find("option")
      .contains("P999")
      .then(option => {
        cy.get("select").first().select(option.text());
      });

    // seleciona matéria prima pelo TEXTO REAL
    cy.get("select").eq(1)
      .find("option")
      .contains("RM999")
      .then(option => {
        cy.get("select").eq(1).select(option.text());
      });

    cy.get("input[type='number']")
      .clear()
      .type("10");

    cy.contains("Associar").click();

    cy.contains("Aço Teste");

    // =========================
    // DASHBOARD
    // =========================

    cy.visit("/");

    // garante que entrou na página
    cy.url().should("eq", "http://localhost:5173/");

    // espera o React renderizar
    cy.contains("Dashboard Industrial", { timeout: 10000 });

    // valida se o produto aparece
    cy.contains("P999", { timeout: 10000 });

  });

});