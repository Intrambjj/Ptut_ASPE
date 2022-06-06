-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2022 at 08:02 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projet_tut`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`, `image`) VALUES
(1, 'Longboard', 'image\\categorie\\longboard\\2099486.jpg'),
(2, 'Cruiser', 'image\\categorie\\cruiser\\cruiser.jpg'),
(3, 'Skateboard', 'image\\categorie\\skateboard\\skating-skate-skateboard-skateboarding.jpg'),
(4, 'Promotion', '');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL COMMENT 'not null',
  `mail` varchar(255) NOT NULL COMMENT 'not null',
  `mdp` varchar(255) NOT NULL COMMENT 'not null ',
  `prenom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `nom`, `mail`, `mdp`, `prenom`) VALUES
(1, 'GUY', 'jacqueg@gmail.com', 'jg42', 'Jacques'),
(2, 'LOUIS', 'martin42@gmail.com', 'lala42', 'martin'),
(3, 'MARTIN', 'jean.martin@gmail.com', 'jeanMdu32', 'Jean'),
(4, 'VIOTTA', 'julie67@gmail.com', 'julie6767', 'Julie'),
(5, 'SMITH', 'SMITH.Will@gmail.com', 'smithwill', 'Will'),
(6, 'UZUMAKI', 'rasengan@gmail.com', 'sasuke42', 'Naruto\r\n'),
(7, 'UCHIWA', 'kaleidoscope@gmail.com', 'killitachi', 'Sasuke'),
(8, 'Auchard', 'squeezie@gmail.com', 'viveyoutube', 'Lucas'),
(10, 'idontknow', 'rossfriend@gmail.com', 'tropcool', 'Ross'),
(13, 'jules', 'julescesar@gmail.com', 'rpzlarome', 'cesar'),
(14, 'LE ROUX', 'mugiwara@gmail.com', 'hakiroyal', 'shanks'),
(15, 'Pecquet', 'mp@gmail.com', 'mp', 'Martin');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL COMMENT 'not null',
  `prix` double NOT NULL COMMENT 'not null',
  `marque` varchar(255) NOT NULL COMMENT 'not null',
  `description` varchar(255) NOT NULL,
  `image1` varchar(255) DEFAULT NULL,
  `image2` varchar(255) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `seuil_alerte` int(11) NOT NULL,
  `image3` varchar(255) DEFAULT NULL,
  `id_sous_categorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `nom`, `prix`, `marque`, `description`, `image1`, `image2`, `stock`, `seuil_alerte`, `image3`, `id_sous_categorie`) VALUES
(1, 'Skateboard 180', 60, 'tony hawk', 'undefined', 'image\\skate\\skate1\\tony-hawk-180-series-complete-skateboard-y2.jpg', 'image\\skate\\skate1\\tony-hawk-180-series-complete-skateboard-8d.jpg', 30, 5, 'image\\skate\\skate1\\tony-hawk-180-series-complete-skateboard-kt.jpg', 7),
(3, 'goodstock skateboard', 80, 'Globe', 'undefined', 'image\\skate\\skate2\\globe-goodstock-complete-skateboard-1t.jpg', 'image\\skate\\skate2\\globe-goodstock-complete-skateboard-7w.jpg', 30, 5, 'image\\skate\\skate2\\globe-goodstock-complete-skateboard-uy.jpg', 7),
(4, 'Skateboard complet section', 90, 'Element', 'undefined', 'image\\skate\\skate3\\element-complete-skateboard-78.jpg', 'image\\skate\\skate3\\element-complete-skateboard-cg.jpg', 10, 2, 'image\\skate\\skate3\\element-complete-skateboard-nh.jpg', 7),
(5, 'classic Skateboard', 105, 'Jart', 'undefined', 'image\\skate\\skate4\\jart-classic-complete-skateboard-1a.jpg', 'image\\skate\\skate4\\jart-classic-complete-skateboard-6r.jpg', 15, 5, '', 7),
(6, 'Drop Hammer Longboard', 250, 'Landyachtz', '', 'image\\longboard\\longboard1\\landyachtz-drop-hammer-complete-longboard-tu.jpg', 'image\\longboard\\longboard1\\landyachtz-drop-hammer-complete-longboard-m5.jpg', 10, 5, 'image\\longboard\\longboard1\\landyachtz-drop-hammer-complete-longboard-ri.jpg', 1),
(7, 'Tribal Rogue IV Longboard', 95, 'Mindless', '', 'image\\longboard\\longboard2\\mindless-tribal-rogue-iv-complete-longboard.jpg', 'image\\longboard\\longboard2\\mindless-tribal-rogue-iv-complete-longboard-mj.jpg', 10, 5, 'image\\longboard\\longboard2\\mindless-tribal-rogue-iv-complete-longboard-fj.jpg', 3),
(8, 'Pintail Longboard Complet', 145, 'Ocean Pacific', '', 'image\\longboard\\longboard3\\ocean-pacific-pintail-complete-longboard-g5.jpg', 'image\\longboard\\longboard3\\ocean-pacific-pintail-complete-longboard-r6.jpg', 10, 5, 'image\\longboard\\longboard3\\ocean-pacific-pintail-complete-longboard-qw.jpg', 1),
(10, 'Dinghy Cruiser Skate Complet', 200, 'Landyachtz', '', 'image\\cruiser\\cruiser1\\landyachtz-dinghy-cruiser-skateboard-2.jpg', 'image\\cruiser\\cruiser1\\landyachtz-dinghy-cruiser-skateboard-s6.jpg', 20, 10, 'image\\cruiser\\cruiser1\\landyachtz-dinghy-cruiser-skateboard-58.jpg', 4),
(11, 'Screaming Hand Cruiser Skate', 160, 'Santa Cruz ', '', 'image\\cruiser\\cruiser2\\santa-cruz-screaming-hand-cruiser-skateboard-gn.jpg', 'image\\cruiser\\cruiser2\\santa-cruz-screaming-hand-cruiser-skateboard-7l.jpg', 20, 10, 'image\\cruiser\\cruiser2\\santa-cruz-screaming-hand-cruiser-skateboard-7l.jpg', 5),
(12, 'Cruiser Skate Complet', 110, 'Element', '', 'image\\cruiser\\cruiser3\\element-cruiser-skateboard.jpg', 'image\\cruiser\\cruiser3\\element-cruiser-skateboard-e4.jpg', 15, 8, '', 6),
(13, 'Mandala Cruiser Skate Complet', 90, 'Mindless', '', 'image\\cruiser\\cruiser4\\mindless-mandala-cruiser-skateboard-dg.jpg', 'image\\cruiser\\cruiser4\\mindless-mandala-cruiser-skateboard-e2.jpg', 8, 4, 'image\\cruiser\\cruiser4\\mindless-mandala-cruiser-skateboard-q2.jpg', 4);

