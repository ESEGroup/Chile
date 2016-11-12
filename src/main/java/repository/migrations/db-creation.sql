-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema manutencao-ufrj
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema manutencao-ufrj
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `manutencao-ufrj` ;
USE `manutencao-ufrj` ;

-- -----------------------------------------------------
-- Table `manutencao-ufrj`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `manutencao-ufrj`.`Role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `manutencao-ufrj`.`Department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `manutencao-ufrj`.`Department` (
  `department_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`department_id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `manutencao-ufrj`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `manutencao-ufrj`.`User` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` CHAR(7) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `rg` CHAR(9) NOT NULL,
  `rg_issuer` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `password` CHAR(60) NOT NULL,
  `birth_date` DATETIME NULL,
  `creation_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT 0,
  `role_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`, `department_id`),
  INDEX `fk_User_Role_idx` (`role_id` ASC),
  INDEX `fk_User_Department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_User_Role`
  FOREIGN KEY (`role_id`)
  REFERENCES `manutencao-ufrj`.`Role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Department1`
  FOREIGN KEY (`department_id`)
  REFERENCES `manutencao-ufrj`.`Department` (`department_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



INSERT INTO `manutencao-ufrj`.Role (name)
  VALUES ('Administrador Geral'), ('Administrador de Departamento'), ('Funcionário');

INSERT INTO `manutencao-ufrj`.Department(name)
    VALUES ('Geral');

INSERT INTO `manutencao-ufrj`.User (employee_id, cpf, rg, rg_issuer, name, password, role_id, department_id)
    VALUES ('1234567', '16035256708', '277694709', 'DETRANRJ', 'Eric Reis Figueiredo', '$2a$04$Vz8LR1pmPF22weI2rgEcX.rc9GSeDkxcyxI3Uwymm8/u4mYU02xDK', 1, 1);