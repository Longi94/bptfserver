CREATE TABLE missing_icon (
  ID          INTEGER PRIMARY KEY AUTO_INCREMENT,
  DEFINDEX    INTEGER    NOT NULL,
  AUSTRALIUM  TINYINT(1) NOT NULL,
  WEAPON_WEAR INTEGER    NOT NULL,
  FAILED      TINYINT(1) NOT NULL,
  UNIQUE UQ_UNIQUE_ICON (DEFINDEX, AUSTRALIUM, WEAPON_WEAR)
);
