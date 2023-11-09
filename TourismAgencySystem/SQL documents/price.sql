-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Kas 2023, 16:28:29
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
-- Tablo için tablo yapısı `price`
--

CREATE TABLE `price` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `period_id` int NOT NULL,
  `period_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `period_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int NOT NULL,
  `hostel` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `age` enum('Yetişkin 12+','Çocuk 4-11') COLLATE utf8mb4_general_ci NOT NULL,
  `price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `price`
--

INSERT INTO `price` (`id`, `hotel_id`, `city`, `period_id`, `period_start`, `period_end`, `room_id`, `hostel`, `age`, `price`) VALUES
(1, 1, 'İstanbul', 1, '01/01/2024', '31/05/2024', 1, 'Herşey Dahil', 'Yetişkin 12+', 1500),
(2, 1, 'İstanbul', 1, '01/01/2024', '31/05/2024', 1, 'Herşey Dahil', 'Çocuk 4-11', 1300),
(3, 1, 'İstanbul', 1, '01/01/2024', '31/05/2024', 3, 'Herşey Dahil', 'Yetişkin 12+', 3000),
(4, 1, 'İstanbul', 1, '01/01/2024', '31/05/2024', 3, 'Herşey Dahil', 'Çocuk 4-11', 2800),
(5, 1, 'İstanbul', 1, '01/01/2024', '31/05/2024', 1, 'Tam Pansiyon', 'Yetişkin 12+', 1000),
(6, 1, 'İstanbul', 1, '01/01/2024', '31/05/2024', 1, 'Tam Pansiyon', 'Çocuk 4-11', 800),
(7, 1, 'İstanbul', 1, '01/01/2024', '31/05/2024', 3, 'Tam Pansiyon', 'Yetişkin 12+', 2000),
(8, 1, 'İstanbul', 1, '01/01/2024', '31/05/2024', 3, 'Tam Pansiyon', 'Çocuk 4-11', 1800),
(9, 1, 'İstanbul', 2, '01/06/2024', '31/12/2024', 2, 'Herşey Dahil', 'Yetişkin 12+', 2500),
(10, 1, 'İstanbul', 2, '01/06/2024', '31/12/2024', 2, 'Herşey Dahil', 'Çocuk 4-11', 2300),
(11, 1, 'İstanbul', 2, '01/06/2024', '31/12/2024', 4, 'Herşey Dahil', 'Yetişkin 12+', 5000),
(12, 1, 'İstanbul', 2, '01/06/2024', '31/12/2024', 4, 'Herşey Dahil', 'Çocuk 4-11', 4800),
(13, 1, 'İstanbul', 2, '01/06/2024', '31/12/2024', 2, 'Tam Pansiyon', 'Yetişkin 12+', 2000),
(14, 1, 'İstanbul', 2, '01/06/2024', '31/12/2024', 2, 'Tam Pansiyon', 'Çocuk 4-11', 1800),
(15, 1, 'İstanbul', 2, '01/06/2024', '31/12/2024', 4, 'Tam Pansiyon', 'Yetişkin 12+', 4000),
(16, 1, 'İstanbul', 2, '01/06/2024', '31/12/2024', 4, 'Tam Pansiyon', 'Çocuk 4-11', 3800),
(17, 2, 'İstanbul', 3, '01/03/2024', '31/10/2024', 5, 'Ultra Herşey Dahil', 'Yetişkin 12+', 5000),
(18, 2, 'İstanbul', 3, '01/03/2024', '31/10/2024', 5, 'Ultra Herşey Dahil', 'Çocuk 4-11', 4800),
(19, 2, 'İstanbul', 4, '01/11/2024', '28/02/2025', 6, 'Ultra Herşey Dahil', 'Yetişkin 12+', 3800),
(20, 2, 'İstanbul', 4, '01/11/2024', '28/02/2025', 6, 'Ultra Herşey Dahil', 'Çocuk 4-11', 3600),
(21, 3, 'Giresun', 5, '01/01/2024', '31/05/2024', 7, 'Tam Pansiyon', 'Yetişkin 12+', 1500),
(22, 3, 'Giresun', 5, '01/01/2024', '31/05/2024', 7, 'Tam Pansiyon', 'Çocuk 4-11', 1300),
(23, 3, 'Giresun', 5, '01/01/2024', '31/05/2024', 7, 'Yarım Pansiyon', 'Yetişkin 12+', 1000),
(24, 3, 'Giresun', 5, '01/01/2024', '31/05/2024', 7, 'Yarım Pansiyon', 'Çocuk 4-11', 800),
(25, 3, 'Giresun', 6, '01/06/2024', '31/12/2024', 8, 'Tam Pansiyon', 'Yetişkin 12+', 1000),
(26, 3, 'Giresun', 6, '01/06/2024', '31/12/2024', 8, 'Tam Pansiyon', 'Çocuk 4-11', 800),
(27, 3, 'Giresun', 6, '01/06/2024', '31/12/2024', 8, 'Yarım Pansiyon', 'Yetişkin 12+', 800),
(28, 3, 'Giresun', 6, '01/06/2024', '31/12/2024', 8, 'Yarım Pansiyon', 'Çocuk 4-11', 600),
(29, 4, 'Ankara', 7, '01/04/2024', '30/11/2024', 9, 'Herşey Dahil', 'Yetişkin 12+', 2500),
(30, 4, 'Ankara', 7, '01/04/2024', '30/11/2024', 9, 'Herşey Dahil', 'Çocuk 4-11', 2300),
(31, 4, 'Ankara', 7, '01/04/2024', '30/11/2024', 10, 'Herşey Dahil', 'Yetişkin 12+', 3500),
(32, 4, 'Ankara', 7, '01/04/2024', '30/11/2024', 10, 'Herşey Dahil', 'Çocuk 4-11', 3300),
(33, 4, 'Ankara', 8, '01/12/2024', '30/04/2025', 11, 'Herşey Dahil', 'Yetişkin 12+', 2000),
(34, 4, 'Ankara', 8, '01/12/2024', '30/04/2025', 11, 'Herşey Dahil', 'Çocuk 4-11', 1800),
(35, 4, 'Ankara', 8, '01/12/2024', '30/04/2025', 12, 'Herşey Dahil', 'Yetişkin 12+', 3000),
(36, 4, 'Ankara', 8, '01/12/2024', '30/04/2025', 12, 'Herşey Dahil', 'Çocuk 4-11', 2800),
(37, 5, 'İzmir', 9, '01/01/2024', '31/05/2024', 13, 'Oda Kahvaltı', 'Yetişkin 12+', 2000),
(38, 5, 'İzmir', 9, '01/01/2024', '31/05/2024', 13, 'Oda Kahvaltı', 'Çocuk 4-11', 1800),
(39, 5, 'İzmir', 9, '01/01/2024', '31/05/2024', 13, 'Alkol Hariç Full Credit', 'Yetişkin 12+', 1200),
(40, 5, 'İzmir', 9, '01/01/2024', '31/05/2024', 13, 'Alkol Hariç Full Credit', 'Çocuk 4-11', 1000),
(41, 5, 'İzmir', 10, '01/06/2024', '31/12/2024', 14, 'Oda Kahvaltı', 'Yetişkin 12+', 2500),
(42, 5, 'İzmir', 10, '01/06/2024', '31/12/2024', 14, 'Oda Kahvaltı', 'Çocuk 4-11', 2300),
(43, 5, 'İzmir', 10, '01/06/2024', '31/12/2024', 14, 'Alkol Hariç Full Credit', 'Yetişkin 12+', 2000),
(44, 5, 'İzmir', 10, '01/06/2024', '31/12/2024', 14, 'Alkol Hariç Full Credit', 'Çocuk 4-11', 1800);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `price`
--
ALTER TABLE `price`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `price`
--
ALTER TABLE `price`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