-- --------------------------------------------------------

--
-- Table structure for table `produitcategorie`
--

CREATE TABLE `produitcategorie` (
  `id_produit` int(11) NOT NULL,
  `id_categorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `produitcategorie`
--

INSERT INTO `produitcategorie` (`id_produit`, `id_categorie`) VALUES
(1, 3),
(3, 3),
(4, 3),
(5, 3),
(6, 1),
(7, 1),
(8, 1),
(10, 2),
(11, 2),
(12, 2),
(13, 2);

-- --------------------------------------------------------

--
-- Table structure for table `produitclient`
--

CREATE TABLE `produitclient` (
  `id_produit` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `date_achat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `produitpromo`
--

CREATE TABLE `produitpromo` (
  `id_produit` int(11) NOT NULL,
  `id_promo` int(11) NOT NULL,
  `new_price` double NOT NULL COMMENT 'not null',
  `stock_restant` int(11) NOT NULL COMMENT 'not null'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id` int(11) NOT NULL,
  `date_début` date NOT NULL COMMENT 'not null',
  `date_fin` date NOT NULL COMMENT 'not null'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `souscategorie`
--

CREATE TABLE `souscategorie` (
  `id_sous_categorie` int(11) NOT NULL,
  `id_categorie` int(11) NOT NULL,
  `nom_sous_categorie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `souscategorie`
--

INSERT INTO `souscategorie` (`id_sous_categorie`, `id_categorie`, `nom_sous_categorie`) VALUES
(1, 1, 'Freeride'),
(2, 1, 'Downhill'),
(3, 1, 'Dancing'),
(4, 2, 'Carving'),
(5, 2, 'Casual'),
(6, 2, 'Plateau bois'),
(7, 3, 'Complet'),
(8, 3, 'Enfant'),
(9, 3, 'Planche');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_sous_categorie` (`id_sous_categorie`);

--
-- Indexes for table `produitcategorie`
--
ALTER TABLE `produitcategorie`
  ADD PRIMARY KEY (`id_produit`,`id_categorie`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- Indexes for table `produitclient`
--
ALTER TABLE `produitclient`
  ADD PRIMARY KEY (`id_produit`,`id_client`),
  ADD KEY `id_client` (`id_client`);

--
-- Indexes for table `produitpromo`
--
ALTER TABLE `produitpromo`
  ADD PRIMARY KEY (`id_produit`,`id_promo`),
  ADD KEY `id_promo` (`id_promo`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `souscategorie`
--
ALTER TABLE `souscategorie`
  ADD PRIMARY KEY (`id_sous_categorie`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `souscategorie`
--
ALTER TABLE `souscategorie`
  MODIFY `id_sous_categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_sous_categorie`) REFERENCES `souscategorie` (`id_sous_categorie`);

--
-- Constraints for table `produitcategorie`
--
ALTER TABLE `produitcategorie`
  ADD CONSTRAINT `produitcategorie_ibfk_1` FOREIGN KEY (`id_produit`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `produitcategorie_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id`);

--
-- Constraints for table `produitclient`
--
ALTER TABLE `produitclient`
  ADD CONSTRAINT `produitclient_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `produitclient_ibfk_2` FOREIGN KEY (`id_produit`) REFERENCES `product` (`id`);

--
-- Constraints for table `produitpromo`
--
ALTER TABLE `produitpromo`
  ADD CONSTRAINT `produitpromo_ibfk_1` FOREIGN KEY (`id_produit`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `produitpromo_ibfk_2` FOREIGN KEY (`id_promo`) REFERENCES `promotion` (`id`);

--
-- Constraints for table `souscategorie`
--
ALTER TABLE `souscategorie`
  ADD CONSTRAINT `souscategorie_ibfk_1` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
