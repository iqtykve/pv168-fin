-- my-test-data.sql

INSERT INTO accounts (birthName, givenName, accountNumber,sumAmount,wasDeleted) VALUES ('Jarduv','Jarda','800/100',100.00,false);

INSERT INTO accounts (birthName, givenName, accountNumber,sumAmount,wasDeleted) VALUES ('Kraluv','Kral','823/103',100.00,false);

INSERT INTO payment (amount , message, sender,reciever,sended, date) VALUES (50.00,'Platba Karlovi od Jardy',1,2,false, '2014-01-28');

