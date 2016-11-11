-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema diretodafont
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema diretodafont
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `manutencao-ufrj` ;
USE `manutencao-ufrj` ;

-- -----------------------------------------------------
-- Table `diretodafont`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `manutencao-ufrj`.`User` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `rg` CHAR(9) NOT NULL,
  `rgIssuer` VARCHAR(45) NOT NULL,
  `employee_id` CHAR(7) NOT NULL,
  `password` CHAR(60) NOT NULL,
  `birth_date` DATETIME NOT NULL,
  `is_admin` TINYINT(1) NOT NULL,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`))
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
