alert("No login");
document
  .getElementById("btnLoginUsuario")
  .addEventListener("click", async (event) => {
    event.preventDefault();

    // Captura os valores dos campos de input
    const username = document.getElementById("nicknameLoginUsuario").value;
    const password = document.getElementById("senhaLoginUsuario").value;
    
    try {
      // Faz a requisição para o endpoint do login
      const response = await fetch(
        `http://localhost:8080/api/usuario/login/${username}/${password}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.ok) {
        const isAuthenticated = await response.json();

        if (isAuthenticated) {
          // Ver para onde redirecionar
          window.location.href = "...";
        } else {
          alert("Usuário ou senha inválidos.");
        }
      } else {
        console.error("Erro na requisição:", response.status);
        alert("Erro ao tentar realizar login. Tente novamente.");
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("Erro de conexão. Verifique se o servidor está ativo.");
    }
  });