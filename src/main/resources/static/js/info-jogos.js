document.addEventListener("DOMContentLoaded", function () {
  const apiUrl = "http://localhost:8080/jogos/id";
  const params = new URLSearchParams(window.location.search);
  const jogoId = params.get("id");

  if (!jogoId) {
    alert("Nenhum jogo foi selecionado!");
    return;
  }

  async function carregarJogo() {
    try {
      const response = await fetch(`${apiUrl}?id=${jogoId}`);
      if (!response.ok) {
        throw new Error("Erro ao carregar os dados do jogo.");
      }
      const jogo = await response.json();
      preencherDadosDoJogo(jogo);
    } catch (error) {
      console.error(error);
      alert("Erro ao carregar os dados do jogo.");
    }
  }

  function preencherDadosDoJogo(jogo) {
    document.querySelector("h2").textContent = jogo.nome;
    document.querySelector(".col-md-4 p").textContent = jogo.descricao;
    document.querySelector(".col-md-4 button a").href = jogo.imagem || "#";
  }

  carregarJogo();
});
