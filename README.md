# YesFinance

## Segunda iteração

Tivemos alguns problemas a interligar o backend com o frontend, portanto, deixamos um pequeno "tutorial" de como fazemos para testar.

## Como correr
```
sudo docker-compose up
docker ps -a

[verificar qual o container do client]

docker stop <container_do_client>
docker rm <container_do_client>
```

noutro terminal
```
cd projFrontend/src/
ng serve
```

## Serviços a funcionar

```
localhost:4200/
localhost:4200/coins/
localhost:4200/ticker/
localhost:4200/markets/
```


## Base de dados

ENTIDADES

Table -> Moeda (https://api.bittrex.com/v3/currencies)
- symbol
- name
- coinType
- status
- minConfirmations
- notice
- txFee
- logoUrl
- prohibitedIn
- baseAddress
- associatedTermsOfService
- tags

Table -> Exchange (https://api.bittrex.com/v3/markets/tickers)
- symbol
- lastTradeRate
- bidRate
- askRate

Table -> User
- username
- fullname
- email
- password
- telephone
- creditCard (será que é seguro ou não?)

## Autores

| NMec | Name | Email | Roles |
|--:|---|---|---|
| 98474| João Reis | joaoreis16@ua.pt | Team Manager |
| 97631| Nuno Fahla| nunofahla@ua.pt| Product Owner |
| 97673| Pedro Duarte | pedro.dld@ua.pt | Architect |
| 98388| Ricardo Rodriguez| ricardombrodriguez@ua.pt| Dev0ps Master |
