# Email Scheduler API

Este projeto implementa uma API REST/Messageria para envio de e-mails agendados. Utiliza Spring Boot e Java para gerenciar e-mails de forma assíncrona e escalável.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Mail** (para envio de e-mails)
- **Spring Scheduler** (para agendamento)
- **RabbitMQ** (para comunicação entre os serviços)
- **Jackson** (para manipulação de JSON)

## 📦 Instalação

### Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- **JDK 17** ou superior
- **Maven**
- Um servidor SMTP configurado (ex: Gmail, SendGrid, Mailtrap)

### Configuração do SMTP

No arquivo `application.properties`, configure os detalhes do seu servidor SMTP:

```properties
spring.mail.host=smtp.exemplo.com
spring.mail.port=587
spring.mail.username=seu-email@exemplo.com
spring.mail.password=sua-senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> **⚠️ Importante:** Evite expor credenciais no código-fonte. Utilize variáveis de ambiente ou serviços de gerenciamento de configurações.

## ▶️ Como Rodar o Projeto

1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/email-scheduler-api.git
   cd email-scheduler-api
   ```
2. Compile e execute:
   ```sh
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`.

## 📌 Endpoints

### 1. Enviar um e-mail agendado

- **URL:** `POST /emails`
- **Descrição:** Agenda o envio de um e-mail para um horário específico.
- **Corpo da Requisição:**

```json
{
  "to": "usuario@example.com",
  "subject": "Tarefa Agendada",
  "body": "Este é um e-mail automático.",
  "sendAt": "2025-03-07T10:30:00"
}
```

- **Resposta de Sucesso:** `201 Created`

### 2. Listar e-mails agendados

- **URL:** `GET /emails`
- **Descrição:** Retorna uma lista dos e-mails agendados.

## 🛠️ Melhorias Futuras

- Implementação de banco de dados para persistência de e-mails.
- Suporte para múltiplos provedores de e-mail.
- Painel de administração para monitoramento dos envios.


---

