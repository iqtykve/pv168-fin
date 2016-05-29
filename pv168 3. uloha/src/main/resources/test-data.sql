-- my-test-data.sql

INSERT INTO accounts (birthName, givenName, accountNumber,sumAmount,wasDeleted) VALUES ('Valenta','Jirka','820/100',100.00,false);

INSERT INTO accounts (birthName, givenName, accountNumber,sumAmount,wasDeleted) VALUES ('Valenta','Ondra','821/103',100.00,false);

INSERT INTO accounts (birthName, givenName, accountNumber,sumAmount,wasDeleted) VALUES ('Valenta','Honza','822/103',100.00,false);

INSERT INTO accounts (birthName, givenName, accountNumber,sumAmount,wasDeleted) VALUES ('Viluda','Lubo','823/103',100.00,false);

INSERT INTO payment (amount , message, sender,reciever,sended, date) VALUES (20.00,'Platba Karlovi od Jardy',1,2,true, '2014-01-28');

INSERT INTO payment (amount , message, sender,reciever,sended, date) VALUES (60.00,'Platba Karlovi od Jardy',2,3,false, '2014-01-28');

INSERT INTO payment (amount , message, sender,reciever,sended, date) VALUES (80.00,'Platba Karlovi od Jardy',3,2,true, '2014-01-28');

INSERT INTO payment (amount , message, sender,reciever,sended, date) VALUES (30.00,'Platba Karlovi od Jardy',4,2,false, '2014-01-28');

INSERT INTO payment (amount , message, sender,reciever,sended, date) VALUES (80.00,'Platba Karlovi od Jardy',1,2,true, '2014-01-28');

INSERT INTO payment (amount , message, sender,reciever,sended, date) VALUES (120.00,'Platba Karlovi od Jardy',2,1,false, '2014-01-28');

INSERT INTO payment (amount , message, sender,reciever,sended, date) VALUES (30.00,'Platba Karlovi od Jardy',3,2,true, '2014-01-28');