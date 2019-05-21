--liquibase formatted sql

--changeset changelog:1.2

-- INSERT test records
INSERT INTO TEST.DEPARTMENT (
    ID,
    NAME,
    ACTIVE
) VALUES (
    1,
    'HR',
    'Y'
);

-- INSERT test records
INSERT INTO TEST.DEPARTMENT (
    ID,
    NAME,
    ACTIVE
) VALUES (
    2,
    'Marketing',
    'Y'
);