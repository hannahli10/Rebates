-- CREATE SEQUENCE provider_id_seq START WITH 1;
-- CREATE SEQUENCE rebate_id_seq START WITH 1;

CREATE TABLE providers(
/*id             INTEGER NOT NULL default nextval(‘account_id_seq’),*/
    id                BIGSERIAL,
    name              VARCHAR(30) not null unique
);


CREATE TABLE rebates(
/*id             INTEGER NOT NULL default nextval(‘account_id_seq’),*/
    id                BIGSERIAL,
    name              VARCHAR(30) not null unique,
    link              VARCHAR(150),
    rebate_type       VARCHAR(30),
    value             NUMERIC(10, 2),
    provider_id       BIGINT NOT NULL
);


CREATE TABLE orders(
/*id             INTEGER NOT NULL default nextval(‘account_id_seq’),*/
    id                         BIGSERIAL,
    purchase_time              TIMESTAMP,
    order_number               VARCHAR(30),
    amount                     NUMERIC(10, 2),
    rebate_id                  BIGINT NOT NULL
);


ALTER TABLE providers ADD CONSTRAINT provider_pk PRIMARY KEY ( id );
ALTER TABLE rebates ADD CONSTRAINT rebate_pk PRIMARY KEY ( id );
ALTER TABLE orders ADD CONSTRAINT order_pk PRIMARY KEY ( id );


ALTER TABLE rebates ADD CONSTRAINT rebate_provider_fk FOREIGN KEY (provider_id) REFERENCES providers (id);
ALTER TABLE orders ADD CONSTRAINT order_rebate_fk FOREIGN KEY (rebate_id) REFERENCES rebates (id);