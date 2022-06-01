DROP SCHEMA IF EXISTS cafe CASCADE;
CREATE SCHEMA cafe;
SET SCHEMA 'cafe';

CREATE DOMAIN status varchar(50) CHECK (VALUE IN ('unpaid', 'pending', 'completed'));
CREATE DOMAIN type varchar(50) CHECK (VALUE IN ('coffee', 'tea', 'snack', 'smoothie')); --TODO CHECK WHAT TYPES ACTUALLY EXIST

CREATE TABLE order_
(
    order_id serial PRIMARY KEY,
    comment  varchar(300),
    datetime timestamp NOT NULL,
    price    float     NOT NULL,
    status   status    NOT NULL
);

CREATE TABLE item
(
    item_id     serial PRIMARY KEY,
    name        varchar(50) NOT NULL,
    type        type        NOT NULL,
    price       float       NOT NULL,
    description varchar(300)
);

CREATE TABLE itemInOrder
(
    item_in_order_id serial PRIMARY KEY,
    order_id         integer REFERENCES order_ (order_id) ON DELETE CASCADE,
    item_id          integer REFERENCES item (item_id)  ON DELETE CASCADE
);

CREATE TABLE extra
(
    name varchar(50) PRIMARY KEY
);

CREATE TABLE extraInItemInOrder
(
    extra_id         varchar(50) REFERENCES extra (name)  ON DELETE CASCADE,
    item_in_order_id integer REFERENCES itemInOrder (item_in_order_id),
    item_id          integer REFERENCES item (item_id)  ON DELETE CASCADE,
    order_id         integer REFERENCES order_ (order_id) ON DELETE CASCADE
);

ALTER TABLE extraInItemInOrder
    ADD CONSTRAINT extra_item_order_ppk PRIMARY KEY (extra_id, item_in_order_id);

CREATE TABLE extraAvailableForType
(
    extra_id varchar(50) REFERENCES extra (name) ON DELETE CASCADE,
    type     type
);

ALTER TABLE extraAvailableForType
    ADD CONSTRAINT extraType PRIMARY KEY (extra_id, type);

CREATE TABLE accessKey
(
    accessKey  varchar(50) PRIMARY KEY,
    permission VARCHAR(50) NOT NULL
);

INSERT INTO item (name, type, price, description)
VALUES ('Coffee', 'coffee', 20, ':)'),
       ('Tea', 'tea', 7, 'lovely herbal tea'),
       ('KitKat', 'snack', 20, 'crunchy and sweet'),
       ('Strawberry & Banana', 'smoothie', 50, 'creamy');

INSERT INTO extra(name)
VALUES ('milk'),('sugar'),('hazelnut syrup'),('cheese sauce');
INSERT INTO extraavailablefortype (extra_id, type)
VALUES ('milk', 'coffee'),('milk', 'tea'),('sugar','coffee'),('sugar','tea'),('hazelnut syrup','coffee'),('cheese sauce','snack');

INSERT INTO accessKey(accesskey, permission) VALUES ('admin', 'Admin'), ('barista', 'Barista'), ('display', 'Display'), ('cashier', 'Cashier');
