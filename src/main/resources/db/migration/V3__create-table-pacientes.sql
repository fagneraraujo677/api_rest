create table pacientes(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(20) not null,
    celular varchar(20) not null,
    corporativo varchar(20) not null,
    registro varchar(6) not null unique,
    rg varchar(14) not null,
    cpf varchar(14) not null unique,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    estado char(2) not null,
    cep varchar(9) not null,
    pais varchar(50) not null,
    numero varchar(20) not null,
    complemento varchar(100),

    primary key(id)
);
