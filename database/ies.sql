USE IES;

CREATE SCHEMA IF NOT EXISTS PROJETO;

CREATE TABLE IF NOT EXISTS  PROJETO.User (

	id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS  PROJETO.Currency (

	id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
	symbol VARCHAR(100) NOT NULL,
	logoUrl VARCHAR(200) NOT NULL,
	online BIT DEFAULT 1,
	PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS  PROJETO.Market (

  id INT NOT NULL AUTO_INCREMENT,
	origin_currency INT NOT NULL,
	destiny_currency INT NOT NULL,
	last_value FLOAT NOT NULL,
	max_bid FLOAT NOT NULL,
	max_sell FLOAT NOT NULL,
	FOREIGN KEY (origin_currency) REFERENCES PROJETO.Currency(id),
	FOREIGN KEY (destiny_currency) REFERENCES PROJETO.Currency(id),
	PRIMARY KEY (id)

);

-- Guardar apenas dos utilizadores da nossa app mas não os dados que vem da API
-- Licitação
CREATE TABLE IF NOT EXISTS  PROJETO.Order (

	id INT NOT NULL AUTO_INCREMENT,
	portfolio INT NOT NULL,
	market INT NOT NULL,
	quantity FLOAT NOT NULL,
	value FLOAT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (market) REFERENCES PROJETO.Market(id),
	FOREIGN KEY (portfolio) REFERENCES PROJETO.Portfolio(id)

);

-- relação entre um user e um portfolio
CREATE TABLE IF NOT EXISTS  PROJETO.UserPortfolio (

    user INT NOT NULL,
    portfolio INT NOT NULL,
    FOREIGN KEY (user) REFERENCES PROJETO.User(id),
    FOREIGN KEY (portfolio) REFERENCES PROJETO.Portfolio(id),
    PRIMARY KEY (user,portfolio)

);

-- pode ser portfolio partilhado (ou não mas isso é definido no userportfolio)
CREATE TABLE IF NOT EXISTS  PROJETO.Portfolio (

    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    public_key VARCHAR(256) NOT NULL,
    PRIMARY KEY (id)

);

-- Troca de ordens (entre ordem de venda e de compra)
CREATE TABLE IF NOT EXISTS  PROJETO.Transaction (

    origin_order INT NOT NULL,
    destiny_order INT NOT NULL,
    FOREIGN KEY (origin_order) REFERENCES PROJETO.Order(id),
    FOREIGN KEY (destiny_order) REFERENCES PROJETO.Order(id),
    PRIMARY KEY (origin_order,destiny_order)

);

-- Todas vão ter um script que dizem como é que corremos a extensão
-- o path é que tem toda a informação

CREATE TABLE IF NOT EXISTS  PROJETO.Extensions (

    id INT NOT NULL AUTO_INCREMENT,
    user INT NOT NULL,
    path VARCHAR(500) NOT NULL,
    FOREIGN KEY (user) REFERENCES PROJETO.User(id),
    PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS  PROJETO.PortfolioExtensions(

    extension INT NOT NULL,
    portfolio INT NOT NULL,
    FOREIGN KEY (extension) REFERENCES PROJETO.Extensions(id),
    FOREIGN KEY (portfolio) REFERENCES PROJETO.Portfolio(id),
    PRIMARY KEY (extension,portfolio)

);
