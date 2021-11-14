-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2021 at 06:31 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `groupproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(5) UNSIGNED NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(20000, 'Lighting'),
(30000, 'Power'),
(40000, 'Sound'),
(10000, 'Staging');

-- --------------------------------------------------------

--
-- Table structure for table `company_inventory`
--

CREATE TABLE `company_inventory` (
  `id` int(10) UNSIGNED NOT NULL,
  `equipment_id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(30) NOT NULL,
  `category_id` int(5) UNSIGNED NOT NULL,
  `availability` tinyint(1) UNSIGNED NOT NULL,
  `cost` float UNSIGNED DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`id`, `name`, `category_id`, `availability`, `cost`) VALUES
(23, 'Bose Pro F1', 40000, 1, NULL),
(24, 'Zoook Rocker Boombox', 40000, 2, NULL),
(25, 'boAt Stone 1000', 40000, 1, NULL),
(26, 'JBL SB110', 40000, 1, NULL),
(27, 'Artis BT90', 40000, 2, NULL),
(28, 'Tycon Power (UPS-DC1248-9)', 30000, 1, NULL),
(29, 'ALLWEI Portable Power Station', 30000, 1, NULL),
(30, 'Goal Zero Yeti 200x', 30000, 2, NULL),
(31, 'EF ECOFLOW DELTA Max ', 30000, 1, NULL),
(32, 'BLUETTI Portable Power Station', 30000, 2, NULL),
(33, 'EF ECOFLOW RIVER Pro', 30000, 2, NULL),
(34, 'OPPSK Stage Light', 20000, 1, NULL),
(35, 'TSSS RGBW Pattern Stage Light', 20000, 1, NULL),
(36, 'TENKOO LED Par Stage Light', 20000, 2, NULL),
(37, 'KOOT Stage Lights', 20000, 1, NULL),
(38, 'SAHAUHY Stage Lights', 20000, 2, NULL),
(39, 'BETOPPER Stage Lights', 20000, 1, NULL),
(40, 'LED Smoke Fog Machine', 10000, 1, NULL),
(41, 'Portable 36Wide DJ Workstation', 10000, 1, NULL),
(42, 'CC-500 Crowd Control Barricade', 10000, 2, NULL),
(43, 'Concert Front Of Stage Barrier', 10000, 1, NULL),
(44, 'Guardian Hinged Gate Barrier', 10000, 2, NULL),
(45, 'Guardian 90º Angle Flex Corner', 10000, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `ItemID` varchar(5) NOT NULL,
  `ItemName` varchar(25) NOT NULL,
  `Category` varchar(15) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `DateofRequirement` varchar(10) NOT NULL,
  `ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rental_status`
--

CREATE TABLE `rental_status` (
  `id` tinyint(1) UNSIGNED NOT NULL,
  `status` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rental_status`
--

INSERT INTO `rental_status` (`id`, `status`) VALUES
(1, 'available'),
(2, 'unavailable');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `id` int(10) UNSIGNED NOT NULL,
  `equipment_id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `approve` tinyint(1) UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`id`, `equipment_id`, `date`, `approve`) VALUES
(1, 29, '2021-11-01', 0),
(6, 45, '2021-11-10', 0),
(7, 45, '2021-11-09', 0),
(8, 35, '2021-10-10', 0),
(9, 43, '2021-11-10', 0),
(10, 40, '2021-11-18', 0);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(10) UNSIGNED NOT NULL,
  `equiment_id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `customer_id` int(10) UNSIGNED NOT NULL,
  `approve` tinyint(1) UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `equiment_id`, `date`, `customer_id`, `approve`) VALUES
(1, 27, '2021-11-02', 5, 0),
(2, 45, '2021-10-19', 6, 0),
(3, 27, '2021-11-08', 5, 0),
(4, 23, '2021-11-08', 5, 0),
(5, 40, '2021-11-05', 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `customer_id` varchar(5) DEFAULT NULL,
  `staff_id` varchar(5) DEFAULT NULL,
  `type` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `password`, `customer_id`, `staff_id`, `type`) VALUES
(5, 'Torri-Ann', 'Whittaker', 'torriw@gmail.com', 'Torri23', 'Tor83', NULL, 1),
(6, 'Shirley', 'Temple', 'shirleyt123@yahoo.com', 'alcohol', 'Shi17', NULL, 1),
(7, 'Brandy', 'Smith', 'smithB@hotmail.com', 'newpassword', 'Bra23', NULL, 1),
(8, 'Gwen', 'Tennyson', 'luckygirl500@gmail.com', 'cousin', 'Gwe18', NULL, 1),
(9, 'Mario', 'Vitaly', 'plumber1@yahoo.com', 'pe@ch', 'Mar38', NULL, 1),
(10, 'Torrike', 'Whittaker', 'whittakertorrike@gmail.com', 'ToRRike', NULL, 'Whi25', 2),
(11, 'Oshane', 'Williams', 'oshanewilliams@gmail.com', 'Will9', NULL, 'Wil45', 2),
(12, 'Tavauhni', 'Grant', 'tavaunhigrant@gmail.com', 'Vscode', NULL, 'Gra80', 2),
(13, 'Arrun', 'Thomas', 'arrunthomas@gmail.com', 'p@ssW0rd', NULL, 'Tho73', 2),
(14, 'Amoy', 'Scarlett', 'amoyscarlett@gmail.com', 'grizzlab', NULL, 'Sca77', 2),
(15, NULL, NULL, 'admin@admin.com', 'admin', 'admin', 'admin', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `company_inventory`
--
ALTER TABLE `company_inventory`
  ADD PRIMARY KEY (`id`),
  ADD KEY `inventory_equipment_id_foreign` (`equipment_id`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `equipment_category_id_foreign` (`category_id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `availability` (`availability`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`ItemID`);

--
-- Indexes for table `rental_status`
--
ALTER TABLE `rental_status`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `status` (`status`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `request_equipment_id_foreign_key` (`equipment_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaction_equipemnt_id_foreign` (`equiment_id`),
  ADD KEY `trans_cust_id_foreign` (`customer_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `customer_id` (`customer_id`),
  ADD UNIQUE KEY `staff_id` (`staff_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company_inventory`
--
ALTER TABLE `company_inventory`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `equipment`
--
ALTER TABLE `equipment`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `rental_status`
--
ALTER TABLE `rental_status`
  MODIFY `id` tinyint(1) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`id`) REFERENCES `equipment` (`category_id`) ON DELETE CASCADE;

--
-- Constraints for table `company_inventory`
--
ALTER TABLE `company_inventory`
  ADD CONSTRAINT `inventory_equipment_id_foreign` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `equipment_availability_id_foreign` FOREIGN KEY (`availability`) REFERENCES `rental_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `equipment_category_id_foreign_key` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_equipment_id_foreign_key` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `trans_cust_id_foreign` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_equipemnt_id_foreign` FOREIGN KEY (`equiment_id`) REFERENCES `equipment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
