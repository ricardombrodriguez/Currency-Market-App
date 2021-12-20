# YesFinance

## How to run
```
docker-compose build
docker-compose up
```

## How to shutdown
```
docker-compose stop
docker-compose down
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
