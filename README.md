# Transfer API

REST API para transferências entre contas bancárias, desenvolvida com Java 21 e Spring Boot.

## Tecnologias

- Java 21
- Spring Boot 3
- PostgreSQL
- Flyway
- JUnit 5 + Mockito

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/accounts` | Criar uma conta |
| `GET` | `/accounts/{id}` | Consultar dados e saldo |
| `POST` | `/transfers` | Realizar uma transferência |
| `GET` | `/transfers/{id}` | Consultar uma transferência |

## Regras de negócio

- Saldo não pode ficar negativo
- Valor zero ou negativo não é permitido
- Transferência para a mesma conta não é permitida
- Transferência é atômica — debita e credita juntos ou não faz nada

## Testes

Cobertura de testes unitários com JUnit 5 e Mockito para as regras de negócio:

- Transferência realizada com sucesso — valida débito na origem e crédito no destino
- Saldo insuficiente — lança `InsufficientBalanceException`
- Transferência para a mesma conta — lança `SameAccountTransferException`
- Conta de origem não encontrada — lança `AccountNotFoundException`
- Conta de destino não encontrada — lança `AccountNotFoundException`
- Transferência não encontrada — lança `TransferNotFoundException`
- Criação de conta — valida que o repositório persiste a entidade
- Consulta de conta por id — valida dados retornados
- Conta não encontrada — lança `AccountNotFoundException`

## Decisões técnicas

**BigDecimal para valores monetários** — tipos de ponto flutuante como `double` causam erros de precisão. `BigDecimal` garante exatidão em operações financeiras.

**@Transactional** — garante atomicidade na transferência. Se qualquer exceção for lançada durante o processo, o Spring realiza rollback automático, evitando inconsistências no banco.

**Exceções customizadas** — em vez de `RuntimeException` genérica, cada cenário de erro tem sua própria exceção (`AccountNotFoundException`, `InsufficientBalanceException`, `SameAccountTransferException`), tornando o código mais expressivo e o tratamento de erros mais preciso.

**Flyway para migrations** — versionamento do schema do banco garante que qualquer ambiente reproduza a mesma estrutura de dados, sem necessidade de configuração manual.

**ddl-auto: validate** — o Hibernate valida o schema em vez de alterá-lo automaticamente, evitando mudanças acidentais no banco em produção.
