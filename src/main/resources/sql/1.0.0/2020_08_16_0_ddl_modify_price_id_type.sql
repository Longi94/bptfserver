DROP TABLE IF EXISTS item_price;

CREATE TABLE item_price
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
    DEFINDEX    INTEGER     NOT NULL,
    QUALITY     INTEGER     NOT NULL,
    TRADABLE    TINYINT(1)  NOT NULL,
    CRAFTABLE   TINYINT(1)  NOT NULL,
    PRICE_INDEX INTEGER     NOT NULL,
    AUSTRALIUM  TINYINT(1)  NOT NULL,
    PRICE       DOUBLE      NOT NULL,
    PRICE_MAX   DOUBLE,
    PRICE_RAW   DOUBLE      NOT NULL,
    CURRENCY    VARCHAR(10) NOT NULL,
    UPDATE_TS   BIGINT      NOT NULL,
    DIFFERENCE  DOUBLE      NOT NULL,
    WEAPON_WEAR INTEGER     NOT NULL,
    UNIQUE UQ_UNIQUE_ITEM (DEFINDEX, QUALITY, TRADABLE, CRAFTABLE, PRICE_INDEX, AUSTRALIUM, WEAPON_WEAR)
);
