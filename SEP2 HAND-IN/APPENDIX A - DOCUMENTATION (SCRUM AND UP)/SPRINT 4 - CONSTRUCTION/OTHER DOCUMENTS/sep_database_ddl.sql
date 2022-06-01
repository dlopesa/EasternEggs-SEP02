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
    order_id integer REFERENCES order_ (order_id) ON DELETE CASCADE,
    item_id  integer REFERENCES item (item_id)
);

CREATE TABLE extra
(
    name varchar(50) PRIMARY KEY
);

CREATE TABLE extraInItemInOrder
(
    extra_id varchar(50) REFERENCES extra (name),
    item_in_order_id integer REFERENCES itemInOrder(item_in_order_id),
    item_id  integer REFERENCES item(item_id),
    order_id integer REFERENCES order_(order_id)
);

ALTER TABLE extraInItemInOrder
    ADD CONSTRAINT extra_item_order_ppk PRIMARY KEY (extra_id, item_in_order_id);

CREATE TABLE extraAvailableForType
(
    extra_id varchar(50) REFERENCES extra (name),
    type     type
);

ALTER TABLE extraAvailableForType
    ADD CONSTRAINT extraType PRIMARY KEY (extra_id, type);

CREATE TABLE accessKey
(
    accessKey varchar(50) PRIMARY KEY
);

CREATE TABLE permissionForKey
(
    accessKey  varchar(50) REFERENCES accessKey (accessKey),
    permission varchar(50) NOT NULL
);

INSERT INTO item (name, type, price, description)
VALUES ('Coffee', 'coffee', 20, ':)'),
       ('Tea', 'tea', 7, 'lovely herbal tea');
INSERT INTO extra (name)
VALUES ('caramel syrup'),
       ('honey');
