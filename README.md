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
      - acesso a internet para que as imagens sejam baixadas do docker hub
      - Java 11 (caso deseje rodar as aplicações localmente)
      - bash ou cmd (para executar os comandos)
      
## Como rodar esta demonstração
   - #### Na raiz do projeto constam dois arquivos:
        - start.sh
        - start.bat
   - #### Como executar de acordo com o sistema operacional:
       - Linx: ./start.sh
       - MacOS:./start.sh
       - Windows: Caso possua uma versão recente de windows que reconheça sh basta clicar duas vezes no arquivo start.sh.
       -  Caso o windows não consiga exeuctar, efetuar procedimentos para execução manual.
 
   - #### Como executar manualmente esta demonstração (Windows, Linux e MAC)
       1. acessar a pasta bill-payment e executar o comando mvnw clean compile package
       2. acessar a pasta wallet-timeline e executar o comando mvnw clean compile package
       3. acessar a pasta wallet-user e executar o comando mvnw clean compile package
       4. voltar para o diretório raiz do projeto (onde consta este README) E executar o comando docker-compose up -d --remove-orphans
   - #### Como efetuar testes
     - Acessar as Apis que constam no final deste readme.
     - Através das Apis de user, criar dois usuários, que serão utilizados nos testes (importante anotar estes ids e usernames, pois serão usados em outros testes).
     - Efetuar testes de deposit (depósito);
     - Efetuar testes de withdraw(saque);
     - Efetuar testes de money_transfers(transferência);
     - Efetuar testes de bill_payments(pagamento de contas/faturas);
     - Efetuar testes de timelines (consultando através do id);

## Considerações importantes sobre performance
 > Esta demonstração utiliza ao todo 10 containers, o que para certas máquinas (com menos recursos) pode gerar lentidão ou o não correto funcionamento da demonstração.
 > * Neste caso remova do arquivo docker-compose.yml os seguintes services:  
 >      - wallet-timeline-2
 >      - bill-payment-2 
 >      - wallet-user-app-2
 > * Também remova das configurações do nginx (arquivo nginx.conf) este servidores:
 >      - wallet-user-app-2:8080
 >      - wallet-timeline-2:8080
 >      - bill-payment-2:8080
  
  ### **IMPORTANTE: removendo os services acima para efetios praticos não teremos o load balancer**.
## Considerações sobre este modelo de arquitetura:
- Esta sendo utilizado o Nginx como load balancer e proxy reverso, desta formas as apis  portas das aplicações serão todas expostas em uma única porta.
- O Nginx támbem faz o redirect das URLS que serão direcionadas para cadas umas das aplicação, onde a compactação por gzip foi ativada, diminuindo o tamanho das requisições.
- Foram criados dois containers de cada uma das aplicações para a aplicação prática do load balancer do Nginx.
- A constução das aplicações é efetudas através do docker-compose, onde são construídas todas as imagens. Não é necessário nenhum arquivo de dockerfile
- Foi criado apenas um container mongo db onde todas as aplicações tem sua base de dados. Isto foi efetuado para poupar recursos computacionais da demonstração.
- Foi desenvolvida a aplicação wallet-timeline separada para prover as consultas de timeline do usuiário. Esta decisão foi tomada para que as operações de consulta, que são mais custosas, não sejam efetudas nas mesmas aplicações que executam movimentações financeiras.
-  A aplicação wallet-timeline utiliza cache no Redis, para que sejam efetuadas menos consultas ao banco de dados e tenhamos menor processamento de memória e também tanhamos respostas mais rápidas.
- Foi densevolvida a aplicação bill-payment para demonstrar a comunicação entre dois micro-serviços através de REST API.
- O Envio de dados para a timeline é efetuado através de comunicação assíncrona utilizando o RabbitMQ, onde os eventos de timeline são enviados para a exchange e são consumidos pela aplicação wallet-timeline. São enviados para a timeline os eventos de depósito, saque, transferências e pagamentos de faturas.
- No projeto waller-user foi executado um teste de integração com o RabbitMQ, utilizado TestContainer.
- Para fins de demonstração, os testes unitários foram implementados no projeto wallet-user.

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
## Documentação das APIs:

Após executar a demonstração as documentações estarão disponíveis via Swagger nos endereços abaixo:
### Api da aplicação wallet-user
* http://localhost:8081/swagger-ui/index.html

### Api da aplicação wallet-timeline
* http://localhost:8082/swagger-ui/index.html

### Api da aplicação bill-payment
* http://localhost:8083/swagger-ui/index.html
## ARQUITETURA DA SOLUÇÃO:
![Topologia](https://github.com/edercnj/wallet-architecture/blob/master/documents/topologia-wallet.svg)
