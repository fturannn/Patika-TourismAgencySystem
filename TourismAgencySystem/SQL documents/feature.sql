-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Kas 2023, 16:26:31
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
-- Tablo için tablo yapısı `feature`
--

CREATE TABLE `feature` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `feature` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `feature`
--

INSERT INTO `feature` (`id`, `hotel_id`, `feature`) VALUES
(1, 1, 'Ücretsiz Otopark'),
(2, 1, 'Yüzme Havuzu'),
(3, 1, 'SPA'),
(4, 1, 'Ücretsiz Wifi'),
(5, 2, 'Yüzme Havuzu'),
(6, 2, 'Fitness Center'),
(7, 2, 'SPA'),
(8, 2, 'Ücretsiz Wifi'),
(9, 3, '7/24 Oda Servisi'),
(10, 3, 'Ücretsiz Wifi'),
(11, 4, 'Fitness Center'),
(12, 4, 'Ücretsiz Wifi'),
(13, 5, 'Yüzme Havuzu'),
(14, 5, 'Hotel Concierge'),
(15, 5, 'SPA');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `feature`
--
ALTER TABLE `feature`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `feature`
--
ALTER TABLE `feature`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
