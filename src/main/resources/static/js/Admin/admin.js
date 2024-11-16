var btnAdicionar = document.querySelector("#btnAdicionar");

async function adicionarPlataforma(nome) {
  const response = await fetch("http://localhost:8080/api/plataformas/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ nome: nome }),
  });
  if (response.ok) {
    alert("Plataforma adicionada com sucesso!");
  } else {
    alert("Erro ao adicionar plataforma.");
  }
}

function handleBuscarPlataformas() {
  const inputNome = document.getElementById("nomePlataforma").value;
  buscarPlataformas(inputNome);
}

async function buscarPlataformas(nome) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/plataformas/${nome}`
    );
    const plataformas = await response.json();
    atualizarTabela(plataformas);
    alert("Plataformas carregadas com sucesso!");
  } catch (error) {
    console.error("Erro ao buscar plataformas:", error);
  }
}

function atualizarTabela(plataformas) {
  const tabela = document.querySelector("tbody");
  tabela.innerHTML = "";
  plataformas.forEach((plataforma) => {
    tabela.innerHTML += `
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

async function deletarPlataforma(id) {
  const response = await fetch(`http://localhost:8080/plataformas/${id}`, {
    method: "DELETE",
  });
  if (response.ok) {
    alert("Plataforma removida com sucesso!");
    atualizarLista();
  } else {
    alert("Erro ao remover plataforma.");
  }
}

document.addEventListener("DOMContentLoaded", buscarPlataformas);
