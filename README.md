# wallet-architecture

Exemplo de arquitetura de uma wallet utilizado Java com Spring boot, RabbitMQ, MongoDB, Docker, Redis e Nginx.

## Tecnologias utilizadas no projeto:
        - Java
        - Spring Boot
        - RabbitMQ
        - Docker
        - Nginx
        - MongoDb
        - Redis

 ## O que é necessário para rodar esta demonstração:
      - Docker
      - Docker-compose
      - Java para o build através do maven
      - shell script (no caso de linx ou MacOS)
      - cmd (No caso de Windows)
      - acesso a internet para que as imagens sejam baixadas do docker hub
      
## Como rodar esta demonstração
   - #### Na raiz do projeto constam dois arquivos:
        - start.sh
        - start.bat
   - #### Como executar de acordo com o sistema operacional:
       - Linx: ./start.sh
       - MacOS:./start.sh
       - Windows: Clicar duas vezes no arquivo start.sh caso seja windows com o wsl2 instalado.
 
   - #### Como executar manualmente esta demonstração (Windows, Linux e MAC)
       1.    acessar a pasta bill-payment e executar o comando mvnw clean compile package
       2.    acessar a pasta wallet-timeline e executar o comando mvnw clean compile package
       3.    acessar a pasta wallet-user e executar o comando mvnw clean compile package
       4.    voltar para o diretório raiz do projeto (onde consta este README) E executar o comando docker-compose up -d --remove-orphans

## Considerações importantes sobre performance
 > Esta demonstração utiliza ao todo 10 containers, o que para certas máquinas pode gerar lentidão ou o não correto funcionamento da aplicação.
 > * Nestes caso remova do arquivo docker-compose.yml os seguintes services:  wallet-timeline-2, bill-payment-2 e wallet-user-app-2.
 > * Também remova das configurações do nginx este servidores, remover no arquivos nginx.conf os seguintes servidores  server wallet-user-app-2:8080,  server wallet-timeline-2:8080 e server bill-payment-2:8080.
 > 
  -  ## Considerações sobre este modelo de arquitetura::
- Esta sendo utilizado o Nginx como load balancer e proxy reverso, desta formas as apis se portas das aplicações não serão expostas para fora.
- O Nginx támbem expõe url difrentes que são direcionadas por ele para cada uma das aplicações.
- Foram criados dois containers de cada uma das aplicações para a aplicação prática do load balancer do Nginx.
- A constução das aplicações é efetudas através do docker-compose, onde são construídas todas as imagens.
- Foi criado apenas um container mongo db onde todas as aplicações tem sua base de dados. Isto foi efetuado para poupar recursos computacionais.
- Foi o RabbitMQ para a troca de mensagens assíncronas entre os sistemas.
- Foi desenvolvida uma aplicação separada para prover as consultas de timeline do usuiário. Esta decisão foi tomada para que as operações de consulta, que são mais custosas, não sejam efetudas na mesma aplicação ou base de dados.

## Sobre as aplicações e seu funcionamento:

### wallet-user:
- fornece um endpoint para que seja criados usuários, onde será possível executar as aperações de saque, depósito, transferência entre usuários.
- Tecnicas utilizadas nesta aplicação:
- Desenvolvidos testes unitários e teste de integração com o RabbitMQ (treste de integração está no package integration do diretório de testes)
- Foram fornecidos endpoints através para as operações citadas acima.
- Toda operação financeira executada (saque, depósito e transferência) geram uma evento de movimentação financeira que alimenta uma fila do RabbitMQ, atentendo desta forma a uma comunicação  assíncrona.
- Todas as movimentação financeiras e a troca de saldo do usuário são armazenados no banco de dados mongodb.
- Para tornar este exemplo funcional foi criado um endpoint de criação de usuário, permitindo assim que as operações sejam efetuadas.
  
