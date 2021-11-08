-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 08, 2021 at 11:20 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

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
  `category` varchar(10) NOT NULL,
  `availability` tinyint(1) UNSIGNED NOT NULL,
  `eqID` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`id`, `name`, `category`, `availability`, `eqID`) VALUES
(23, 'Bose Pro F1', 'Sound', 1, 'BPF10'),
(24, 'Zoook Rocker Boombox', 'Sound', 2, 'ZRP00'),
(25, 'boAt Stone 1000', 'Sound', 1, 'BS100'),
(26, 'JBL SB110', 'Sound', 1, 'JS000'),
(27, 'Artis BT90', 'Sound', 2, 'AB000'),
(28, 'Tycon Power (UPS-DC1248-9)', 'Power', 1, 'TP000'),
(29, 'ALLWEI Portable Power Station', 'Power', 1, 'APPS0'),
(30, 'Goal Zero Yeti 200x', 'Power', 2, 'GZY20'),
(31, 'EF ECOFLOW DELTA Max ', 'Power', 1, 'EEDM0'),
(32, 'BLUETTI Portable Power Station', 'Power', 2, 'BPPS0'),
(33, 'EF ECOFLOW RIVER Pro', 'Power', 2, 'EERP0'),
(34, 'OPPSK Stage Light', 'Lighting', 1, 'OSL00'),
(35, 'TSSS RGBW Pattern Stage Light', 'Lighting', 1, 'TRPSL'),
(36, 'TENKOO LED Par Stage Light', 'Lighting', 2, 'TLPSL'),
(37, 'KOOT Stage Lights', 'Lighting', 1, 'KSL00'),
(38, 'SAHAUHY Stage Lights', 'Lighting', 2, 'SSL00'),
(39, 'BETOPPER Stage Lights', 'Lighting', 1, 'BSL00'),
(40, 'LED Smoke Fog Machine', 'Staging', 1, 'LSFM0'),
(41, 'Portable 36Wide DJ Workstation', 'Staging', 1, 'PWDW0'),
(42, 'CC-500 Crowd Control Barricade', 'Staging', 2, 'CCCB0'),
(43, 'Concert Front Of Stage Barrier', 'Staging', 1, 'CFOSB'),
(44, 'Guardian Hinged Gate Barrier', 'Staging', 2, 'GHGB0'),
(45, 'Guardian 90º Angle Flex Corner', 'Staging', 1, 'GAFC0');

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
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(10) UNSIGNED NOT NULL,
  `equiment_id` int(10) UNSIGNED NOT NULL,
  `date` varchar(50) NOT NULL,
  `cost` float UNSIGNED DEFAULT NULL,
  `customer_id` int(10) UNSIGNED NOT NULL,
  `custID` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `equiment_id`, `date`, `cost`, `customer_id`, `custID`) VALUES
(1, 27, '2021-11-02', 1000, 5, 'Tor83'),
(2, 45, '2021-10-19', 2500.8, 6, 'Shi17'),
(5, 40, 'January 1, 2021', 0, 5, 'Tor83');

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
(14, 'Amoy', 'Scarlett', 'amoyscarlett@gmail.com', 'grizzlab', NULL, 'Sca77', 2);

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
  ADD KEY `equipment_category_id_foreign` (`category`),
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
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaction_equipemnt_id_foreign` (`equiment_id`),
  ADD KEY `trans_cust_id_foreign` (`customer_id`),
  ADD KEY `custID` (`custID`);

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
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `company_inventory`
--
ALTER TABLE `company_inventory`
  ADD CONSTRAINT `inventory_equipment_id_foreign` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `equipment_availability_id_foreign` FOREIGN KEY (`availability`) REFERENCES `rental_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `custid_custId_foreign` FOREIGN KEY (`custID`) REFERENCES `users` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `trans_cust_id_foreign` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_equipemnt_id_foreign` FOREIGN KEY (`equiment_id`) REFERENCES `equipment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
