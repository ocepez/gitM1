-- Structure de la base de données (Tables)
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema e21901204_db2
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `e21901204_db2` DEFAULT CHARACTER SET utf8 ;
USE `e21901204_db2` ;

-- -----------------------------------------------------
-- Table `e21901204_db2`.`lieu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e21901204_db2`.`lieu` ;

CREATE TABLE IF NOT EXISTS `e21901204_db2`.`lieu` (
                                                      `id_lieu` INT NOT NULL AUTO_INCREMENT,
                                                      `nom_lieu` VARCHAR(100) NOT NULL,
    `adresse_lieu` VARCHAR(100) NOT NULL,
    `capacite_lieu` INT NOT NULL,
    PRIMARY KEY (`id_lieu`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e21901204_db2`.`evenement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e21901204_db2`.`evenement` ;

CREATE TABLE IF NOT EXISTS `e21901204_db2`.`evenement` (
                                                           `id_evenement` INT NOT NULL AUTO_INCREMENT,
                                                           `nom_evenement` VARCHAR(100) NOT NULL,
    `duree_evenement` INT NOT NULL,
    `nb_max_evenement` INT NOT NULL,
    `date_evenement` DATE NOT NULL,
    `id_lieu` INT NOT NULL,
    `desc_evenement` TEXT NOT NULL,
    `image_evenement` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id_evenement`),
    INDEX `fk_evenement_1_idx` (`id_lieu` ASC) VISIBLE,
    CONSTRAINT `fk_evenement_1`
    FOREIGN KEY (`id_lieu`)
    REFERENCES `e21901204_db2`.`lieu` (`id_lieu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e21901204_db2`.`membre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e21901204_db2`.`membre` ;

CREATE TABLE IF NOT EXISTS `e21901204_db2`.`membre` (
                                                        `id_membre` INT NOT NULL AUTO_INCREMENT,
                                                        `email_membre` VARCHAR(100) NOT NULL,
    `nom_membre` VARCHAR(100) NOT NULL,
    `prenom_membre` VARCHAR(100) NOT NULL,
    `role_membre` VARCHAR(100) NOT NULL,
    `adresse_membre` VARCHAR(100) NOT NULL,
    `photo_membre` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id_membre`),
    UNIQUE INDEX `email_membre_UNIQUE` (`email_membre` ASC) VISIBLE)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `e21901204_db2`.`inscription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e21901204_db2`.`inscription` ;

CREATE TABLE IF NOT EXISTS `e21901204_db2`.`inscription` (
                                                             `email_membre` VARCHAR(100) NOT NULL,
    `id_evenement` INT NOT NULL,
    INDEX `fk_inscription_1_idx` (`id_evenement` ASC) VISIBLE,
    INDEX `fk_inscription_2_idx` (`email_membre` ASC) VISIBLE,
    CONSTRAINT `fk_inscription_1`
    FOREIGN KEY (`id_evenement`)
    REFERENCES `e21901204_db2`.`evenement` (`id_evenement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_inscription_2`
    FOREIGN KEY (`email_membre`)
    REFERENCES `e21901204_db2`.`membre` (`email_membre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- Données initiales

-- Data for table `e21901204_db2`.`lieu`
START TRANSACTION;
USE `e21901204_db2`;
INSERT INTO `e21901204_db2`.`lieu` (`id_lieu`, `nom_lieu`, `adresse_lieu`, `capacite_lieu`) VALUES (1, 'Opera', 'Paris', 10000);
INSERT INTO `e21901204_db2`.`lieu` (`id_lieu`, `nom_lieu`, `adresse_lieu`, `capacite_lieu`) VALUES (2, 'Quartz', 'Brest', 5000);
INSERT INTO `e21901204_db2`.`lieu` (`id_lieu`, `nom_lieu`, `adresse_lieu`, `capacite_lieu`) VALUES (DEFAULT, 'Arena', 'Brest', 7000);
INSERT INTO `e21901204_db2`.`lieu` (`id_lieu`, `nom_lieu`, `adresse_lieu`, `capacite_lieu`) VALUES (3, 'Stade de France', 'Saint-Denis', 80000);
INSERT INTO `e21901204_db2`.`lieu` (`id_lieu`, `nom_lieu`, `adresse_lieu`, `capacite_lieu`) VALUES (4, 'Palais des Congrès', 'Paris', 5000);
COMMIT;

-- Data for table `e21901204_db2`.`evenement`
START TRANSACTION;
USE `e21901204_db2`;
INSERT INTO `e21901204_db2`.`evenement` (`id_evenement`, `nom_evenement`, `duree_evenement`, `nb_max_evenement`, `date_evenement`, `id_lieu`) VALUES (1, 'Ballet', 2, 3000, '2023-12-01', 1);
INSERT INTO `e21901204_db2`.`evenement` (`id_evenement`, `nom_evenement`, `duree_evenement`, `nb_max_evenement`, `date_evenement`, `id_lieu`) VALUES (2, 'Le malade imaginaire', 3, 4000, '2024-12-01', 2);
INSERT INTO `e21901204_db2`.`evenement` (`id_evenement`, `nom_evenement`, `duree_evenement`, `nb_max_evenement`, `date_evenement`, `id_lieu`) VALUES (3, 'Concert Rock', 4, 10000, '2024-03-15', 3);
INSERT INTO `e21901204_db2`.`evenement` (`id_evenement`, `nom_evenement`, `duree_evenement`, `nb_max_evenement`, `date_evenement`, `id_lieu`) VALUES (4, 'Conférence Tech', 2, 500, '2024-04-20', 4);
COMMIT;

-- Data for table `e21901204_db2`.`membre`
START TRANSACTION;
USE `e21901204_db2`;
INSERT INTO `e21901204_db2`.`membre` (`id_membre`, `email_membre`, `nom_membre`, `prenom_membre`, `age_membre`, `adresse_membre`) VALUES (1, 'oceane@gmail.com', 'pez', 'oce', 22, '4 rue Nap, 29200 Brest');
INSERT INTO `e21901204_db2`.`membre` (`id_membre`, `email_membre`, `nom_membre`, `prenom_membre`, `age_membre`, `adresse_membre`) VALUES (DEFAULT, 'maxime@gmail.com', 'andre', 'maxime', 21, '5, square du Roussillon, 29200 Brest');
INSERT INTO `e21901204_db2`.`membre` (`id_membre`, `email_membre`, `nom_membre`, `prenom_membre`, `age_membre`, `adresse_membre`) VALUES (3, 'lucas@gmail.com', 'Duran', 'Lucas', 25, '123 Rue de la République, 13002 Marseille');
INSERT INTO `e21901204_db2`.`membre` (`id_membre`, `email_membre`, `nom_membre`, `prenom_membre`, `age_membre`, `adresse_membre`) VALUES (4, 'emma@gmail.com', 'Martin', 'Emma', 30, '8 Avenue des Fleurs, 06000 Nice');
COMMIT;

INSERT INTO `e21901204_db2`.`evenement` (`id_evenement`, `nom_evenement`, `duree_evenement`, `nb_max_evenement`, `date_evenement`, `id_lieu`,`desc_evenement`,`image_evenement`) VALUES (1, 'Ballet', 2, 3000, '2023-12-01', 1, 'Evenement ballet', '/');
INSERT INTO `e21901204_db2`.`evenement` (`id_evenement`, `nom_evenement`, `duree_evenement`, `nb_max_evenement`, `date_evenement`, `id_lieu`,`desc_evenement`,`image_evenement`) VALUES (2, 'Le malade imaginaire', 3, 4000, '2024-12-01', 2, 'Evenement theatre', '/');
INSERT INTO `e21901204_db2`.`evenement` (`id_evenement`, `nom_evenement`, `duree_evenement`, `nb_max_evenement`, `date_evenement`, `id_lieu`,`desc_evenement`,`image_evenement`) VALUES (3, 'Concert Rock', 4, 10000, '2024-03-15', 3, 'Evenement concert', '/');
INSERT INTO `e21901204_db2`.`evenement` (`id_evenement`, `nom_evenement`, `duree_evenement`, `nb_max_evenement`, `date_evenement`, `id_lieu`,`desc_evenement`,`image_evenement`) VALUES (4, 'Conférence Tech', 2, 500, '2024-04-20', 4, 'Evenement conférence', '/');
COMMIT;