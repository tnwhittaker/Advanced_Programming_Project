-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2021 at 08:19 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

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
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `ID` int(5) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `AccountType` char(1) NOT NULL,
  `Phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`ID`, `Password`, `Name`, `AccountType`, `Phone`) VALUES
(1001, 'Password', 'Whittaker', 'E', ''),
(1002, 'password', 'Arrun Thomas', 'C', '8761234567');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `EventId` int(6) NOT NULL,
  `ID` int(5) NOT NULL,
  `EventName` varchar(100) NOT NULL,
  `EventDate` date NOT NULL,
  `EventStatus` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`EventId`, `ID`, `EventName`, `EventDate`, `EventStatus`) VALUES
(3001, 1002, 'Dante\'s Rave', '2021-10-20', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `ItemID` varchar(5) NOT NULL,
  `ItemName` varchar(25) NOT NULL,
  `Category` varchar(15) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `Cost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`ItemID`, `ItemName`, `Category`, `Status`, `Cost`) VALUES
('ABC1', 'Shoes', 'Large', 'Online', 20);

-- --------------------------------------------------------

--
-- Table structure for table `rentals`
--

CREATE TABLE `rentals` (
  `EventId` int(6) NOT NULL,
  `EventName` varchar(100) NOT NULL,
  `EventDate` date NOT NULL,
  `ItemID` varchar(5) NOT NULL,
  `ItemName` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rentals`
--

INSERT INTO `rentals` (`EventId`, `EventName`, `EventDate`, `ItemID`, `ItemName`) VALUES
(3001, '', '0000-00-00', 'ABC1', '');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `ID` int(5) NOT NULL,
  `EventId` int(6) NOT NULL,
  `EventName` varchar(100) NOT NULL,
  `EventDate` date NOT NULL,
  `TotalCost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`ID`, `EventId`, `EventName`, `EventDate`, `TotalCost`) VALUES
(1002, 3001, '', '0000-00-00', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`EventId`),
  ADD KEY `ID` (`ID`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`ItemID`);

--
-- Indexes for table `rentals`
--
ALTER TABLE `rentals`
  ADD PRIMARY KEY (`EventId`),
  ADD KEY `ItemID` (`ItemID`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `EventId` (`EventId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `account` (`ID`);

--
-- Constraints for table `rentals`
--
ALTER TABLE `rentals`
  ADD CONSTRAINT `rentals_ibfk_1` FOREIGN KEY (`EventId`) REFERENCES `events` (`EventId`),
  ADD CONSTRAINT `rentals_ibfk_2` FOREIGN KEY (`ItemID`) REFERENCES `inventory` (`ItemID`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `account` (`ID`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`EventId`) REFERENCES `events` (`EventId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
