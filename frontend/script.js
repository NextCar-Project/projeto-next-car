const API = "http://localhost:8080/usuario";

async function salvarUsuario() {

    const nome = document.getElementById("nome").value;
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;

    const usuario = {
        nome,
        login,
        senha
    };

    await fetch(API, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
    });

    limparCampos();

    listarUsuarios();
}

async function listarUsuarios() {

    const resposta = await fetch(API);

    const usuarios = await resposta.json();

    const div = document.getElementById("usuarios");

    div.innerHTML = "";

    usuarios.forEach(usuario => {

        div.innerHTML += `
            <div class="usuario">
                <strong>ID:</strong> ${usuario.id} <br>
                <strong>Nome:</strong> ${usuario.nome} <br>
                <strong>Email:</strong> ${usuario.login}
            </div>
        `;
    });
}

function limparCampos() {

    document.getElementById("nome").value = "";
    document.getElementById("login").value = "";
    document.getElementById("senha").value = "";
}

listarUsuarios();