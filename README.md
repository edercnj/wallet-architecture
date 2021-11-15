# wallet-architecture

Exemplo de arquitetura de uma wallet utilizado Spring boot, RabbitMQ, MongoDB, Docker, Redis e Postgres.

## Tecnologias utilizadas no projeto:
 - Java
 - SpringBoot
 - Docker através do docker-compose
 - Nginx
 - MongoDb

 ## O que é necessário para rodar esta demonstração:
- Docker
- Docker-compose
- Java para o build através do maven
- shell script
- bat
 ## Consigerações sobre este modelo de arquitetura::
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
### wallet-timeline:
- A aplicação wallet-timeline consome a fila do RabbitMQ onde existe as movimentações financeiras do usário e popula sua base de dados.
- O objetivo desta aplicação é não sobrecarregar a api da carteira de usuário com operações de consulta, por este motivo ela consome os eventos que a aplicação wallet-user coloca na fila do rabbitMQ.
#### Operações da API da carteira do usuário:
##### Criar usuário:
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
##### Depositar na conta do usuário:
- **URL:**http://localhost:8000/api/v1/wallet/deposit
- **MÉTODO:** POST
- **BODY:** 
```json
{
    "username":  "usuario1",
    "amountToDeposit" : 1500
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

##### Sacar da conta do usuário:
- **URL:**http://localhost:8000/api/v1/wallet/withdraw
- **MÉTODO:** POST
- **BODY:** 
```json
{
    "username":  "usuario1",
    "amountToDeposit" : 1500
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

##### Transferir dinheiro entre usuários:
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