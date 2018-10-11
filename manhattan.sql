-- -----------------------------------------------------
-- Table 'departamento'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'departamento' (
  'id_departamento' INT NOT NULL,
  'desc_departamento' VARCHAR(128) NOT NULL,
  PRIMARY KEY ('id_departamento'))


-- -----------------------------------------------------
-- Table 'municipio'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'municipio' (
  'id_municipio' INT NOT NULL,
  'desc_municipio' VARCHAR(128) NOT NULL,
  'id_departamento' INT NOT NULL,
  PRIMARY KEY ('id_municipio'),
  CONSTRAINT 'fk_municipio_departamento'
    FOREIGN KEY ('id_departamento')
    REFERENCES 'departamento' ('id_departamento')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

CREATE INDEX 'fk_municipio_departamento_idx' ON 'municipio' ('id_departamento' ASC)


-- -----------------------------------------------------
-- Table 'tienda'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'tienda' (
  'id_tienda' INT NOT NULL AUTO_INCREMENT,
  'desc_tienda' VARCHAR(128) NOT NULL,
  'direccion_tienda' VARCHAR(45) NULL,
  'coordenadas_tienda' VARCHAR(45) NULL,
  'id_municipio' INT NOT NULL,
  PRIMARY KEY ('id_tienda'),
  CONSTRAINT 'fk_tienda_municipio1'
    FOREIGN KEY ('id_municipio')
    REFERENCES 'municipio' ('id_municipio')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

CREATE INDEX 'fk_tienda_municipio1_idx' ON 'tienda' ('id_municipio' ASC)


-- -----------------------------------------------------
-- Table 'producto'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'producto' (
  'id_producto' INT NOT NULL AUTO_INCREMENT,
  'barcode' BIGINT NOT NULL,
  'Marca' VARCHAR(45) NOT NULL,
  'descripcion' VARCHAR(45) NOT NULL,
  'id_tienda' INT NOT NULL,
  PRIMARY KEY ('id_producto'),
  CONSTRAINT 'fk_producto_tienda1'
    FOREIGN KEY ('id_tienda')
    REFERENCES 'tienda' ('id_tienda')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

CREATE UNIQUE INDEX 'barcode_UNIQUE' ON 'producto' ('barcode' ASC)

CREATE INDEX 'fk_producto_tienda1_idx' ON 'producto' ('id_tienda' ASC)


-- -----------------------------------------------------
-- Table 'usuario'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'usuario' (
  'id_usuario' INT NOT NULL AUTO_INCREMENT,
  'nombre_usuario' VARCHAR(45) NOT NULL,
  'apellido_usuario' VARCHAR(45) NOT NULL,
  'direccion_usuario' VARCHAR(45) NULL,
  'coordenadas_usuario' VARCHAR(45) NULL,
  'e_mail_usuario' VARCHAR(45) NOT NULL,
  'facebook' VARCHAR(45) NULL,
  'google' VARCHAR(45) NULL,
  PRIMARY KEY ('id_usuario'))


-- -----------------------------------------------------
-- Table 'mercado'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'mercado' (
  'idMercado' INT NOT NULL AUTO_INCREMENT,
  'id_usuario' INT NOT NULL,
  'id_producto' INT NOT NULL,
  'fecha_registro' VARCHAR(45) NOT NULL,
  'cantidad_producto' INT NOT NULL,
  PRIMARY KEY ('idMercado'),
  CONSTRAINT 'fk_usuario_has_producto_usuario1'
    FOREIGN KEY ('id_usuario')
    REFERENCES 'usuario' ('id_usuario')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'fk_usuario_has_producto_producto1'
    FOREIGN KEY ('id_producto')
    REFERENCES 'producto' ('id_producto')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

CREATE INDEX 'fk_usuario_has_producto_producto1_idx' ON 'mercado' ('id_producto' ASC)

CREATE INDEX 'fk_usuario_has_producto_usuario1_idx' ON 'mercado' ('id_usuario' ASC)

CREATE UNIQUE INDEX 'id_usuario_UNIQUE' ON 'mercado' ('id_usuario' ASC)

CREATE UNIQUE INDEX 'id_producto_UNIQUE' ON 'mercado' ('id_producto' ASC)


-- -----------------------------------------------------
-- Table 'valor_producto'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'valor_producto' (
  'id_producto' INT NOT NULL,
  'valor_producto' VARCHAR(45) NOT NULL,
  'fecha_registro_valor' DATE NOT NULL,
  'valor_producto_equivalente' VARCHAR(45) NULL,
  'valor_medida' VARCHAR(45) NULL,
  'medida' VARCHAR(45) NULL,
  PRIMARY KEY ('id_producto', 'valor_producto', 'fecha_registro_valor'),
  CONSTRAINT 'fk_valor_producto_producto1'
    FOREIGN KEY ('id_producto')
    REFERENCES 'producto' ('id_producto')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

CREATE INDEX 'fk_valor_producto_producto1_idx' ON 'valor_producto' ('id_producto' ASC)
