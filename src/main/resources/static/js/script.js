// Função para observar os elementos
const observer = new IntersectionObserver((entries, observer) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      // Adiciona a classe 'show' quando o elemento está visível
      entry.target.classList.add("show");
      observer.unobserve(entry.target); // Parar de observar o elemento após ele aparecer
    }
  });
});

// Seleciona todos os elementos com a classe 'animate'
const elements = document.querySelectorAll(".animate");
elements.forEach((el) => {
  observer.observe(el); // Adiciona observador a cada elemento
});

function cadastrar() {
  fetch("http://localhost:8080/usuarios", {
    // Substitua "porta" pela porta do seu servidor
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify({
      nome: document.getElementById("nome").value,
      nickname: document.getElementById("nickname").value,
      email: document.getElementById("email").value,
      telefone: document.getElementById("telefone").value,
      senha: document.getElementById("senha").value,
    }),
  })
    .then(function (res) {
      if (res.ok) {
        console.log("Usuário cadastrado com sucesso!");
      } else {
        console.error("Erro ao cadastrar usuário.");
      }
      return res.json();
    })
    .then(function (data) {
      console.log(data); // Exibir a resposta do servidor (usuário cadastrado ou mensagem de erro)
    })
    .catch(function (error) {
      console.error("Erro na requisição:", error);
    });
}

document
  .getElementById("formulario")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    cadastrar();
  });

// Função para buscar usuários por nome 
function buscarUsuarios() {
  fetch(`http://localhost:8080/usuarios/${nome}`, {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    method: "GET", // Aqui usamos o método GET
  })
    .then(function (res) {
      if (res.ok) {
        return res.json(); // Retorna a resposta como JSON
      } else {
        console.error("Erro ao buscar usuários.");
        return;
      }
    })
    .then(function (data) {
      console.log(data); // Exibe os dados dos usuários retornados pelo servidor
    })
    .catch(function (error) {
      console.error("Erro na requisição:", error);
    });
}

// Chame a função buscarUsuarios quando quiser realizar a requisição GET
buscarUsuarios();
