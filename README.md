# 🧾 Sistema de Emissão de Orçamentos  
Aplicação web desenvolvida em **Java 17** com **Spring Boot**, usando **Thymeleaf** como engine de templates para renderização de páginas HTML e geração de arquivos **PDF** contendo orçamentos profissionais.

Este projeto foi criado com foco em simplicidade e produtividade, permitindo que a empresa configure seus dados (logo, nome, CNPJ, conta bancária) e gere orçamentos personalizados para seus clientes de forma rápida e organizada.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Thymeleaf**
- **Maven**
- **OpenPDF / iText** (para geração de PDF)
- **HTML + CSS**
- **Render (para deploy futuro em ambiente cloud)**
- **IntelliJ IDEA** (IDE utilizada no desenvolvimento)

---

## 💻 Sobre o Desenvolvimento

Este projeto foi desenvolvido utilizando a **IDE IntelliJ IDEA**, escolhida por sua excelente integração com Maven, Spring Boot e ferramentas de produtividade.

Durante o desenvolvimento, alguns desafios foram enfrentados:

### 🧩 Desafios e Dificuldades

- **Primeiro contato com Thymeleaf:**  
  Aprender a conectar formulários HTML com objetos Java através do `th:object` e `th:field` exigiu prática, especialmente para manter a validação e o binding funcionando corretamente.

- **Upload e leitura de arquivos (logo da empresa):**  
  Foi necessário entender como o Spring trata `MultipartFile`, como salvar imagens no sistema e como carregá-las no template.

- **Servir arquivos PDF de forma dinâmica:**  
  Gerar o PDF foi a primeira parte. A segunda foi torná-lo acessível via navegador, configurando corretamente o:
  - `static-locations`
  - diretórios dinâmicos
  - caminhos absolutos/relativos

- **Persistência de arquivos no ambiente de deploy (Render):**  
  No Render, o sistema de arquivos é temporário.  
  A solução foi implementar **detecção de ambiente** e usar:
  - `/var/data` no Render (persistente)
  - `./data/` localmente  
  Isso garantiu compatibilidade e confiabilidade em produção.

Essas dificuldades fizeram parte do aprendizado e reforçaram a compreensão sobre:
- templates server-side  
- manipulação de arquivos  
- estrutura MVC no Spring  
- deploy de aplicações Java modernas  

---

## 🎉 Primeiro Projeto Usando Thymeleaf

Este foi **o primeiro projeto** utilizando *Thymeleaf* como motor de renderização.  
Alguns aprendizados marcantes:

- Thymeleaf é poderoso, simples e totalmente integrado ao Spring Boot.
- A sintaxe `th:text`, `th:href`, `th:if` e `th:each` permite controlar totalmente o conteúdo da página.
- A ligação **bidirecional entre HTML e objetos Java** facilita muito a criação de formulários.
- O template é renderizado no servidor, garantindo segurança e flexibilidade.

---

## 📂 Funcionalidades do Sistema

✔ Configuração da empresa (nome, CNPJ, conta bancária, logotipo)  
✔ Armazenamento persistente dos dados  
✔ Cadastro de cliente  
✔ Descrição do serviço / orçamento  
✔ Geração automática de arquivo **PDF profissional**  
✔ Layout limpo e espaçamentos aprimorados  
✔ Botões de:
- Download do PDF
- Impressão
- Nova emissão  

🌐 Aplicação em Produção
A aplicação está atualmente rodando em um ambiente de Cloud para testes e demonstração.

Acesse aqui: https://orcamento-web-btdc.onrender.com
