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


-- -----------------------------------------------------
-- Table `manutencao-ufrj`.`Equipament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `manutencao-ufrj`.`Equipament` (
  `equipament_id` INT NOT NULL AUTO_INCREMENT,
  `equipament_registry` VARCHAR(45) NOT NULL,
  `last_maintenance` DATETIME NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `maintenance_periodicity` INT NOT NULL,
  `status` TINYINT(1) NOT NULL,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT 0,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`equipament_id`, `department_id`),
  CONSTRAINT `fk_Equipament_Department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `manutencao-ufrj`.`Department` (`department_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `manutencao-ufrj`.`Maintenance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `manutencao-ufrj`.`Maintenance` (
  `id_maintenance` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `finished_date` DATETIME NULL,
  `description` VARCHAR(8000) NOT NULL,
  `finished` TINYINT(1) NOT NULL DEFAULT 0,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT 0,
  `equipament_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id_maintenance`, `equipament_id`, `user_id`),
  INDEX `fk_Maintenance_User1_idx` (`user_id` ASC),
  INDEX `fk_Maintenance_Equipament1_idx` (`equipament_id` ASC),
  CONSTRAINT `fk_Maintenance_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `manutencao-ufrj`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Maintenance_Equipament1`
    FOREIGN KEY (`equipament_id`)
    REFERENCES `manutencao-ufrj`.`Equipament` (`equipament_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


INSERT INTO `manutencao-ufrj`.Role (name)
  VALUES ('Administrador Geral'), ('Administrador de Departamento'), ('Funcionário');

INSERT INTO `manutencao-ufrj`.Department(name)
    VALUES ('Geral');

INSERT INTO `manutencao-ufrj`.User (employee_id, cpf, rg, rg_issuer, name, password, role_id, department_id)
    VALUES ('1234567', '16035256708', '277694709', 'DETRANRJ', 'Eric Reis Figueiredo', '$2a$04$Vz8LR1pmPF22weI2rgEcX.rc9GSeDkxcyxI3Uwymm8/u4mYU02xDK', 1, 1);

INSERT INTO `manutencao-ufrj`.equipament(equipament_registry,last_maintenance,location,maintenance_periodicity,status,is_deleted,department_id)
	VALUES('1111111','2016-02-27', 'Sala H204', 60, false, false, 1)

INSERT INTO `manutencao-ufrj`.maintenance(date, finished_date, description, finished, is_deleted, equipament_id, user_id)
	VALUES('2016-04-27', '2016-05-30', 'Pilha trocada', true, false, 1,1)
