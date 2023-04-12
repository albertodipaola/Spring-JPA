CREATE TABLE prodotti (
    id bigint auto_increment primary key,
    nome varchar(100) not null,
    descrizione varchar(1000),
    prezzo float check (prezzo > 0),
    netto float check (netto > 0)
);

CREATE TABLE varianti (
    id bigint auto_increment primary key,
    attributo varchar(100),
    valore varchar(100),
    prodotto_id bigint references prodotti(id)
);

CREATE TABLE fornitori (
    id bigint auto_increment primary key,
    nome varchar(100) not null
);

CREATE TABLE fornitori_prodotti (
    fornitore_id bigint references fornitori(id),
    prodotto_id bigint references prodotti(id)
);
