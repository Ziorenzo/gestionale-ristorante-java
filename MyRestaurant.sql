-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Nov 07, 2023 alle 16:41
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ristorante`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_id` varchar(100) NOT NULL,
  `prod_id` varchar(100) NOT NULL,
  `prod_name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL,
  `image` varchar(500) NOT NULL,
  `em_username` varchar(100) NOT NULL,
  `stato` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `prod_id`, `prod_name`, `type`, `quantity`, `price`, `date`, `image`, `em_username`, `stato`) VALUES
(251, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-13', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(252, '2', 'PROD-001', 'Spaghetti al sugo', 'Meals', 1, 5, '2023-10-13', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\SH_pasta_al_pomodoro-1.jpg', 'aa', 'Pagato'),
(253, '1', 'PROD-002', 'Cacio e pepez', 'Meals', 2, 12, '2023-10-13', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\pasta-cacio-e-pepe-256x256.jpg', 'aa', 'Pagato'),
(254, '1', 'PROD-003', 'Acqua', 'Drinks', 2, 4, '2023-10-13', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(255, '1', 'PROD-004', 'Coca colaz', 'Drinks', 1, 3.5, '2023-10-13', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\download.jpg', 'aa', 'Pagato'),
(256, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-13', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(257, '2', 'PROD-004', 'Coca colaz', 'Drinks', 1, 3.5, '2023-10-13', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\download.jpg', 'aa', 'Pagato'),
(258, '2', 'PROD-001', 'Spaghetti al sugo', 'Meals', 1, 5, '2023-10-13', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\SH_pasta_al_pomodoro-1.jpg', 'aa', 'Pagato'),
(259, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(261, '1', 'PROD-004', 'Coca colaz', 'Drinks', 1, 3.5, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\download.jpg', 'Fabiolino', 'Pagato'),
(262, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(263, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(264, '1', 'PROD-001', 'Spaghetti al sugo', 'Meals', 1, 5, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\SH_pasta_al_pomodoro-1.jpg', 'aa', 'Pagato'),
(265, '1', 'PROD-004', 'Coca colaz', 'Drinks', 1, 3.5, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\download.jpg', 'aa', 'Pagato'),
(266, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(267, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(268, '1', 'PROD-001', 'Spaghetti al sugo', 'Meals', 1, 5, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\SH_pasta_al_pomodoro-1.jpg', 'aa', 'Pagato'),
(269, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(270, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(272, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(273, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-10-14', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(274, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-11-03', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(275, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-11-03', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(277, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-11-07', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato'),
(278, '2', 'PROD-001', 'Spaghetti al sugo', 'Meals', 1, 5, '2023-11-07', 'C:\\\\\\\\Users\\\\\\\\Fabio\\\\\\\\Pictures\\\\\\\\Nuova cartella\\\\\\\\SH_pasta_al_pomodoro-1.jpg', 'aa', 'Pagato'),
(280, '1', 'PROD-004', 'Coca colaz', 'Drinks', 1, 3.5, '2023-11-07', 'C:\\\\\\\\Users\\\\\\\\Fabio\\\\\\\\Pictures\\\\\\\\Nuova cartella\\\\\\\\download.jpg', 'aa', 'Pagato'),
(282, '1', 'PROD-003', 'Acqua', 'Drinks', 1, 2, '2023-11-07', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\acqua.png', 'aa', 'Pagato');

-- --------------------------------------------------------

--
-- Struttura della tabella `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `question` varchar(100) NOT NULL,
  `answer` varchar(100) NOT NULL,
  `date` date DEFAULT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'employee'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `employee`
--

INSERT INTO `employee` (`id`, `username`, `password`, `question`, `answer`, `date`, `role`) VALUES
(1, 'aa', '123', 'What is your favorite food?', 'red', '2023-08-24', 'employee'),
(2, 'bb', '123', 'What is your favorite food?', 'red', '2023-08-24', 'administrator'),
(3, 'Renzo', '123123123', 'What is your favorite Color?', 'red', '2023-10-10', 'employee'),
(4, 'Fabiolino', '123', 'What is your favorite Color?', 'red', '2023-10-14', 'employee'),
(5, 'asca', '123123123123', 'What is your favorite Color?', 'aa', '2023-11-07', 'administrator');

-- --------------------------------------------------------

--
-- Struttura della tabella `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `prod_id` varchar(100) NOT NULL,
  `prod_name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `stock` int(100) NOT NULL,
  `price` double NOT NULL,
  `status` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `product`
--

INSERT INTO `product` (`id`, `prod_id`, `prod_name`, `type`, `stock`, `price`, `status`, `image`, `date`) VALUES
(126, 'PROD-003', 'Acqua', 'Drinks', 180, 2, 'Available', 'C:\\Users\\Fabio\\Pictures\\Nuova cartella\\acqua.png', '2023-09-21'),
(127, 'PROD-001', 'Spaghetti al sugo', 'Meals', 198, 5, 'Available', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\SH_pasta_al_pomodoro-1.jpg', '2023-09-24'),
(128, 'PROD-002', 'Cacio e pepez', 'Meals', 199, 6, 'Available', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\pasta-cacio-e-pepe-256x256.jpg', '2023-09-27'),
(129, 'PROD-004', 'Coca colaz', 'Drinks', 199, 3.5, 'Available', 'C:\\\\Users\\\\Fabio\\\\Pictures\\\\Nuova cartella\\\\download.jpg', '2023-09-27');

-- --------------------------------------------------------

--
-- Struttura della tabella `receipt`
--

CREATE TABLE `receipt` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `total` double NOT NULL,
  `date` date DEFAULT NULL,
  `em_username` varchar(100) NOT NULL,
  `tipo_pagamento` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `receipt`
--

INSERT INTO `receipt` (`id`, `customer_id`, `total`, `date`, `em_username`, `tipo_pagamento`) VALUES
(120, 1, 21.5, '2023-10-13', 'bb', 'Debit Card'),
(121, 2, 5, '2023-10-13', 'bb', 'Credit Card'),
(122, 1, 2, '2023-10-13', 'bb', 'Cash'),
(123, 2, 8.5, '2023-10-13', 'bb', 'Credit Card'),
(124, 1, 2, '2023-10-14', 'bb', 'Cash'),
(125, 1, 3.5, '2023-10-14', 'bb', 'Cash'),
(126, 1, 12.5, '2023-10-14', 'bb', 'Cash'),
(127, 1, 2, '2023-10-14', 'bb', 'Cash'),
(128, 1, 7, '2023-10-14', 'bb', 'Cash'),
(129, 1, 2, '2023-10-14', 'bb', 'Cash'),
(130, 1, 2, '2023-10-14', 'bb', 'Cash'),
(131, 1, 2, '2023-10-14', 'bb', 'Credit Card'),
(132, 1, 2, '2023-11-03', 'bb', 'Cash'),
(133, 1, 2, '2023-11-03', 'bb', 'Cash'),
(134, 1, 2, '2023-11-03', 'bb', 'Cash'),
(135, 1, 5.5, '2023-11-07', 'bb', 'Credit Card'),
(136, 2, 5, '2023-11-07', 'bb', 'Cash'),
(137, 1, 2, '2023-11-07', 'bb', 'Cash');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=283;

--
-- AUTO_INCREMENT per la tabella `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=130;

--
-- AUTO_INCREMENT per la tabella `receipt`
--
ALTER TABLE `receipt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
