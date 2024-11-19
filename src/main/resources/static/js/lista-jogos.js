document.addEventListener("DOMContentLoaded", function () {
  const apiUrl = "http://localhost:8080/jogos";
  const listaJogos = document.getElementById("lista-jogos");

  function criarCardJogo(jogo) {
    return `
      <div class="card borda-superior mt-5">
        <div class="card-body d-flex align-items-center">
          <img src="/uploads/${jogo.imagem}" alt="${jogo.nome}" id="teste-image" />
          <div class="p1 ms-5">
            <h5 class="card-title">${jogo.nome}</h5>
            <p class="card-text">${jogo.descricao}</p>
            <a href="./jogo/info-jogos.html?id=${jogo.id}">Saiba mais</a>
          </div>
        </div>
      </div>`;
  }

  async function carregarJogos() {
    try {
      const response = await fetch(apiUrl);
      if (!response.ok) {
        throw new Error("Erro ao buscar a lista de jogos.");
      }
      const jogos = await response.json();
      renderizarJogos(jogos);
    } catch (error) {
      console.error(error);
      alert("Erro ao carregar os jogos.");
    }
  }

  function renderizarJogos(jogos) {
    listaJogos.innerHTML = ""; // Limpa a lista antes de renderizar
    jogos.forEach((jogo) => {
      listaJogos.innerHTML += criarCardJogo(jogo);
    });
  }

  carregarJogos();
});
