CREATE TABLE item_schema (
  DEFINDEX         INTEGER PRIMARY KEY,
  ITEM_NAME        VARCHAR(255) NOT NULL,
  ITEM_DESCRIPTION LONGTEXT,
  TYPE_NAME        VARCHAR(100) NOT NULL,
  PROPER_NAME      BIT          NOT NULL,
  IMAGE_URL        VARCHAR(255),
  IMAGE_URL_LARGE  VARCHAR(255)
);
