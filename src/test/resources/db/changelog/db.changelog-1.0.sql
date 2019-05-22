--liquibase formatted sql

--changeset changelog:1.0-create-user-test dbms:oracle endDelimiter:/

declare
    userexist integer;
begin
    select count(*) into userexist from dba_users where username='TEST';
    if (userexist = 0) then
        execute immediate 'create user TEST identified by "secret"';
    end if;
end;
/

--changeset changelog:1.0-grant-tablespace-to-test dbms:oracle

GRANT UNLIMITED TABLESPACE TO TEST;


