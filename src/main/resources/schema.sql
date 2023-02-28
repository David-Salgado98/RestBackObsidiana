DROP TABLE IF EXISTS materiales;

CREATE TABLE materiales (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS tipos;

CREATE TABLE tipos (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS productos;

CREATE TABLE productos (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  dire VARCHAR(250) NOT NULL,
  cost DOUBLE,
  description VARCHAR(1000) NOT NULL,
  mat_id BIGINT,
  typ_id BIGINT

);