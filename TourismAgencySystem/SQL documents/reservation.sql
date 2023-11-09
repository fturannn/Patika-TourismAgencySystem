-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Kas 2023, 16:29:55
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
-- Tablo için tablo yapısı `reservation`
--

CREATE TABLE `reservation` (
  `id` int NOT NULL,
  `room_id` int NOT NULL,
  `period` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hostel` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `night` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `person` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `tc` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `total` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `reservation`
--

INSERT INTO `reservation` (`id`, `room_id`, `period`, `hotel`, `hostel`, `night`, `person`, `name`, `tc`, `telephone`, `email`, `total`) VALUES
(1, 1, '03/03/2024 - 05/03/2024', 'Patika Otel', 'Herşey Dahil', '2', '1 Yetişkin 0 Çocuk', 'Furkan Turan', 'xxxxxxxxxxx', '05303218728', 'furkn.turan@hotmail.com', '3000'),
(2, 5, '03/03/2024 - 05/03/2024', 'İstanbul Hilltown Hotel', 'Ultra Herşey Dahil', '2', '1 Yetişkin 0 Çocuk', 'Ali Veli', 'xxxxxxxxxxx', '04853748392', 'aveli@email.com', '10000'),
(3, 8, '03/08/2024 - 05/08/2024', 'Hotel Giresun', 'Yarım Pansiyon', '2', '2 Yetişkin 1 Çocuk', 'Hakan Yılmaz', 'xxxxxxxxxxx', '03843728192', 'hyilmaz@email.com', '4400'),
(4, 14, '23/09/2024 - 28/09/2024', 'İzmir Deluxe Hotel', 'Oda Kahvaltı', '5', '2 Yetişkin 2 Çocuk', 'Gökhan Kalkan', 'xxxxxxxxxxx', '04839214839', 'gkalkan@email.com', '48000'),
(5, 9, '23/04/2024 - 24/04/2024', 'Ankara Grand Hotel', 'Herşey Dahil', '1', '2 Yetişkin 0 Çocuk', 'Zeynep Geçen', 'xxxxxxxxxxx', '03849283942', 'zgecen@email.com', '5000');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
