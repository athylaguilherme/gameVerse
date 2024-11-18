alert("No login");
document
  .getElementById("loginButton")
  .addEventListener("click", async (event) => {
    event.preventDefault();

    // Captura os valores dos campos de input
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    try {
      // Faz a requisição para o endpoint do login
      const response = await fetch(
        `http://localhost:8080/api/administradores/login/${username}/${password}`,
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
