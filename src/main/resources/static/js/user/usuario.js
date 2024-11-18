

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
    const idUser = altUsuario.id
    const dadosObjAlterar = {
      nome: formAlterar.nomeAlterarUsuario.value,
      nickname: formAlterar.nicknameAlterarUsuario.value,
      email: formAlterar.emailAlterarUsuario.value,
      dataNascimento: formAlterar.nascimentoAlterarUsuario.value,
      telefone: formAlterar.telefoneAlterarUsuario.value
   };
    alert(idUser)
    const httpResp = await fetch(`/api/usuario/alterar/${idUser}` , {
        method: 'put',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(dadosObjAlterar)
    });
    alert(JSON.stringify(dadosObjAlterar))
    if (!httpResp.ok) {
        if (httpResp.status !== 400) {
            alert('Erro no envio dos dados - ' + httpResp.status);
            return;
        }
        const erros = await httpResp.json();
        
    }
    alert('Usuario Alterado com Sucesso');
    window.location.href = "/usuario/informacoes-usuario";
    
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
}