### wallet-timeline:
- A aplicação wallet-timeline consome a fila do RabbitMQ onde existe as movimentações financeiras do usário e popula sua base de dados.
- O objetivo desta aplicação é não sobrecarregar a api da carteira de usuário com operações de consulta, por este motivo ela consome os eventos que a aplicação wallet-user coloca na fila do RabbitMQ.
- Para otimizar a performance nas consultas, a consulta de timeline está com cache configurado no Redis.
  
 
### bill-pyament:
- A aplicação bill-payment executa uma operação de pagamento de fatura, para isto ela consome a api de usuário para verificar se o usuário existe.
- Não existe nenhuma regra de validaÇão ou envio para outro lugar desta aplicação, a mesma é apenas para demonstrar a comunicação através do feign client.
## Operações das APIs:
#### Criar usuário:
- **URL:**http://localhost:8000/api/v1/user/create
- **MÉTODO:** POST
- **BODY:** 
```json
{
  "name": "Usuario 1",
  "username": "usuario1",
  "wallet": {
    "balance": 1000.00
  }
``` 
- **RESPONSE BODY:** 
```json
{
    "id": "61932251f24305070d519a48",
    "username": "fulano",
    "name": "Fulano de tal",
    "wallet": {
        "balance": 1666.00
    }
}
```       
- **EXEMPLO DE CHAMADA**:  
```console
curl --location --request POST 'http://localhost:8000/api/v1/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Usuario 1",
  "username": "usuario1",
  "wallet": {
    "balance": 1000.00
  }
}'
```
#### Depositar na conta do usuário:
- **URL:**http://localhost:8000/api/v1/wallet/deposit
- **MÉTODO:** POST
- **BODY:** 
```json
{
    "username":  "usuario1",
    "amountToDeposit" : 1500
}
```   
- **RESPONSE BODY:** 
```json
{
    "movementIdentifier": "49a22872-51d1-424a-81a0-6c576f0fc3e2",
    "dateOfFinancialMovement": "2021-11-16T03:16:40.170+00:00",
    "financialMovementType": "DEPOSIT",
    "userId": "61932251f24305070d519a48",
    "amount": 333.0
}
```     
- **EXEMPLO DE CHAMADA**:  
```console
curl --location --request POST 'http://localhost:8000/api/v1/wallet/deposit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":  "usuario1",
    "amountToDeposit" : 1500
}'
```

#### Sacar da conta do usuário:
  - **URL:**http://localhost:8000/api/v1/wallet/withdraw
  - **MÉTODO:** POST
  - **BODY:** 
```json
{
    "username":  "usuario1",
    "amountToWithdraw" : 1500
}
```     
- **RESPONSE BODY:** 
```json
{
    "movementIdentifier": "6dc77b5d-69b8-4785-923c-334918a2198f",
    "dateOfFinancialMovement": "2021-11-16T03:22:46.953+00:00",
    "financialMovementType": "WITHDRAW",
    "userId": "619323f3b7d1275de31110eb",
    "amount": 999.0
}
```  
- **EXEMPLO DE CHAMADA**:  
```console
curl --location --request POST 'http://localhost:8000/api/v1/wallet/withdraw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":  "usuario1",
    "amountToWithdraw" : 999.00
}'
```

#### Transferir dinheiro entre usuários:
- **URL:**http://localhost:8000/api/v1/wallet/money-transfer
- **MÉTODO:** POST
- **BODY:** 
```json
{
    "userTo":  "usuario2",
    "userFrom":  "usuario1",
    "moneyTransferAmount" : 200.98
}
``` 
- **RESPONSE BODY:** 
```json
{
    "movementIdentifier": "54622159-7297-4e94-a07a-6392bae8dfd1",
    "dateOfFinancialMovement": "2021-11-16T03:25:38.571+00:00",
    "financialMovementType": "MONEY_TRANSFER",
    "userId": "6193249ef59d0e1410afab09",
    "amount": 200.98
}
```      
- **EXEMPLO DE CHAMADA**:  
```console
curl --location --request POST 'http://localhost:8000/api/v1/wallet/money-transfer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userTo":  "usuario2",
    "userFrom":  "usuario1",
    "moneyTransferAmount" : 10.98
}'
```

