/*
 *
 */
-- CREATE SEQUENCE TEST.DEPARTMENT_SEQ
--     INCREMENT BY 1
--     START WITH 1;


/*
 * CREATE TABLE DEPARTMENT
 */
CREATE TABLE TEST.DEPARTMENT (
    ID NUMBER,
    NAME VARCHAR2(30),
    ACTIVE VARCHAR2(1) DEFAULT 'N',
    CONSTRAINT DEPARTMENT_PK PRIMARY KEY (ID)
)