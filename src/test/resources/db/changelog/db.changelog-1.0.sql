--liquibase formatted sql

--changeset changelog:1.0



-- CREATE SCHEMA TEST

CREATE USER TEST IDENTIFIED BY "secret";

GRANT UNLIMITED TABLESPACE TO TEST;