#### Consultar timeline do usuário:
  - **URL:**http://localhost:8000/api/v1/timeline/user/{userid}
  - **MÉTODO:** GET
  - **URL PARAMETER:**: Este parâmetro é o código do usuário que é obtido atrvés da criação do usuário


- **RESPONSE BODY:** 
```json
[
    {
        "id": "6193226b0f9ada5107f43cd0",
        "movementIdentifier": "9e1d4579-f212-4005-96d4-e1b29f887c4a",
        "dateOfFinancialMovement": "2021-11-16T03:15:55.552+00:00",
        "financialMovementType": "DEPOSIT",
        "userId": "61932251f24305070d519a48",
        "amount": 333.0
    },
    {
        "id": "6193227ff24305070d519a49",
        "movementIdentifier": "a5c205f3-a6fe-437d-934e-3c82a8949ede",
        "dateOfFinancialMovement": "2021-11-16T03:16:15.133+00:00",
        "financialMovementType": "DEPOSIT",
        "userId": "61932251f24305070d519a48",
        "amount": 333.0
    },
    {
        "id": "61932295f24305070d519a4a",
        "movementIdentifier": "c2eb48c4-8164-483f-b4ed-09c7aa3bc042",
        "dateOfFinancialMovement": "2021-11-16T03:16:37.980+00:00",
        "financialMovementType": "DEPOSIT",
        "userId": "61932251f24305070d519a48",
        "amount": 333.0
    },
    {
        "id": "619322960f9ada5107f43cd1",
        "movementIdentifier": "a582514e-3ec6-4932-86c7-ab6fdc895771",
        "dateOfFinancialMovement": "2021-11-16T03:16:38.884+00:00",
        "financialMovementType": "DEPOSIT",
        "userId": "61932251f24305070d519a48",
        "amount": 333.0
    },
    {
        "id": "61932297f24305070d519a4b",
        "movementIdentifier": "5e59d71d-db96-4364-8e0d-c9bf0dcbdca6",
        "dateOfFinancialMovement": "2021-11-16T03:16:39.543+00:00",
        "financialMovementType": "DEPOSIT",
        "userId": "61932251f24305070d519a48",
        "amount": 333.0
    },
    {
        "id": "619322980f9ada5107f43cd2",
        "movementIdentifier": "49a22872-51d1-424a-81a0-6c576f0fc3e2",
        "dateOfFinancialMovement": "2021-11-16T03:16:40.170+00:00",
        "financialMovementType": "DEPOSIT",
        "userId": "61932251f24305070d519a48",
        "amount": 333.0
    }
]
```       
- **EXEMPLO DE CHAMADA**:  
```console
curl --location --request GET 'http://localhost:8000/api/v1/timeline/user/61932251f24305070d519a48'
```


#### Pagamento de fatura:
  - **URL:**http://localhost:8000/api/v1/wallet/withdraw
  - **MÉTODO:** POST
  - **BODY:** 
```json
{
    "username":  "fulano",
    "amount" : 500,
    "barCode":  "123456789012555444332221568754456745"
}
```     
- **RESPONSE BODY:** 
```json
{
    "id": "619322b2220bee1348cabbf5",
    "billPayment": {
        "username": "fulano",
        "amount": 500.0,
        "barCode": "123456789012555444332221568754456745"
    },
    "financialMovement": {
        "movementIdentifier": "4ede4da5-10b7-4f81-8f6d-1b51ca11a126",
        "dateOfFinancialMovement": "2021-11-16T03:17:06.541+00:00",
        "financialMovementType": "BILL_PAYMENT",
        "userId": "61932251f24305070d519a48",
        "amount": 500.0
    },
    "orderStatus": "PENDING"
}
```  
- **EXEMPLO DE CHAMADA**:  
```console
curl --location --request POST 'http://localhost:8000/api/v1/wallet/withdraw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":  "usuario1",
    "amountToWithdraw" : 999.00
}'
```

# TOPOLOGIA:
![Topologia](https://github.com/edercnj/wallet-architecture/blob/master/topologia-wallet.svg)
