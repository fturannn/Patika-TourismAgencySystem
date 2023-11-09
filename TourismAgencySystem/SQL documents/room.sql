-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Kas 2023, 16:22:15
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
-- Tablo için tablo yapısı `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `period_id` int NOT NULL,
  `room_name` enum('Single Oda','Double Oda','Suit Oda','Kral Dairesi') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stock` int NOT NULL,
  `bed` enum('1 Kişilik','2 Kişilik','3 Kişilik','4 Kişilik','5 Kişilik','Kral Dairesi') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tv` enum('var','yok') COLLATE utf8mb4_general_ci NOT NULL,
  `minibar` enum('var','yok') COLLATE utf8mb4_general_ci NOT NULL,
  `game_console` enum('var','yok') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`id`, `hotel_id`, `period_id`, `room_name`, `stock`, `bed`, `tv`, `minibar`, `game_console`) VALUES
(1, 1, 1, 'Single Oda', 14, '1 Kişilik', 'var', 'var', 'yok'),
(2, 1, 2, 'Single Oda', 15, '1 Kişilik', 'var', 'var', 'yok'),
(3, 1, 1, 'Double Oda', 10, '2 Kişilik', 'var', 'var', 'var'),
(4, 1, 2, 'Double Oda', 10, '2 Kişilik', 'var', 'var', 'var'),
(5, 2, 3, 'Kral Dairesi', 9, 'Kral Dairesi', 'var', 'var', 'var'),
(6, 2, 4, 'Kral Dairesi', 10, 'Kral Dairesi', 'var', 'var', 'var'),
(7, 3, 5, 'Single Oda', 25, '1 Kişilik', 'yok', 'yok', 'yok'),
(8, 3, 6, 'Single Oda', 24, '1 Kişilik', 'yok', 'yok', 'yok'),
(9, 4, 7, 'Single Oda', 14, '1 Kişilik', 'var', 'var', 'yok'),
(10, 4, 7, 'Suit Oda', 10, '3 Kişilik', 'var', 'var', 'yok'),
(11, 4, 8, 'Single Oda', 15, '1 Kişilik', 'var', 'var', 'yok'),
(12, 4, 8, 'Suit Oda', 10, '3 Kişilik', 'var', 'var', 'yok'),
(13, 5, 9, 'Suit Oda', 20, '4 Kişilik', 'var', 'var', 'var'),
(14, 5, 10, 'Suit Oda', 19, '4 Kişilik', 'var', 'var', 'var');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
