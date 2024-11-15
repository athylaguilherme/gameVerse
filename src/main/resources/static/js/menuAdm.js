fetch('../adm/menuAdm.html')
.then(response => response.text())
.then(data => {
    document.getElementById('menuAdm').innerHTML = data;
});


fetch('..../adm/cadastrarPlataforma.html')
.then(response => response.text())
.then(data => {
    document.getElementById('mdCadastoPlataforma').innerHTML = data;
});

fetch('../adm/AtualizarPlataforma.html')
.then(response => response.text())
.then(data => {
    document.getElementById('mdAtualizarPlataforma').innerHTML = data;
});

fetch('../adm/ExcluirPlataforma.html')
.then(response => response.text())
.then(data => {
    document.getElementById('mdExcluirPlataforma').innerHTML = data;
});

fetch('../adm/ExcluirJogo.html')
.then(response => response.text())
.then(data => {
    document.getElementById('mdExcluirJogo').innerHTML = data;
});

fetch('../adm/cadastrarAdm.html')
.then(response => response.text())
.then(data => {
    document.getElementById('mdCadastrarAdm').innerHTML = data;
});

fetch('../adm/alterarSenhaAdm.html')
.then(response => response.text())
.then(data => {
    document.getElementById('mdAlterarSenhaAdm').innerHTML = data;
});

fetch('../adm/excluirAdm.html')
.then(response => response.text())
.then(data => {
    document.getElementById('mdExcluirADM').innerHTML = data;
});

fetch('../adm/minhaContaAdm.html')
.then(response => response.text())
.then(data => {
    document.getElementById('mdMinhaContaAdm').innerHTML = data;
});

 fetch('../adm/atualizarDadosAdm.html')
 .then(response => response.text())
 .then(data => {
     document.getElementById('mdAtualizarDadosAdm').innerHTML = data;
 });









