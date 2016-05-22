  -- schema-psql.sql
  -- DDL commands for PostgreSQL
  DROP TABLE IF EXISTS accounts;
  DROP TABLE IF EXISTS payment;

 CREATE TABLE accounts (
    id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    birthName VARCHAR(20),
    givenName VARCHAR(30),
    accountNumber VARCHAR(20),
    sumAmount NUMERIC(8,3),
    wasDeleted BOOLEAN);

 CREATE TABLE payment (
    ID BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    amount NUMERIC(8,3),
    date DATE,
    message VARCHAR(120),
    sender BIGINT,
    reciever BIGINT,
    sended BOOLEAN);