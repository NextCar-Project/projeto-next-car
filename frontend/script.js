const API_USUARIO = "http://localhost:8080/usuario";
const API_VEICULO = "http://localhost:8080/veiculos";
const API_VENDA = "http://localhost:8080/vendas";

async function salvarUsuario() {

    const usuario = {
        nome: document.getElementById("nome").value,
        login: document.getElementById("login").value,
        senha: document.getElementById("senha").value
    };

    await fetch(API_USUARIO, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
    });

    listarUsuarios();
}async function salvarVeiculo() {

    const veiculo = {
        marca: document.getElementById("marca").value,
        modelo: document.getElementById("modelo").value,
        ano: Number(document.getElementById("ano").value),
        preco: Number(document.getElementById("preco").value),
        tipo: document.getElementById("tipo").value,
        status: document.getElementById("status").value
    };

    await fetch(API_VEICULO, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(veiculo)
    });

    listarVeiculos();
}async function salvarVenda() {

    const venda = {
        data: document.getElementById("dataVenda").value,
        valorFinal: Number(document.getElementById("valorFinal").value),
        veiculo: {
            id: Number(document.getElementById("veiculoId").value)
        },
        usuario: {
            id: Number(document.getElementById("usuarioId").value)
        }
    };

    await fetch(API_VENDA, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(venda)
    });

    listarVendas();
}

async function listarUsuarios() {

    const resposta = await fetch(API_USUARIO);
    const usuarios = await resposta.json();

    const div = document.getElementById("usuarios");

    div.innerHTML = "";

    usuarios.forEach(usuario => {

        div.innerHTML += `
            <div class="item">
                <strong>ID:</strong> ${usuario.id} <br>
                <strong>Nome:</strong> ${usuario.nome} <br>
                <strong>Email:</strong> ${usuario.login}
            </div>
        `;
    });
} async function listarVeiculos() {

    const resposta = await fetch(API_VEICULO);
    const veiculos = await resposta.json();

    const div = document.getElementById("veiculos");

    div.innerHTML = "";

    veiculos.forEach(veiculo => {

        div.innerHTML += `
            <div class="item">
                <strong>ID:</strong> ${veiculo.id} <br>
                <strong>Marca:</strong> ${veiculo.marca} <br>
                <strong>Modelo:</strong> ${veiculo.modelo} <br>
                <strong>Ano:</strong> ${veiculo.ano} <br>
                <strong>Preço:</strong> R$ ${veiculo.preco} <br>
                <strong>Status:</strong> ${veiculo.status}
            </div>
        `;
    });
} async function listarVendas() {

    const resposta = await fetch(API_VENDA);
    const vendas = await resposta.json();

    const div = document.getElementById("vendas");

    div.innerHTML = "";

    vendas.forEach(venda => {

        div.innerHTML += `
            <div class="item">
                <strong>ID:</strong> ${venda.id} <br>
                <strong>Data:</strong> ${venda.data} <br>
                <strong>Valor:</strong> R$ ${venda.valorFinal} <br>
                <strong>Veículo:</strong> ${venda.veiculo.modelo} <br>
                <strong>Usuário:</strong> ${venda.usuario.nome}
            </div>
        `;
    });
}

listarUsuarios();
listarVeiculos();
listarVendas();