create table customer (
       id bigint not null auto_increment,
        address varchar(255),
        date_of_birth date,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        phone varchar(255),
        primary key (id)
    ) engine=MyISAM