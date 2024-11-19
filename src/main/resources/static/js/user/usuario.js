

async function CadastrarUsuario(evt) {
    evt.preventDefault();
    const form = document.getElementById('formCadastroUsuario');
    
    const dadosObj = {
      nome: form.nome.value,
      nickname: form.nickname.value,
      dataNascimento: form.dtNascimento.value,
      email: form.email.value,
      telefone: form.telefone.value,
      senha: form.senha.value,
   };
    console.log(JSON.stringify(dadosObj));
    
    const httpResp = await fetch('/api/usuario/cadastro' , {
        method: 'post',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(dadosObj)
    });
    if (!httpResp.ok) {
        if (httpResp.status !== 400) {
            alert('Erro no envio dos dados - ' + httpResp.status);
            return;
        }
        const erros = await httpResp.json();
        
    }
    alert('Usuario Cadastrado com Sucesso');
   window.location.href = "/login.html";
    
}

async function AlterarUsuario(evt) {
    evt.preventDefault();
    const formAlterar = document.getElementById('formAlterarUsuario');
    const altUsuario = JSON.parse(sessionStorage.getItem('userData'));
    const idUsuarioAlterar = altUsuario.id;
    const dadosObjAlterar = {
        id: idUsuarioAlterar,
        nome: formAlterar.nomeAlterarUsuario.value,
        nickname: formAlterar.nicknameAlterarUsuario.value,
        dataNascimento: formAlterar.nascimentoAlterarUsuario.value,
        email: formAlterar.emailAlterarUsuario.value,
        telefone: formAlterar.telefoneAlterarUsuario.value,
        senha: formAlterar.senhaAlterarUsuario.value,
      };
    const httpResp = await fetch(`/api/usuario/alterar/${idUsuarioAlterar}` , {
        method: 'put',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(dadosObjAlterar)
    });
    if (!httpResp.ok) {
        if (httpResp.status !== 400) {
            alert('Erro no envio dos dados - ' + httpResp.status);
            return;
        }
        const erros = await httpResp.json();
        
    }
    alert('Usuario Alterado com Sucesso');
    sessionStorage.setItem('userData',JSON.stringify(dadosObjAlterar))
    window.location.href = "/usuario/informacoes-usuario.html";
    
}

async function ExcluirUsuario(evt) {
    evt.preventDefault();
    const formAlterar = document.getElementById('formExcluirUsuario');
    const exclurUser = JSON.parse(sessionStorage.getItem('userData'));
    const idUsuarioExcluir = exclurUser.id;
    alert("Teste")
    const httpResp = await fetch(`/api/usuario/excluir/${idUsuarioExcluir}` , {
        method: 'put',
        headers: {
            'content-type': 'application/json'
        },
    });
    if (!httpResp.ok) {
        if (httpResp.status !== 400) {
            alert('Erro no envio dos dados - ' + httpResp.status);
            return;
        }
        const erros = await httpResp.json();
        
    }
    alert('Usuario Excluido com Sucesso');
    sessionStorage.removeItem('userData')
    window.location.href = "/index.html";
    
}





async function LoginUsuario(evt) {
    evt.preventDefault();
    const form = document.getElementById('formLoginUsuario');
    const nickname = form.nicknameLoginUsuario.value;
    const senha = form.senhaLoginUsuario.value;
    try {
        if (!nickname || !senha ) {
           alert('Apelido ou Senha não informado');
           return;
        }
        const httpResp = await fetch('/api/usuario/login/' + nickname +'/' + senha);
        if (!httpResp.ok) {
          alert('Dados não foram carregados');
          return;
        }
        const dados = await httpResp.json();
        sessionStorage.setItem('userData', JSON.stringify(dados));
        window.location.href = "/usuario/informacoes-usuario.html";
        alert("Logado com Sucesso")

      }catch(err) {
        alert('Requsição HTTP deu erro');
      }

}




async function InfoUsuario() {
    const usuarioLogado = JSON.parse(sessionStorage.getItem('userData'));
    document.getElementById('nomeUsuario').innerText += " "+usuarioLogado.nome;
    document.getElementById('nicknameUsuario').innerText += " "+usuarioLogado.nickname;
    document.getElementById('emailUsuario').innerText += " "+usuarioLogado.email;
    document.getElementById('telUsuario').innerText += " "+usuarioLogado.telefone;
    document.getElementById('nascimentoUsuario').innerText += usuarioLogado.dataNascimento;
}


async function preencherAlterarDados() {
    const usuarioLogado = JSON.parse(sessionStorage.getItem('userData'));
    document.getElementById('nomeAlterarUsuario').value += " "+usuarioLogado.nome;
    document.getElementById('nicknameAlterarUsuario').value += " "+usuarioLogado.nickname;
    document.getElementById('emailAlterarUsuario').value += " "+usuarioLogado.email;
    document.getElementById('telefoneAlterarUsuario').value += " "+usuarioLogado.telefone;
    document.getElementById('nascimentoAlterarUsuario').value += usuarioLogado.dataNascimento;
}

async function indexLogado() {
    const usuarioLogado = JSON.parse(sessionStorage.getItem('userData'));
    if(usuarioLogado.nome != null){
       const minhaDiv = document.getElementById('loginEntrar');
       
       // Seleciona todos os botões dentro da div pai
       var botoes = minhaDiv.querySelectorAll("button");
       // Remove cada botão
       botoes.forEach(function(botao) {
            minhaDiv.removeChild(botao);
       });

       const botao1 = document.createElement('a');
       botao1.id = "minhaConta"
       botao1.classList.add("btn", "btn-outline-light", "me-2");
       botao1.setAttribute("href","/usuario/informacoes-usuario.html")
       botao1.innerHTML += `<i class="fa-regular fa-user fa-lg" style="color: #646973;"></i> ${"Athyla"}`
       minhaDiv.appendChild(botao1);

       const botao = document.createElement('button');
       botao.id = "sair"
       botao.classList.add("btn", "btn-outline-light");
       botao.innerHTML += '<i class="fa-solid fa-right-to-bracket fa-lg" style="color: #696969;"></i> Sair'
       minhaDiv.appendChild(botao);
       botao.addEventListener("click", Sair)
    }
}

async function Sair(){
    sessionStorage.removeItem('userData')
    window.location.href = "/index.html";
}












