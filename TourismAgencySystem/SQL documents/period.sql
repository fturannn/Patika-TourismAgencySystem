-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Kas 2023, 16:19:40
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `turizm_acenta`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `period`
--

CREATE TABLE `period` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `period_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `period_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `period` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `period`
--

INSERT INTO `period` (`id`, `hotel_id`, `period_start`, `period_end`, `period`) VALUES
(1, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024'),
(2, 1, '01/06/2024', '31/12/2024', '01/06/2024-31/12/2024'),
(3, 2, '01/03/2024', '31/10/2024', '01/03/2024-31/10/2024'),
(4, 2, '01/11/2024', '28/02/2025', '01/11/2024-28/02/2025'),
(5, 3, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024'),
(6, 3, '01/06/2024', '31/12/2024', '01/06/2024-31/12/2024'),
(7, 4, '01/04/2024', '30/11/2024', '01/04/2024-30/11/2024'),
(8, 4, '01/12/2024', '30/04/2025', '01/12/2024-30/04/2025'),
(9, 5, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024'),
(10, 5, '01/06/2024', '31/12/2024', '01/06/2024-31/12/2024');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `period`
--
ALTER TABLE `period`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `period`
--
ALTER TABLE `period`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
