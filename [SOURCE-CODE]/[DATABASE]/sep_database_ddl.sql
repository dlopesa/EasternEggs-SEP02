DROP SCHEMA IF EXISTS cafe CASCADE;
CREATE SCHEMA cafe;
SET SCHEMA 'cafe';

CREATE DOMAIN status varchar(50) CHECK (VALUE IN ('unpaid', 'pending', 'completed'));
CREATE DOMAIN type varchar(50) CHECK (VALUE IN ('coffee', 'tea', 'snack', 'smoothie')); --TODO CHECK WHAT TYPES ACTUALLY EXIST

CREATE TABLE order_
(
    order_id serial PRIMARY KEY,
    comment  varchar(300),
    datetime timestamp   NOT NULL,
    price    float  NOT NULL,
    status   status NOT NULL
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
    order_id integer REFERENCES order_ (order_id),
    item_id  integer REFERENCES item (item_id)
);

ALTER TABLE itemInOrder
    ADD CONSTRAINT itemInOrderPPK PRIMARY KEY (order_id, item_id);

CREATE TABLE extra
(
    name varchar(50) PRIMARY KEY
);

CREATE TABLE extraInItemInOrder
(
    extra_id varchar(50) REFERENCES extra (name),
    item_id  integer,
    order_id integer
);

ALTER TABLE extraInItemInOrder
    ADD CONSTRAINT extra_item_order_ppk PRIMARY KEY (extra_id, item_id, order_id),
    ADD CONSTRAINT itemInOrderFK FOREIGN KEY (order_id, item_id) REFERENCES itemInOrder (order_id, item_id);

CREATE TABLE extrasAvailableForItem
(
    item_id  integer REFERENCES item (item_id),
    extra_id varchar(50) REFERENCES extra (name)
);

ALTER TABLE extrasAvailableForItem
    ADD CONSTRAINT extraItem PRIMARY KEY (item_id, extra_id);

CREATE TABLE accessKey
(
    accessKey varchar(50) PRIMARY KEY
);

CREATE TABLE permissionForKey
(
    accessKey  varchar(50) REFERENCES accessKey (accessKey),
    permission varchar(50) NOT NULL
);




--- dummy inserts

INSERT INTO item (name, type, price, description) VALUES ('Coffee', 'coffee',20, ':)' );

INSERT INTO extra VALUES ('caramel syrup');
