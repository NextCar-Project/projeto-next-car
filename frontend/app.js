// Constantes das APIs
const API_USUARIO = "http://localhost:8080/usuarios";
const API_VEICULO = "http://localhost:8080/veiculos";
const API_VENDA = "http://localhost:8080/vendas";

// ==========================================
// RECURSOS DE TEMA (CLARO/ESCURO) E VISIBILIDADE DA SENHA
// ==========================================

// Alternador de Tema
const themeToggleBtn = document.getElementById('theme-toggle');
const themeIcon = document.getElementById('theme-icon');

const savedTheme = localStorage.getItem('theme') || 'light';
document.documentElement.setAttribute('data-theme', savedTheme);
themeIcon.innerText = savedTheme === 'dark' ? 'light_mode' : 'dark_mode';

themeToggleBtn.addEventListener('click', () => {
    const currentTheme = document.documentElement.getAttribute('data-theme');
    let newTheme = 'light';

    if (currentTheme === 'light') {
        newTheme = 'dark';
        themeIcon.innerText = 'light_mode';
    } else {
        themeIcon.innerText = 'dark_mode';
    }

    document.documentElement.setAttribute('data-theme', newTheme);
    localStorage.setItem('theme', newTheme);
});

// Revelar/Ocultar Senha
const togglePasswordBtn = document.getElementById('toggle-password-btn');
const passwordInput = document.getElementById('usuario-senha');
const passwordIcon = document.getElementById('password-icon');

togglePasswordBtn.addEventListener('click', () => {
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        passwordIcon.innerText = 'visibility_off';
    } else {
        passwordInput.type = 'password';
        passwordIcon.innerText = 'visibility';
    }
});

// ==========================================
// BUSCA INTEGRADA EM TEMPO REAL (CORRIGIDO)
// ==========================================
document.getElementById('global-search').addEventListener('input', (e) => {
    const searchTerm = e.target.value.toLowerCase();
    const activeSection = document.querySelector('.content-section.active');
    if (!activeSection) return;

    const rows = activeSection.querySelectorAll('tbody tr');

    rows.forEach(row => {
        const rowText = row.innerText.toLowerCase();
        if (rowText.includes(searchTerm)) {
            row.style.display = ''; // Exibe a linha normalmente se bater com a busca
        } else {
            row.style.display = 'none'; // Esconde a linha caso não bata com a busca
        }
    });
});

// Limpa o campo de busca ao trocar de aba
function clearSearchInput() {
    const searchInput = document.getElementById('global-search');
    if (searchInput) {
        searchInput.value = '';
    }
}

// ==========================================
// CONTROLE DE NAVEGAÇÃO (SPA)
// ==========================================
document.querySelectorAll('.nav-btn').forEach(button => {
    button.addEventListener('click', () => {
        document.querySelectorAll('.nav-btn').forEach(b => b.classList.remove('active'));
        document.querySelectorAll('.content-section').forEach(s => s.classList.remove('active'));

        button.classList.add('active');
        const target = button.getAttribute('data-target');
        document.getElementById(target).classList.add('active');

        clearSearchInput();

        if (target === 'veiculos-sect') loadVeiculos();
        if (target === 'usuarios-sect') loadUsuarios();
        if (target === 'vendas-sect') loadVendas();
    });
});

function openModal(id) {
    document.getElementById(id).style.display = 'flex';
}

function closeModal(id) {
    document.getElementById(id).style.display = 'none';
    if(id === 'veiculoModal') document.getElementById('veiculo-form').reset();
    if(id === 'usuarioModal') {
        document.getElementById('usuario-form').reset();
        passwordInput.type = 'password';
        passwordIcon.innerText = 'visibility';
    }
    if(id === 'vendaModal') document.getElementById('venda-form').reset();
}

// ==========================================
// OPERAÇÕES DE VEÍCULOS
// ==========================================
async function loadVeiculos() {
    try {
        const res = await fetch(API_VEICULO);
        const veiculos = await res.json();
        const tbody = document.getElementById('veiculos-table-body');
        tbody.innerHTML = '';

        veiculos.forEach(v => {
            tbody.innerHTML += `
                <tr>
                    <td>${v.id}</td>
                    <td><strong>${v.marca}</strong></td>
                    <td>${v.modelo}</td>
                    <td>${v.ano}</td>
                    <td>R$ ${v.preco.toFixed(2)}</td>
                    <td>${v.tipo}</td>
                    <td><span class="status-tag">${v.status}</span></td>
                    <td>${v.tipoPreco}</td>
                    <td>
                        <button class="btn btn-edit" onclick="editVeiculo(${v.id}, '${v.marca}', '${v.modelo}', ${v.ano}, ${v.preco}, '${v.tipo}', '${v.status}', '${v.tipoPreco}')">Editar</button>
                        <button class="btn btn-delete" onclick="deleteVeiculo(${v.id})">Excluir</button>
                    </td>
                </tr>
            `;
        });
    } catch (err) {
        console.error("Erro ao carregar veículos:", err);
    }
}

