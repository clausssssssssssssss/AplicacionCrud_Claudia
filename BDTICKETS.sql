CREATE TABLE TBusuariosh (
    UUID_Usuario VARCHAR2(50) ,
    Email VARCHAR2(100) ,
    Contrasena VARCHAR2(100)
);
drop table TBticketsh

CREATE TABLE tbticketsh (
    UUUID VARCHAR2(100),
    ID_Ticket INT ,
    titulo VARCHAR2(100) ,
    descripcion VARCHAR2(500),
    Autor VARCHAR2(100),
    Email VARCHAR2(100),
    Creacion DATE,
    Estado VARCHAR2(10),
    Finalizacion DATE
);

