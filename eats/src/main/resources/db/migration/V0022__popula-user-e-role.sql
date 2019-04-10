insert into role(authority) values ('ADMIN');
insert into role(authority) values ('PARCEIRO');

-- senha: 123456
insert into user (id, name, password) values (1, 'admin', '$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');
insert into user (id, name, password) values (2, 'longfu', '$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');

insert into user_authorities (user_id, authorities_authority) values (1, 'ADMIN');
insert into user_authorities (user_id, authorities_authority) values (2, 'PARCEIRO');
