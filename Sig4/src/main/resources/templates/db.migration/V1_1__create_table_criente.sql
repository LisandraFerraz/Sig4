create table Cliente (
       id bigint not null,
        cep varchar(255),
        complemento varchar(255),
        cpf varchar(255),
        dataCadastro varchar(255),
        dataNascimento varchar(255),
        endereco varchar(255),
        nome varchar(255),
        sexo varchar(255),
        primary key (id)
);
