<h1 align="center"> Next Car </h1>

<p align="center">
Projeto desenvolvido durante a disciplina de Análise e Projeto de Software.
</p>

<p align="center">
  <a href="#-documentação">Documentação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#%EF%B8%8F-funcionalidades">Funcionalidades</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-fluxo-do-sistema">Fluxo do Sistema</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#%EF%B8%8F-arquitetura-c4-model">Arquitetura (C4 Model)</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-estrutura-da-documentação">Estrutura da Documentação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-integrantes">Integrantes</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-licença">Licença</a>
</p>

<!-- <p align="center">
  <a href="#-documentação">Documentação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#%EF%B8%8F-funcionalidades">Funcionalidades</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-fluxo-do-sistema">Fluxo do Sistema</a>
</p>

<p align="center">
  <a href="#%EF%B8%8F-arquitetura-c4-model">Arquitetura (C4 Model)</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-integrantes">Integrantes</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-estrutura-da-documentação">Estrutura da Documentação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-licença">Licença</a>
</p> -->

<p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT&color=49AA26&labelColor=000000">
</p>

<br>

<p align="center">
  <img alt="img" src="assets/Logo_NextCar.png" width="100%">
</p>

## 🌐 Documentação

## 💻 Projeto

O Next Car é um sistema desenvolvido para gerenciamento e venda de carros seminovos, criado com o objetivo de aplicar conceitos avançados de Análise e Projeto de Software em um cenário real. O projeto utiliza arquitetura moderna, padrões de design e boas práticas de desenvolvimento, além de métricas e testes automatizados para garantir qualidade, organização e escalabilidade da aplicação.

## 🚀 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

- Java
- Spring Boot
- MySQL
- IntelliJ
- Postman
- Git e Github


## 🛠️ Funcionalidades

- Gestão de Inventário: Registo completo de veículos, incluindo marca, modelo, ano, quilometragem e especificaçõesoes técnicas.
- Módulo de Vendas: Fluxo estruturado para o registo de transaçõoes e histórico de negociações.
- Painel Administrativo: Visualização rápida de métricas de entrada e saída, facilitando o controlo do stock.
- Interface Otimizada: Foco na experiência do utilizador para agilizar o processo de gestão e venda.

## 🧭 Fluxo do Sistema

``` mermaid
flowchart LR

A[Cadastro de Veículo] --> B[Entrada no Inventário]
B --> C[Stock Disponível]
C --> D[Negociação / Reserva]
D --> E[Venda Confirmada]
E --> F[Atualização de Status: Vendido]
F --> G[Atualização do Painel Administrativo]
G --> H[Relatórios e Indicadores]
```

## 🏗️ Arquitetura (C4 Model)

## Contexto do Sistema

``` mermaid
flowchart LR

E[Empresário/Funcionário]  --> N[Next Car]


N --> DB[(Banco de Dados)]
N --> P[Processo de Registro]
N --> V[Vizualização dos dados]
```
## Containers

## Componentes

``` mermaid
flowchart LR

API[API da Aplicação]

Inventário[Gestão de Inventário]
Negociações[Registro de Negociações]


DB[(Banco de Dados)]

API --> Inventário
API --> Negociações

Inventário --> DB
Negociações --> DB
```

## 📂 Estrutura da Documentação

## 👨‍💻 Integrantes

<table>
  <tr>
    <td align="center">
      <img src="assets/Emerson.png" width="120px;" alt="Foto do Emerson"/><br>
      <a href="https://www.linkedin.com/in/emerson-willian-19984630b/" target="_blank">
        <sub><b>Emerson Dias</b></sub>
      </a>
    </td>
    <td align="center">
      <img src="assets/Lucas.png" width="120px;" alt="Foto do Lucas"/><br>
      <a href="https://www.linkedin.com/in/lucas-hudson-1245bb409/" target="_blank">
        <sub><b>Lucas Hudson</b></sub>
      </a>
    </td>
    <td align="center">
      <img src="assets/Marcela.png" width="120px;" alt="Foto da Marcela"/><br>
      <a href="https://www.linkedin.com/in/maria/" target="_blank">
        <sub><b>Marcela Cantalice</b></sub>
      </a>
    </td>
    <td align="center">
      <img src="assets/Marcos.png" width="120px;" alt="Foto do Marcos"/><br>
      <a href="https://www.linkedin.com/in/marcos-dalyson-9457373b3?utm_source=share_via&utm_content=profile&utm_medium=member_android" target="_blank">
        <sub><b>Marcos Dalyson</b></sub>
      </a>
    </td>
    <td align="center">
      <img src="assets/Mateus.png" width="120px;" alt="Foto do Mateus"/><br>
      <a href="https://www.linkedin.com/in/mateus-oliveira-172492213" target="_blank">
        <sub><b>Mateus Oliveira</b></sub>
      </a>
    </td>
    <td align="center">
      <img src="assets/Rosalvo.png" width="120px;" alt="Foto da Rosalvo"/><br>
      <a href="https://www.linkedin.com/in/rosalvo-alves-de-oliveira-filho-928521391/" target="_blank">
        <sub><b>Rosalvo Alves</b></sub>
      </a>
    </td>
      </a>
    </td>
  </tr>
</table>

## 📝 Licença

Esse projeto está sob a licença MIT.
