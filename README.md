# SKILLFORGE

Documentação do Projeto Final

Visão Geral
Este projeto foi desenvolvido com o objetivo de criar uma aplicação robusta e escalável, composta por três módulos principais:

Hard Skills: Um sistema para teste de habilidades técnicas de programação.
Soft Skills: Um jogo interativo que aborda diversidade e inclusão.

A aplicação utiliza uma arquitetura baseada em micro serviços, com um backend desenvolvido em Java e um BFF em python, com o frontend em React, além de um banco de dados relacional para armazenamento de informações.

Arquitetura do Sistema
Descrição Geral

A arquitetura do sistema segue o modelo cliente-servidor, com separação clara entre o frontend e o backend. O backend é responsável por fornecer APIs RESTful para o frontend, que consome esses serviços para exibir as informações ao usuário.
Componentes Principais.

Frontend
Desenvolvido em React.
Responsável pela interface do usuário e pela interação com as APIs do backend.
Hospedada em um serviço de armazenamento estático, como Amazon S3 ou CloudFront.
Backend for Frontend (BFF)
Desenvolvido em Python 3.13.1.
Responsável pela conexão entre o java e o react.

Backend
Desenvolvido em Java usando Spring Boot.
Responsável pela lógica de negócios, manipulação de dados e fornecimento de APIs RESTful.
Hospedada em uma instância EC2 da AWS.

Banco de Dados
PostgreSQL como banco de dados relacional.
Armazena informações relacionadas aos módulos do sistema, como desafios de programação, cenários de soft skills e registros de ponto.

Hospedagem e Infraestrutura
Backend hospedado em uma instância EC2 da AWS.
Banco de dados configurado na mesma instância ou em um serviço gerenciado como Amazon RDS.
Proxy reverso opcional configurado com Nginx para gerenciar o tráfego.

Tecnologias Utilizadas

Frontend
Linguagem: JavaScript (React)
Bibliotecas: React Router, Axios
Hospedagem: Amazon S3 ou CloudFront

Backend
Linguagem: Python 3.13.1 e Java
Framework: Fast API
Gerenciamento de Dependências: pip e venv
Hospedagem: AWS EC2
Servidor de Aplicação: Uvicorn
Proxy Reverso (opcional): Nginx
Hospedagem: Container Docker em uma instância EC2 da AWS.

Banco de Dados
Sistema Gerenciador de Banco de Dados (SGBD): PostgreSQL
Conexão: Configurada via SQLAlchemy

Infraestrutura
Provedor de Nuvem: AWS
Serviços Utilizados: 
EC2 para hospedagem do backend.
S3/CloudFront para hospedagem do frontend.
RDS (opcional) para banco de dados gerenciado.

Arquitetura Detalhada

Fluxo de Dados
O usuário interage com o frontend (React), que conectado ao sistema em python  faz chamadas às APIs RESTful do backend.
O backend processa as requisições, aplica a lógica de negócios e interage com o banco de dados para recuperar ou armazenar informações.
As respostas são enviadas de volta ao python que manda para o frontend, que exibe os dados ao usuário.

Diagrama de Arquitetura
[Usuário] → [Frontend (React)] → [BFF[(Python)] → [Backend (FastAPI)] → [Banco de Dados (PostgreSQL)]

Contêinerização
Todos os componentes do backend (BFF e backend principal) foram empacotados em containers Docker para garantir portabilidade e consistência no ambiente de execução.
O uso de contêineres facilita o gerenciamento e a escalabilidade da aplicação, permitindo que os serviços sejam implantados de forma independente.
