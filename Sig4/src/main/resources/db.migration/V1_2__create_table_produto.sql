create table Produto (
       id bigint not null,
        cnpj varchar(255),
        cor varchar(255),
        dataCadastro varchar(255),
        nome varchar(255),
        preco double precision not null,
        qtdEstoque integer not null,
        tipo varchar(255),
        primary key (id)
    );
