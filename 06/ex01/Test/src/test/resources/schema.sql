create table if not exists product (
    id          INT PRIMARY KEY IDENTITY,
    name        VARCHAR(50) NOT NULL ,
    price       INT NOT NULL
    );