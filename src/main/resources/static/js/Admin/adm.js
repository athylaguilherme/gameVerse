document.addEventListener("DOMContentLoaded", () => {
  const tabelaPlataformas = document.querySelector("tbody");
  const btnBuscar = document.querySelector("#buscarPlataforma");
  const btnCadastrar = document.querySelector("#cadastrarPlataforma");
  const inputBuscar = document.querySelector("#nomePlataforma");

  // Função para buscar plataformas
  async function buscarPlataformas(nome = "") {
    try {
      const response = await fetch(
        `http://localhost:8080/api/plataformas/${nome}`
      );
      if (!response.ok) throw new Error("Erro ao buscar plataformas");
      const plataformas = await response.json();
      atualizarTabela(plataformas);
      alert("Plataformas carregadas com sucesso!");
    } catch (error) {
      console.error("Erro ao buscar plataformas:", error);
      alert("Não foi possível carregar as plataformas.");
    }
  }

  // Função para adicionar plataforma
  async function adicionarPlataforma(nome) {
    try {
      const response = await fetch(
        "http://localhost:8080/api/plataformas/add",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ nome }),
        }
      );
      if (!response.ok) throw new Error("Erro ao adicionar plataforma");
      alert("Plataforma adicionada com sucesso!");
      buscarPlataformas(); // Atualiza a lista após adicionar
    } catch (error) {
      console.error("Erro ao adicionar plataforma:", error);
      alert("Não foi possível adicionar a plataforma.");
    }
  }

  // Função para deletar plataforma
  async function deletarPlataforma(id) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/plataformas/${id}`,
        {
          method: "DELETE",
        }
      );
      if (!response.ok) throw new Error("Erro ao remover plataforma");
      alert("Plataforma removida com sucesso!");
      buscarPlataformas(); // Atualiza a lista após remover
    } catch (error) {
      console.error("Erro ao deletar plataforma:", error);
      alert("Não foi possível deletar a plataforma.");
    }
  }

  // Atualiza a tabela de plataformas
  function atualizarTabela(plataformas) {
    tabelaPlataformas.innerHTML = "";
    plataformas.forEach((plataforma) => {
      tabelaPlataformas.innerHTML += `
          <tr>
            <td>${plataforma.nome}</td>
            <td>
              <button class="btn btn-warning" onclick="editarPlataforma(${plataforma.id})">Editar</button>
              <button class="btn btn-danger" onclick="deletarPlataforma(${plataforma.id})">Excluir</button>
            </td>
          </tr>
        `;
    });
  }

  // Eventos de busca e cadastro
  btnBuscar.addEventListener("click", () => {
    const nome = inputBuscar.value.trim();
    buscarPlataformas(nome);
  });

  btnCadastrar.addEventListener("click", () => {
    const nome = document.querySelector("#nomeUser").value.trim();
    if (nome) {
      adicionarPlataforma(nome);
    } else {
      alert("Por favor, insira um nome para a plataforma.");
    }
  });

  // Carrega plataformas ao iniciar
  buscarPlataformas();
});
