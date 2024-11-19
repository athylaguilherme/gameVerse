// Seletores do HTML
const btnAdicionar = document.querySelector("#btnAdicionar");
const tabela = document.querySelector("tbody");

// Função para adicionar um administrador
async function adicionarAdministrador(nome, email, senha) {
  try {
    const response = await fetch(
      "http://localhost:8080/api/administradoreshome/add",
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, email, senha }),
      }
    );

    if (response.ok) {
      alert("Administrador adicionado com sucesso!");
      buscarAdministrador(""); // Atualiza a tabela
    } else {
      alert("Erro ao adicionar administrador.");
    }
  } catch (error) {
    console.error("Erro ao adicionar Administrador:", error);
    alert("Erro ao adicionar administrador. Verifique o console.");
  }
}

// Função para buscar administradores
async function buscarAdministrador(email = "") {
  try {
    const url = email.trim()
      ? `http://localhost:8080/api/administradoreshome/email/${encodeURIComponent(
          email
        )}`
      : "http://localhost:8080/api/administradoreshome";

    const response = await fetch(url);
    if (!response.ok) throw new Error(`Erro na requisição: ${response.status}`);

    let administradores = await response.json();
    if (!Array.isArray(administradores)) administradores = [administradores];
    atualizarTabela(administradores);
  } catch (error) {
    console.error("Erro ao buscar administrador:", error);
    alert("Erro ao buscar administrador. Verifique o console.");
  }
}

// Função para atualizar a tabela
function atualizarTabela(administradores) {
  tabela.innerHTML = ""; // Limpa a tabela

  if (administradores.length === 0) {
    tabela.innerHTML = `<tr><td colspan="3">Nenhum administrador encontrado.</td></tr>`;
    return;
  }

  administradores.forEach((admin) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${admin.nome}</td>
      <td>${admin.email}</td>
      <td>
        <button type="button" class="btn btn-warning mb-2" data-id="${admin.id}" data-bs-toggle="modal" data-bs-target="#atualizarDadosAdm">
          <i class="fa-solid fa-key"></i> Alterar Dados
        </button>
        <button type="button" class="btn btn-danger mb-2" data-id="${admin.id}">
          <i class="fa-solid fa-trash"></i> Excluir
        </button>
      </td>
    `;
    tabela.appendChild(row);
  });

  configurarEventos();
}

// Configura eventos dinâmicos para editar e excluir
function configurarEventos() {
  document.querySelectorAll(".btn-warning").forEach((btn) => {
    btn.addEventListener("click", (event) => {
      const id = event.currentTarget.getAttribute("data-id");
      carregarModal(id);
    });
  });

  document.querySelectorAll(".btn-danger").forEach((btn) => {
    btn.addEventListener("click", (event) => {
      const id = event.currentTarget.getAttribute("data-id");
      if (confirm("Tem certeza que deseja excluir este administrador?")) {
        deletarAdministrador(id);
      }
    });
  });
}

// Função para carregar modal com dados do administrador
async function carregarModal(id) {
  try {
    const admin = await buscarAdministradorPorId(id);

    // Ajuste os campos do modal para corresponder aos IDs no HTML
    document.getElementById("nomeDeUsuario").value = admin.nome || "";
    document.getElementById("e-mail").value = admin.email || "";

    // Configura o botão de salvar
    document.getElementById("btnSalvar").onclick = async () => {
      const novoNome = document.getElementById("nomeDeUsuario").value.trim();
      const novoEmail = document.getElementById("e-mail").value.trim();

      if (novoNome && novoEmail) {
        await atualizarAdministrador(id, novoNome, novoEmail);
      } else {
        alert("Por favor, preencha todos os campos.");
      }
    };
  } catch (error) {
    console.error("Erro ao carregar modal:", error);
    alert("Erro ao carregar informações.");
  }
}

// Função para buscar administrador por ID
async function buscarAdministradorPorId(id) {
  const response = await fetch(
    `http://localhost:8080/api/administradoreshome/${id}`
  );
  if (!response.ok) throw new Error("Erro ao buscar administrador.");
  return response.json();
}

// Função para excluir administrador
async function deletarAdministrador(id) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/administradoreshome/${id}`,
      {
        method: "DELETE",
      }
    );

    if (response.ok) {
      alert("Administrador removido com sucesso!");
      buscarAdministrador(""); // Atualiza a tabela
    } else {
      alert("Erro ao remover administrador.");
    }
  } catch (error) {
    console.error("Erro ao remover administrador:", error);
    alert("Erro ao remover administrador. Verifique o console.");
  }
}

async function atualizarAdministrador(id, nome, email) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/administradoreshome/update/${id}`,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, email }), // Envia os dados como JSON
      }
    );

    if (response.ok) {
      alert("Administrador atualizado com sucesso!");
      buscarAdministrador(""); // Atualiza a tabela
    } else {
      const erro = await response.text();
      console.error("Erro ao atualizar administrador:", erro);
      alert("Erro ao atualizar administrador.");
    }
  } catch (error) {
    console.error("Erro ao atualizar administrador:", error);
    alert("Erro ao atualizar administrador. Verifique o console.");
  }
}
