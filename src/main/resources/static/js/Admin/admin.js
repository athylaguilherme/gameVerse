// Seleciona os elementos necessários
const btnAdicionar = document.querySelector("#btnAdicionar");
const inputNome = document.getElementById("nomePlataforma");
const tabela = document.querySelector("tbody");

// Função para adicionar uma nova plataforma
async function adicionarPlataforma(nome) {
  try {
    const response = await fetch("http://localhost:8080/api/plataformas/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ nome }),
    });

    if (response.ok) {
      alert("Plataforma adicionada com sucesso!");
      buscarPlataformas(""); // Atualiza a tabela após adicionar
    } else {
      alert("Erro ao adicionar plataforma.");
    }
  } catch (error) {
    console.error("Erro ao adicionar plataforma:", error);
    alert("Erro ao adicionar plataforma. Verifique o console.");
  }
}

// Função para buscar plataformas com base no nome
async function buscarPlataformas(nome = "") {
  try {
    nome = nome.trim(); // Remove espaços extras

    // Configura a URL de busca
    const url = nome
      ? `http://localhost:8080/api/plataformas?nome=${encodeURIComponent(nome)}`
      : `http://localhost:8080/api/plataformas`; // Busca todas se o nome estiver vazio

    const response = await fetch(url);
    if (!response.ok) {
      throw new Error("Erro na requisição: " + response.status);
    }

    const plataformas = await response.json();

    // Atualiza a tabela com os resultados
    if (Array.isArray(plataformas)) {
      atualizarTabela(plataformas);
    } else if (plataformas) {
      atualizarTabela([plataformas]); // Para um único item
    } else {
      atualizarTabela([]);
      alert("Nenhuma plataforma encontrada.");
    }
  } catch (error) {
    console.error("Erro ao buscar plataformas:", error);
    alert("Erro ao buscar plataformas. Verifique o console.");
  }
}

// Função para atualizar a tabela com os dados das plataformas
function atualizarTabela(plataformas) {
  const tabela = document.getElementById("tabela") || document.querySelector("tbody");
  tabela.innerHTML = ""; // Limpa a tabela

  if (plataformas.length === 0) {
    tabela.innerHTML = `<tr><td colspan="2">Nenhuma plataforma encontrada.</td></tr>`;
    return;
  }

  plataformas.forEach((plataforma) => {
    tabela.innerHTML += `
      <tr>
        <td>${plataforma.nome}</td>
        <td>
          <button 
            type="button" 
            class="btn btn-warning mb-2" 
            data-id="${plataforma.id}" 
            data-bs-toggle="modal" 
            data-bs-target="#modalAtualizarPlataforma"
          >
            <i class="fa-solid fa-pen-to-square"></i> Editar
          </button>
          <button 
            class="btn btn-danger" 
            onclick="deletarPlataforma(${plataforma.id})"
          >
            Excluir
          </button>
        </td>
      </tr>
    `;
  });

  // Configura eventos de "Editar"
  document.querySelectorAll(".btn-warning").forEach((btn) => {
    btn.addEventListener("click", () => {
      const plataformaId = btn.getAttribute("data-id");
      carregarModal(plataformaId);
    });
  });
}

// Função para deletar uma plataforma pelo ID
async function deletarPlataforma(id) {
  try {
    const response = await fetch(`http://localhost:8080/api/plataformas/${id}`, {
      method: "DELETE",
    });

    if (response.ok) {
      alert("Plataforma removida com sucesso!");
      buscarPlataformas(""); // Atualiza a tabela após deletar
    } else {
      alert("Erro ao remover plataforma.");
    }
  } catch (error) {
    console.error("Erro ao remover plataforma:", error);
    alert("Erro ao remover plataforma. Verifique o console.");
  }
}

// Função para carregar o modal dinamicamente
async function carregarModal(plataformaId) {
  try {
    const plataforma = await buscarPlataformaPorId(plataformaId);

    // Preenche o campo de entrada com o nome da plataforma (ou deixa vazio)
    const plataformaInput = document.getElementById("plataforma");
    if (plataformaInput) {
      plataformaInput.value = plataforma.nome || "";  // Se nome estiver vazio, o campo ficará vazio
    }

    // Configura o evento de clique no botão de salvar
    const salvarBtn = document.getElementById("btnSalvar");
    if (salvarBtn) {
      salvarBtn.onclick = async () => {
        const novoNome = plataformaInput.value.trim();
        if (novoNome) {
          await atualizarPlataforma(plataformaId, novoNome);
        } else {
          alert("Por favor, insira um nome válido.");
        }
      };
    }

    // Exibe o modal
    const modal = new bootstrap.Modal(document.getElementById("modalAtualizarPlataforma"));
    modal.show();

  } catch (error) {
    console.error("Erro ao carregar os dados para atualização:", error);
    alert("Erro ao carregar dados da plataforma.");
  }
}


// Função para buscar plataforma pelo ID
async function buscarPlataformaPorId(id) {
  try {
    const response = await fetch(`http://localhost:8080/api/plataformas/${id}`);
    return await response.json();
  } catch (error) {
    console.error("Erro ao buscar plataforma:", error);
  }
}

// Evento para buscar plataformas ao carregar a página
document.addEventListener("DOMContentLoaded", () => buscarPlataformas(""));

// Evento para o botão de adicionar
btnAdicionar?.addEventListener("click", () => {
  const nome = inputNome.value.trim();
  if (nome) {
    adicionarPlataforma(nome);
  } else {
    alert("Por favor, insira o nome da plataforma.");
  }
});

// Função para atualizar uma plataforma pelo ID
async function atualizarPlataforma(id, nome) {
  try {
    const response = await fetch(`http://localhost:8080/api/plataformas/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ nome }),
    });

    if (response.ok) {
      alert("Plataforma atualizada com sucesso!");
      buscarPlataformas(""); 
    } else {
      alert("Erro ao atualizar plataforma.");
    }
  } catch (error) {
    console.error("Erro ao atualizar plataforma:", error);
    alert("Erro ao atualizar plataforma. Verifique o console.");
  }
}
