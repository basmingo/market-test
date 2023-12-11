create table PUBLIC.PRODUCT
(
    P_ID     UUID      not null,
    CREATED  TIMESTAMP not null,
    ORDER_ID UUID      not null,
    constraint PRODUCT_PK
        primary key (P_ID)
);

-- alter table PUBLIC.PRODUCT owner to admin;

create table PUBLIC."ORDER"
(
    ORDER_ID UUID                  not null,
    USER_ID  UUID                  not null,
    STATUS   CHARACTER VARYING(16) not null,
    constraint ORDER_PK
        primary key (ORDER_ID)
);

