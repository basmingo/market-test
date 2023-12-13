create table PUBLIC."USER"
(
    U_ID      UUID                  not null,
    NAME      CHARACTER VARYING(64) not null,
    LAST_NAME CHARACTER VARYING(64) not null,
    BALANCE   NUMERIC(20, 3)        not null,
    AGE       INTEGER               not null,
    constraint USER_PK
        primary key (U_ID)
);
