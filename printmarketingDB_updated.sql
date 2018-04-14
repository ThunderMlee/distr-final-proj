-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2018 at 05:50 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `printmarketing`
--
CREATE DATABASE IF NOT EXISTS `printmarketing` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `printmarketing`;

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `agentId` int(11) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `streetNumber` int(20) NOT NULL,
  `streetName` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `province` varchar(50) NOT NULL,
  `postalCode` varchar(20) NOT NULL,
  `telOffice` varchar(20) NOT NULL,
  `telCell` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `company` varchar(50) NOT NULL,
  `companyType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `locationName` varchar(100) NOT NULL,
  `distributionCapacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `locationxorders`
--

DROP TABLE IF EXISTS `locationxorders`;
CREATE TABLE `locationxorders` (
  `id` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `locationId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` varchar(50) NOT NULL,
  `agentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `marketingagent`
--

DROP TABLE IF EXISTS `marketingagent`;
CREATE TABLE `marketingagent` (
  `id` int(11) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `phoneNo` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `agentId` int(11) NOT NULL,
  `clientId` int(11) NOT NULL,
  `flyerQty` int(11) NOT NULL,
  `flyerLayout` varchar(50) NOT NULL,
  `flyerImg` blob NOT NULL,
  `personalCopy` int(11) NOT NULL,
  `paymentInformation` varchar(100) NOT NULL,
  `invoiceNumber` varchar(100) NOT NULL,
  `comments` varchar(200) NOT NULL,
  `isFlyerArtApproved` tinyint(1) NOT NULL,
  `isPaymentReceived` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `clients_agentId` (`agentId`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `locationxorders`
--
ALTER TABLE `locationxorders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `locationXordersOrderId` (`orderId`),
  ADD KEY `locationXordersLocationId` (`locationId`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD KEY `agentId` (`agentId`);

--
-- Indexes for table `marketingagent`
--
ALTER TABLE `marketingagent`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ordersAgentId` (`agentId`),
  ADD KEY `ordersClientId` (`clientId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `locationxorders`
--
ALTER TABLE `locationxorders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `marketingagent`
--
ALTER TABLE `marketingagent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `clientsAgentId` FOREIGN KEY (`agentId`) REFERENCES `marketingagent` (`id`);

--
-- Constraints for table `locationxorders`
--
ALTER TABLE `locationxorders`
  ADD CONSTRAINT `locationXordersLocationId` FOREIGN KEY (`locationId`) REFERENCES `location` (`id`),
  ADD CONSTRAINT `locationXordersOrderId` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`);

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `loginAgentId` FOREIGN KEY (`agentId`) REFERENCES `marketingagent` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `ordersAgentId` FOREIGN KEY (`agentId`) REFERENCES `marketingagent` (`id`),
  ADD CONSTRAINT `ordersClienttId` FOREIGN KEY (`clientId`) REFERENCES `clients` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
