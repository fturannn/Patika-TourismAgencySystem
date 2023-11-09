-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Kas 2023, 16:11:38
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
-- Tablo için tablo yapısı `hotel`
--

CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `region` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `star` enum('1','2','3','4','5') COLLATE utf8mb4_general_ci NOT NULL,
  `feature` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '-',
  `hostel` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '-'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `city`, `region`, `address`, `email`, `telephone`, `star`, `feature`, `hostel`) VALUES
(1, 'Patika Otel', 'İstanbul', 'Ataşehir', 'Ataşehir / İstanbul', 'patika@patika.com', '05463827302', '5', 'Ücretsiz Otopark Yüzme Havuzu SPA Ücretsiz Wifi ', 'Herşey Dahil Tam Pansiyon '),
(2, 'İstanbul Hilltown Hotel', 'İstanbul', 'Maltepe', 'Maltepe / İstanbul', 'hilltown@hotel.com', '04637283298', '5', 'Yüzme Havuzu Fitness Center SPA Ücretsiz Wifi ', 'Ultra Herşey Dahil '),
(3, 'Hotel Giresun', 'Giresun', 'Merkez', 'Merkez / Giresun', 'hotelgiresun@email.com', '04548392837', '3', '7/24 Oda Servisi Ücretsiz Wifi ', 'Tam Pansiyon Yarım Pansiyon '),
(4, 'Ankara Grand Hotel', 'Ankara', 'Kızılay', 'Kızılay / Ankara', 'ankaragrand@hotel.com', '05473948238', '4', 'Fitness Center Ücretsiz Wifi ', 'Herşey Dahil '),
(5, 'İzmir Deluxe Hotel', 'İzmir', 'Karşıyaka', 'Karşıyaka / İzmir', 'hoteldeluxe@email.com', '05362738293', '4', 'Yüzme Havuzu Hotel Concierge SPA ', 'Oda Kahvaltı Alkol Hariç Full Credit ');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
