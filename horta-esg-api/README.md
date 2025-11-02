# Horta ESG API 🌱

API RESTful com **Spring Boot**, **Oracle XE** e **Docker**, simulando uma horta comunitária com foco em ESG.

## Como rodar
1) Build:
   mvn clean package

2) Subir os serviços:
   docker-compose up --build

- App: http://localhost:8080
- Oracle XE: porta 1521
- Health: http://localhost:8080/actuator/health

## Credenciais (Basic Auth)
- Login: user
- Senha: user123

## Banco (Docker)
- Service name: oracle-db
- JDBC URL: jdbc:oracle:thin:@oracle-db:1521/FREEPDB1
- Usuário app: ESG_USER / ESG_PASSWORD (injetado pelo compose como esg_user / esg123)
- Migrações: Flyway (V1__schema.sql e V2__seed.sql)

## Endpoints

### Público (sem auth)
- GET /api/horta/public/ping
  - Ex.: curl http://localhost:8080/api/horta/public/ping

### Protegidos (Basic Auth user/user123)
- GET /api/especies
- POST /api/especies
- GET /api/especies/{id}
- DELETE /api/especies/{id}

- GET /api/horta/canteiros
- POST /api/horta/canteiros
- PUT /api/horta/canteiros/{id}
- DELETE /api/horta/canteiros/{id}

- GET /api/horta/relatorios/doacoes-mensais?ano=2025&mes=9
- GET /api/horta/indicadores?ano=2025&mes=9

### Exemplos de teste

Listar espécies:
curl -u user:user123 http://localhost:8080/api/especies

Criar espécie:
curl -u user:user123 -H "Content-Type: application/json" \
  -d '{"nome":"Alface"}' \
  http://localhost:8080/api/especies

Listar canteiros:
curl -u user:user123 http://localhost:8080/api/horta/canteiros

Criar canteiro (IDs gerados pelo Oracle — não envie "id"):
curl -u user:user123 -H "Content-Type: application/json" \
  -d '{"nome":"Canteiro C3","especieId":1,"areaM2":12.5,"metaDoacaoKg":40}' \
  http://localhost:8080/api/horta/canteiros

Relatório:
curl -u user:user123 "http://localhost:8080/api/horta/relatorios/doacoes-mensais?ano=2025&mes=9"

Indicadores:
curl -u user:user123 "http://localhost:8080/api/horta/indicadores?ano=2025&mes=9"

