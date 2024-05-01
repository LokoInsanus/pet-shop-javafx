DROP TABLE pet;
DROP TABLE cliente;

CREATE TABLE cliente (
    cdCliente INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    rua VARCHAR(50) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    numCasa VARCHAR(10) NOT NULL,
    telefone CHAR(12) NOT NULL,
    cpf  CHAR(14) NOT NULL
);

CREATE TABLE pet (
    cdPet INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    animal VARCHAR(50) NOT NULL,
    dono INT NOT NULL,
    raca VARCHAR(50) NOT NULL,
    rga VARCHAR(9) NOT NULL,
    FOREIGN KEY(dono) REFERENCES cliente(cdCliente)
);