document.getElementById('veiculo-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('veiculo-id').value;
    const veiculoData = {
        marca: document.getElementById('veiculo-marca').value,
        modelo: document.getElementById('veiculo-modelo').value,
        ano: parseInt(document.getElementById('veiculo-ano').value),
        preco: parseFloat(document.getElementById('veiculo-preco').value),
        tipo: document.getElementById('veiculo-tipo').value,
        status: document.getElementById('veiculo-status').value,
        tipoPreco: document.getElementById('veiculo-tipoPreco').value
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_VEICULO}/${id}` : API_VEICULO;

    await fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(veiculoData)
    });

    closeModal('veiculoModal');
    loadVeiculos();
});

function editVeiculo(id, marca, modelo, ano, preco, tipo, status, tipoPreco) {
    document.getElementById('veiculo-id').value = id;
    document.getElementById('veiculo-marca').value = marca;
    document.getElementById('veiculo-modelo').value = modelo;
    document.getElementById('veiculo-ano').value = ano;
    document.getElementById('veiculo-preco').value = preco;
    document.getElementById('veiculo-tipo').value = tipo;
    document.getElementById('veiculo-status').value = status;
    document.getElementById('veiculo-tipoPreco').value = tipoPreco;

    document.getElementById('veiculo-modal-title').innerText = "Editar Veículo";
    openModal('veiculoModal');
}

async function deleteVeiculo(id) {
    if (confirm("Tem certeza que deseja deletar este veículo?")) {
        await fetch(`${API_VEICULO}/${id}`, { method: 'DELETE' });
        loadVeiculos();
    }
}

// ==========================================
// OPERAÇÕES DE USUÁRIOS
// ==========================================
async function loadUsuarios() {
    try {
        const res = await fetch(API_USUARIO);
        const usuarios = await res.json();
        const tbody = document.getElementById('usuarios-table-body');
        tbody.innerHTML = '';

        usuarios.forEach(u => {
            tbody.innerHTML += `
                <tr>
                    <td>${u.id}</td>
                    <td><strong>${u.nome}</strong></td>
                    <td>${u.login}</td>
                    <td>
                        <button class="btn btn-edit" onclick="editUsuario(${u.id}, '${u.nome}', '${u.login}')">Editar</button>
                        <button class="btn btn-delete" onclick="deleteUsuario(${u.id})">Excluir</button>
                    </td>
                </tr>
            `;
        });
    } catch (err) {
        console.error("Erro ao carregar usuários:", err);
    }
}

document.getElementById('usuario-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('usuario-id').value;
    const usuarioData = {
        nome: document.getElementById('usuario-nome').value,
        login: document.getElementById('usuario-login').value,
        senha: document.getElementById('usuario-senha').value
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_USUARIO}/${id}` : API_USUARIO;

    await fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuarioData)
    });

    closeModal('usuarioModal');
    loadUsuarios();
});

function editUsuario(id, nome, login) {
    document.getElementById('usuario-id').value = id;
    document.getElementById('usuario-nome').value = nome;
    document.getElementById('usuario-login').value = login;
    document.getElementById('usuario-senha').placeholder = "Digite a nova senha";

    document.getElementById('usuario-modal-title').innerText = "Editar Usuário";
    openModal('usuarioModal');
}

async function deleteUsuario(id) {
    if (confirm("Deseja deletar este usuário?")) {
        await fetch(`${API_USUARIO}/${id}`, { method: 'DELETE' });
        loadUsuarios();
    }
}

// ==========================================
// OPERAÇÕES DE VENDAS
// ==========================================
async function loadVendas() {
    try {
        const res = await fetch(API_VENDA);
        const vendas = await res.json();
        const tbody = document.getElementById('vendas-table-body');
        tbody.innerHTML = '';

        vendas.forEach(v => {
            tbody.innerHTML += `
                <tr>
                    <td>${v.id}</td>
                    <td>${v.data}</td>
                    <td>${v.veiculoDescricao} (ID: ${v.veiculoId})</td>
                    <td>${v.usuarioNome} (ID: ${v.usuarioId})</td>
                    <td><strong>R$ ${v.valorFinal.toFixed(2)}</strong></td>
                    <td>
                        <button class="btn btn-delete" onclick="cancelarVenda(${v.id})">Cancelar Venda</button>
                    </td>
                </tr>
            `;
        });
    } catch (err) {
        console.error("Erro ao carregar vendas:", err);
    }
}

async function openVendaModal() {
    try {
        const [resVeiculos, resUsuarios] = await Promise.all([
            fetch(API_VEICULO),
            fetch(API_USUARIO)
        ]);

        const veiculos = await resVeiculos.json();
        const usuarios = await resUsuarios.json();

        const veiculoSelect = document.getElementById('venda-veiculo');
        const usuarioSelect = document.getElementById('venda-usuario');

        veiculoSelect.innerHTML = veiculos
            .filter(v => v.status.toLowerCase() === 'disponivel')
            .map(v => `<option value="${v.id}">${v.marca} ${v.modelo} - R$ ${v.preco}</option>`)
            .join('');

        usuarioSelect.innerHTML = usuarios
            .map(u => `<option value="${u.id}">${u.nome}</option>`)
            .join('');

        if(veiculoSelect.innerHTML === '') {
            alert("Não há veículos disponíveis para venda no momento!");
            return;
        }

        openModal('vendaModal');
    } catch (err) {
        console.error("Erro ao preparar dados da venda:", err);
    }
}

document.getElementById('venda-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const vendaData = {
        data: document.getElementById('venda-data').value,
        veiculoId: parseInt(document.getElementById('venda-veiculo').value),
        usuarioId: parseInt(document.getElementById('venda-usuario').value),
        valorFinal: 1.0 // Passa liso pela validação do DTO e o Service sobrescreve
    };

    try {
        const response = await fetch(API_VENDA, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(vendaData)
        });

        if (!response.ok) {
            const errorData = await response.json();
            alert("Erro ao salvar venda: " + (errorData.message || response.statusText));
            return;
        }

        closeModal('vendaModal');
        loadVendas();
    } catch (err) {
        console.error("Erro na requisição de venda:", err);
    }
});

async function cancelarVenda(id) {
    if (confirm("Deseja realmente cancelar este registro de venda?")) {
        await fetch(`${API_VENDA}/${id}`, { method: 'DELETE' });
        loadVendas();
    }
}

window.onload = () => {
    loadVeiculos();
};