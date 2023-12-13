create table PUBLIC.PRODUCT
(
    P_ID         UUID                  not null,
    DISPLAY_NAME CHARACTER VARYING(32) not null,
    CREATED      TIMESTAMP             not null,
    UPDATED      TIMESTAMP             not null,
    STATUS       CHARACTER VARYING(16) not null,
    constraint PRODUCT_PK
        primary key (P_ID)
);
