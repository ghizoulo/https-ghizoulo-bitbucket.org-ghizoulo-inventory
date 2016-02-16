-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 16 Février 2016 à 22:14
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `inventory`
--

-- --------------------------------------------------------

--
-- Structure de la table `alertes`
--

CREATE TABLE IF NOT EXISTS `alertes` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATEALERTE` date NOT NULL,
  `DATEARRET` date DEFAULT NULL,
  `NBRMAXIMUM` int(11) DEFAULT NULL,
  `NBRMINIMUM` int(11) DEFAULT NULL,
  `TYPEALERTE` varchar(25) NOT NULL,
  `STOCK_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `FKF4183AEAD5EFED43` (`STOCK_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `alertes`
--

INSERT INTO `alertes` (`ID`, `DATEALERTE`, `DATEARRET`, `NBRMAXIMUM`, `NBRMINIMUM`, `TYPEALERTE`, `STOCK_ID`) VALUES
(1, '2016-01-14', NULL, 18, 3, 'AlerteStock', 2),
(2, '2016-01-14', NULL, 0, 60, 'AlerteStock', 1);

-- --------------------------------------------------------

--
-- Structure de la table `depots`
--

CREATE TABLE IF NOT EXISTS `depots` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADRESSE` varchar(40) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `LATITUDE` double DEFAULT NULL,
  `LONGITUDE` double DEFAULT NULL,
  `NOMDEPOT` varchar(25) NOT NULL,
  `RAYON` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  UNIQUE KEY `NOMDEPOT` (`NOMDEPOT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `depots`
--

INSERT INTO `depots` (`ID`, `ADRESSE`, `DESCRIPTION`, `LATITUDE`, `LONGITUDE`, `NOMDEPOT`, `RAYON`) VALUES
(1, 'Route de Taza, Maroc', 'Depot d''artisanat', 34.03445260967644, -4.910888671875, 'Dépot de fes', 0),
(2, '', 'Dépot de l''école nationale des sciences appliquées', 34.2540503, -6.589016600000036, 'EnsakDépot', 0);

-- --------------------------------------------------------

--
-- Structure de la table `familles`
--

CREATE TABLE IF NOT EXISTS `familles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTIONF` varchar(100) DEFAULT NULL,
  `MOYENPRIX` float DEFAULT NULL,
  `MOYENQUANTITE` float DEFAULT NULL,
  `NOMFAMILLE` varchar(25) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `familles`
--

INSERT INTO `familles` (`ID`, `DESCRIPTIONF`, `MOYENPRIX`, `MOYENQUANTITE`, `NOMFAMILLE`) VALUES
(1, 'Produits d''artisanat', 5000, 200, 'Artisanat'),
(2, 'les ordinateurs et leurs accessoires', 6000, 400, 'Ordinateur');

-- --------------------------------------------------------

--
-- Structure de la table `inventaires`
--

CREATE TABLE IF NOT EXISTS `inventaires` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATEINVENTAIRE` date NOT NULL,
  `DESCRIPTIONI` varchar(100) NOT NULL,
  `RESPONSABLEI` varchar(25) NOT NULL,
  `STOCK_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  UNIQUE KEY `DATEINVENTAIRE` (`DATEINVENTAIRE`),
  KEY `FK6379583ED5EFED43` (`STOCK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESIGNATION` varchar(25) NOT NULL,
  `FOURNISSEUR` varchar(25) NOT NULL,
  `IDTAG` varchar(100) NOT NULL,
  `LIBELLE` varchar(25) NOT NULL,
  `PRIX` float NOT NULL,
  `QUANTITE` float NOT NULL,
  `FAMILLE_ID` int(11) NOT NULL,
  `STOCK_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  UNIQUE KEY `IDTAG` (`IDTAG`),
  KEY `FKF2D1C164D5EFED43` (`STOCK_ID`),
  KEY `FKF2D1C16450B17003` (`FAMILLE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `products`
--

INSERT INTO `products` (`ID`, `DESIGNATION`, `FOURNISSEUR`, `IDTAG`, `LIBELLE`, `PRIX`, `QUANTITE`, `FAMILLE_ID`, `STOCK_ID`) VALUES
(4, 'test', 'test', '0000 A017 F681 D53B 4D4B 5E55', 'test', 6000, 400, 2, 1),
(5, 'PC', 'PC-COM', '0090 06CE 4EED FDCC 62BA 9837', 'LenovoPC', 6000, 40, 2, 2),
(6, 'toshiba', 'PC-COM', '0050 36D0 40AE D2C4 EEBC 7A5C', 'ToshibaPC', 6500, 47, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `stocks`
--

CREATE TABLE IF NOT EXISTS `stocks` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MODEALERTE` tinyint(1) NOT NULL,
  `NOMSTOCK` varchar(25) NOT NULL,
  `DEPOT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  UNIQUE KEY `NOMSTOCK` (`NOMSTOCK`),
  KEY `FK9266E01DA7C8B083` (`DEPOT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `stocks`
--

INSERT INTO `stocks` (`ID`, `MODEALERTE`, `NOMSTOCK`, `DEPOT_ID`) VALUES
(1, 1, 'Stock A', 1),
(2, 1, 'StockENSAK', 2);

-- --------------------------------------------------------

--
-- Structure de la table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
  `TRANSACTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATETRANSACTION` date NOT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `TYPETRANSACTION` varchar(25) NOT NULL,
  PRIMARY KEY (`TRANSACTION_ID`),
  UNIQUE KEY `TRANSACTION_ID` (`TRANSACTION_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Contenu de la table `transactions`
--

INSERT INTO `transactions` (`TRANSACTION_ID`, `DATETRANSACTION`, `DESCRIPTION`, `TYPETRANSACTION`) VALUES
(27, '2015-09-22', 'ces produits sont sortis le :', 'Transaction out'),
(28, '2015-09-22', 'ces produits sont sortis le :', 'Transaction out'),
(29, '2015-09-22', 'ces produits sont sortis le :', 'Transaction out'),
(30, '2015-09-23', 'ces produits sont sortis le :', 'Transaction out'),
(31, '2015-09-23', 'ces produits sont sortis le :', 'Transaction out'),
(32, '2015-10-22', 'Ce produit a été bien ajouté au stock! ', 'Transaction d''entrée'),
(33, '2015-11-23', 'Ce produit a été bien ajouté au stock! ', 'Transaction d''entrée'),
(34, '2015-11-23', 'Ce produit a été bien ajouté au stock! ', 'Transaction d''entrée'),
(35, '2015-11-23', 'Ce produit a été bien ajouté au stock! ', 'Transaction d''entrée'),
(36, '2015-11-23', 'Ce produit a été bien ajouté au stock! ', 'Transaction d''entrée'),
(37, '2015-11-23', 'Ce produit a été bien ajouté au stock! ', 'Transaction d''entrée'),
(38, '2016-01-14', 'Ce produit a été bien ajouté au stock! ', 'Transaction d''entrée'),
(39, '2016-01-14', 'Ce produit a été bien ajouté au stock! ', 'Transaction d''entrée'),
(40, '2016-01-14', 'ces produits sont sortis le :', 'Transaction out'),
(41, '2016-01-14', 'ces produits sont sortis le :', 'Transaction out'),
(42, '2016-01-14', 'ces produits sont sortis le :', 'Transaction out'),
(43, '2016-01-14', 'ces produits sont sortis le :', 'Transaction out');

-- --------------------------------------------------------

--
-- Structure de la table `transactions_products`
--

CREATE TABLE IF NOT EXISTS `transactions_products` (
  `ID` int(11) NOT NULL,
  `TRANSACTION_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`,`TRANSACTION_ID`),
  KEY `FK6BF0310E4FE00713` (`ID`),
  KEY `FK6BF0310E1FCF9D83` (`TRANSACTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `transactions_products`
--

INSERT INTO `transactions_products` (`ID`, `TRANSACTION_ID`) VALUES
(4, 31),
(5, 43),
(6, 43);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` varchar(45) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `enabled`) VALUES
(2, 'mkyong', 'e10adc3949ba59abbe56e057f20f883e', 'ACTIVE'),
(3, 'alex', 'e10adc3949ba59abbe56e057f20f883e', 'ACTIVE'),
(4, 'Ghizlane', 'e10adc3949ba59abbe56e057f20f883e', 'ACTIVE');

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`user_id`),
  KEY `fk_user_idx` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `user_id`, `role`) VALUES
(2, 2, 'ROLE_ADMIN'),
(4, 4, 'ROLE_ADMIN'),
(1, 2, 'ROLE_USER'),
(3, 3, 'ROLE_USER'),
(5, 4, 'ROLE_USER');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `alertes`
--
ALTER TABLE `alertes`
  ADD CONSTRAINT `FKF4183AEAD5EFED43` FOREIGN KEY (`STOCK_ID`) REFERENCES `stocks` (`ID`);

--
-- Contraintes pour la table `inventaires`
--
ALTER TABLE `inventaires`
  ADD CONSTRAINT `FK6379583ED5EFED43` FOREIGN KEY (`STOCK_ID`) REFERENCES `stocks` (`ID`);

--
-- Contraintes pour la table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKF2D1C16450B17003` FOREIGN KEY (`FAMILLE_ID`) REFERENCES `familles` (`ID`),
  ADD CONSTRAINT `FKF2D1C164D5EFED43` FOREIGN KEY (`STOCK_ID`) REFERENCES `stocks` (`ID`);

--
-- Contraintes pour la table `stocks`
--
ALTER TABLE `stocks`
  ADD CONSTRAINT `FK9266E01DA7C8B083` FOREIGN KEY (`DEPOT_ID`) REFERENCES `depots` (`ID`);

--
-- Contraintes pour la table `transactions_products`
--
ALTER TABLE `transactions_products`
  ADD CONSTRAINT `FK6BF0310E1FCF9D83` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `transactions` (`TRANSACTION_ID`),
  ADD CONSTRAINT `FK6BF0310E4FE00713` FOREIGN KEY (`ID`) REFERENCES `products` (`ID`);

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
