set foreign_key_checks = 0;

delete from category;
delete from person;
delete from `release`;
delete from oauth2_registered_client;

set foreign_key_checks = 1;

alter table category auto_increment = 1;
alter table person auto_increment = 1;
alter table `release` auto_increment = 1;

INSERT INTO category (name) VALUES ('Leisure');
INSERT INTO category (name) VALUES ('Food');
INSERT INTO category (name) VALUES ('Supermarket');
INSERT INTO category (name) VALUES ('Pharmacy');
INSERT INTO category (name) VALUES ('Others');

INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO person (name, public_area, number, complement, neighborhood, zip_code, city, state, active) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);

INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'INCOME', 1, 1);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Top Club', '2017-06-10', null, 120, null, 'INCOME', 3, 3);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'INCOME', 3, 4);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENSE', 3, 5);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'INCOME', 4, 6);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Bahamas', '2017-06-10', null, 500, null, 'INCOME', 1, 7);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'EXPENSE', 4, 8);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'EXPENSE', 3, 9);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'INCOME', 5, 10);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Café', '2017-06-10', null, 8.32, null, 'EXPENSE', 1, 5);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'EXPENSE', 5, 4);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'EXPENSE', 4, 3);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'EXPENSE', 4, 2);
INSERT INTO `release` (description, due_date, payment_date, value, observation, type, id_category, id_person) values ('Lanche', '2017-06-10', null, 10.20, null, 'EXPENSE', 4, 1);

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('1', 'angular', '2023-08-04 19:04:12', '$2a$12$eyV2zgKp5tOHTPc./NaPeuu9ykZi0FeapMi7d9tA6JZONm82647ha', NULL, 'angular', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/authorized', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.access-token-time-to-live":["java.time.Duration",900.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

