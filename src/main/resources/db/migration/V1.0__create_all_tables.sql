CREATE SEQUENCE provider_id_seq START WITH 1;
CREATE SEQUENCE rebate_id_seq START WITH 1;

CREATE TABLE provider(
/*id             INTEGER NOT NULL default nextval(‘account_id_seq’),*/
    id                BIGSERIAL NOT NULL,
    name              VARCHAR(30) not null unique
);
ALTER TABLE provider ADD CONSTRAINT provider_pk PRIMARY KEY ( id );

CREATE TABLE rebate(
/*id             INTEGER NOT NULL default nextval(‘account_id_seq’),*/
    id                BIGSERIAL NOT NULL,
    name              VARCHAR(30) not null unique,
    link              VARCHAR(150),
    rebate_type       VARCHAR(30),
    value             NUMERIC(10, 2),
    start_time        TIMESTAMP default CURRENT_TIMESTAMP,
    end_time          TIMESTAMP,
    provider_id       BIGINT NOT NULL
);
ALTER TABLE rebate ADD CONSTRAINT rebate_pk PRIMARY KEY ( id );

CREATE TABLE transaction(
/*id             INTEGER NOT NULL default nextval(‘account_id_seq’),*/
    id                         BIGSERIAL NOT NULL,
    purchase_time              TIMESTAMP default CURRENT_TIMESTAMP,
    order_id                   VARCHAR(30),
    amount                     NUMERIC(10, 2),
    rebate_id                  BIGINT NOT NULL
);
ALTER TABLE transaction ADD CONSTRAINT transaction_pk PRIMARY KEY ( id );


ALTER TABLE rebate ADD CONSTRAINT rebate_provider_fk FOREIGN KEY (provider_id) REFERENCES provider (id);

ALTER TABLE transaction ADD CONSTRAINT transaction_rebate_fk FOREIGN KEY (rebate_id) REFERENCES rebate (id);