SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`departamento` (
  `id_departamento` INT NOT NULL,
  `desc_departamento` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id_departamento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`municipio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`municipio` (
  `id_municipio` INT NOT NULL,
  `desc_municipio` VARCHAR(128) NOT NULL,
  `id_departamento` INT NOT NULL,
  PRIMARY KEY (`id_municipio`),
  CONSTRAINT `fk_municipio_departamento`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `mydb`.`departamento` (`id_departamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_municipio_departamento_idx` ON `mydb`.`municipio` (`id_departamento` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`tienda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tienda` (
  `id_tienda` INT NOT NULL AUTO_INCREMENT,
  `desc_tienda` VARCHAR(128) NOT NULL,
  `direccion_tienda` VARCHAR(45) NOT NULL,
  `coordenadas_tienda` VARCHAR(45) NULL,
  `id_municipio` INT NOT NULL,
  PRIMARY KEY (`id_tienda`),
  CONSTRAINT `fk_tienda_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `mydb`.`municipio` (`id_municipio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tienda_municipio1_idx` ON `mydb`.`tienda` (`id_municipio` ASC);

CREATE UNIQUE INDEX `direccion_tienda_UNIQUE` ON `mydb`.`tienda` (`direccion_tienda` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`producto` (
  `id_producto` INT NOT NULL AUTO_INCREMENT,
  `barcode` VARCHAR(13) NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `medida` VARCHAR(45) NULL,
  `valor_medida` FLOAT NULL,
  PRIMARY KEY (`id_producto`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `barcode_UNIQUE` ON `mydb`.`producto` (`barcode` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `apellido_usuario` VARCHAR(45) NOT NULL,
  `direccion_usuario` VARCHAR(45) NULL,
  `coordenadas_usuario` VARCHAR(45) NULL,
  `e_mail_usuario` VARCHAR(45) NOT NULL,
  `facebook` VARCHAR(45) NULL,
  `google` VARCHAR(45) NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `e_mail_usuario_UNIQUE` ON `mydb`.`usuario` (`e_mail_usuario` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`tienda_producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tienda_producto` (
  `id_tienda_producto` INT NOT NULL,
  `id_tienda` INT NOT NULL,
  `id_producto` INT NOT NULL,
  PRIMARY KEY (`id_tienda_producto`),
  CONSTRAINT `fk_producto_has_tienda_producto1`
    FOREIGN KEY (`id_producto`)
    REFERENCES `mydb`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_tienda_tienda1`
    FOREIGN KEY (`id_tienda`)
    REFERENCES `mydb`.`tienda` (`id_tienda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_producto_has_tienda_tienda1_idx` ON `mydb`.`tienda_producto` (`id_tienda` ASC);

CREATE INDEX `fk_producto_has_tienda_producto1_idx` ON `mydb`.`tienda_producto` (`id_producto` ASC);

CREATE UNIQUE INDEX `id_producto_UNIQUE` ON `mydb`.`tienda_producto` (`id_producto` ASC);

CREATE UNIQUE INDEX `id_tienda_UNIQUE` ON `mydb`.`tienda_producto` (`id_tienda` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`mercado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mercado` (
  `id_mercado` INT NOT NULL AUTO_INCREMENT,
  `fecha_registro` VARCHAR(45) NOT NULL,
  `cantidad_producto` INT NOT NULL,
  `id_tienda_producto` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_mercado`),
  CONSTRAINT `fk_usuario_has_producto_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mercado_tienda_producto1`
    FOREIGN KEY (`id_tienda_producto`)
    REFERENCES `mydb`.`tienda_producto` (`id_tienda_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_usuario_has_producto_usuario1_idx` ON `mydb`.`mercado` (`id_usuario` ASC);

CREATE UNIQUE INDEX `id_usuario_UNIQUE` ON `mydb`.`mercado` (`id_usuario` ASC);

CREATE INDEX `fk_mercado_tienda_producto1_idx` ON `mydb`.`mercado` (`id_tienda_producto` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`valor_producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`valor_producto` (
  `id_producto` INT NOT NULL,
  `valor_producto` FLOAT NOT NULL,
  `valor_producto_equivalente` FLOAT NULL,
  `fecha_registro_valor` DATE NOT NULL,
  PRIMARY KEY (`id_producto`),
  CONSTRAINT `fk_valor_producto_producto1`
    FOREIGN KEY (`id_producto`)
    REFERENCES `mydb`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_valor_producto_producto1_idx` ON `mydb`.`valor_producto` (`id_producto` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
