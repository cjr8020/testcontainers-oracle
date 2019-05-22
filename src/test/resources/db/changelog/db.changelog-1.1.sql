--liquibase formatted sql

--changeset changelog:1.1-create-department-seq dbms:oracle

-- starts with 1001 to allow test data insert below
CREATE SEQUENCE TEST.DEPARTMENT_SEQ
    INCREMENT BY 1
    START WITH 1001
;

--changeset changelog:1.1-create-table-department dbms:oracle

CREATE TABLE TEST.DEPARTMENT (
    ID NUMBER(12),
    NAME VARCHAR2(30),
    ACTIVE VARCHAR2(1) DEFAULT 'N',
    CONSTRAINT DEPARTMENT_PK PRIMARY KEY (ID)
);

--chageset changelog:1.1-create-department-HR dbms:oracle

INSERT INTO TEST.DEPARTMENT (
    ID,
    NAME,
    ACTIVE
) VALUES (
    1,
    'HR',
    'Y'
);


--chageset changelog:1.1-create-department-Marketing dbms:oracle

INSERT INTO TEST.DEPARTMENT (
    ID,
    NAME,
    ACTIVE
) VALUES (
    2,
    'Marketing',
    'Y'
);
