-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Nov 18, 2020 at 02:59 PM
-- Server version: 8.0.22
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crud-application`
--

-- --------------------------------------------------------

--
-- Table structure for table `quote`
--

CREATE TABLE `quote` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `date` varchar(255) DEFAULT NULL ,
  `quote` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `quote`
--

INSERT INTO `quote` (`id`, `date`, `quote`, `source`) VALUES
(7, '1605646652', 'Great leaders inspire greatness in others', 'Star Wars: The Clone Wars 01×01 – Ambush'),
(8, '1605652409', 'Belief is not a matter of choice, but of conviction.', 'Star Wars: The Clone Wars 01×02 – Rising Malevolence'),
(9, '1605652433', 'Easy is the path to wisdom for those not blinded by ego.', 'Star Wars: The Clone Wars 01×03 – Shadow of Malevolence'),
(10, '1605652447', 'A plan is only as good as those who see it through.', 'Star Wars: The Clone Wars 01×04 – Destroy Malevolence'),
(11, '1605652458', 'The best confidence builder is experience.', 'Star Wars: The Clone Wars 01×05 – Rookies'),
(12, '1605652468', 'Trust in your friends, and they’ll have reason to trust in you.', 'Star Wars: The Clone Wars 01×06 – Downfall of a Droid'),
(22, '1605711409', 'You hold onto friends by keeping your heart a little softer than your head.', 'Star Wars: The Clone Wars 01×07 – Duel of the Droids'),
(23, '1605711453', 'Heroes are made by the times.', 'Star Wars: The Clone Wars 01×08 – Bombad Jedi'),
(24, '1605711485', 'Ignore your instincts at your peril.', 'Star Wars: The Clone Wars 01×09 – Cloak of Darkness'),
(25, '1605711520', 'Most powerful is he who controls his own power.', 'Star Wars: The Clone Wars 01×10 – Lair of Grievous');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `quote`

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;