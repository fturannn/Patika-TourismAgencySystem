# Introduction
This document provides the database schema and sample data for seven tables:
"hotel", "period", "room", "hostel", "feature", "price", "reservation".

It also includes a "user" table schema and sample data for user authentication.
The provided SQL scripts allow for the database tables and insertion of sample data.

## Hotel Table

### Hotel Table Creation 

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

### Hotel Table Insertion

INSERT INTO `hotel` (`id`, `name`, `city`, `region`, `address`, `email`, `telephone`, `star`, `feature`, `hostel`) VALUES
(1, 'Patika Otel', 'İstanbul', 'Ataşehir', 'Ataşehir / İstanbul', 'patika@patika.com', '05463827302', '5', 'Ücretsiz Otopark Yüzme Havuzu SPA Ücretsiz Wifi ', 'Herşey Dahil Tam Pansiyon '),
(2, 'İstanbul Hilltown Hotel', 'İstanbul', 'Maltepe', 'Maltepe / İstanbul', 'hilltown@hotel.com', '04637283298', '5', 'Yüzme Havuzu Fitness Center SPA Ücretsiz Wifi ', 'Ultra Herşey Dahil '),
(3, 'Hotel Giresun', 'Giresun', 'Merkez', 'Merkez / Giresun', 'hotelgiresun@email.com', '04548392837', '3', '7/24 Oda Servisi Ücretsiz Wifi ', 'Tam Pansiyon Yarım Pansiyon '),
(4, 'Ankara Grand Hotel', 'Ankara', 'Kızılay', 'Kızılay / Ankara', 'ankaragrand@hotel.com', '05473948238', '4', 'Fitness Center Ücretsiz Wifi ', 'Herşey Dahil '),
(5, 'İzmir Deluxe Hotel', 'İzmir', 'Karşıyaka', 'Karşıyaka / İzmir', 'hoteldeluxe@email.com', '05362738293', '4', 'Yüzme Havuzu Hotel Concierge SPA ', 'Oda Kahvaltı Alkol Hariç Full Credit ');

## Period Table

### Period Table Creation

CREATE TABLE `period` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`period_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`period_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`period` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Period Table Insertion

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

## Room Table

### Room Table Creation

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

### Room Table Insertion
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

## Hostel Table

### Hostel Table Creation

CREATE TABLE `hostel` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`hostel` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Hostel Table Insertion

INSERT INTO `hostel` (`id`, `hotel_id`, `hostel`) VALUES
(1, 1, 'Herşey Dahil'),
(2, 1, 'Tam Pansiyon'),
(3, 2, 'Ultra Herşey Dahil'),
(4, 3, 'Tam Pansiyon'),
(5, 3, 'Yarım Pansiyon'),
(6, 4, 'Herşey Dahil'),
(7, 5, 'Oda Kahvaltı'),
(8, 5, 'Alkol Hariç Full Credit');

## Feature Table

### Feature Table Creation

CREATE TABLE `feature` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`feature` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Feature Table Insertion

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

## Price Table

### Price Table Creation

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

### Price Table Insertion

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

## Reservation Table

### Reservation Table Creation

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

### Reservation Table Insertion

INSERT INTO `reservation` (`id`, `room_id`, `period`, `hotel`, `hostel`, `night`, `person`, `name`, `tc`, `telephone`, `email`, `total`) VALUES
(1, 1, '03/03/2024 - 05/03/2024', 'Patika Otel', 'Herşey Dahil', '2', '1 Yetişkin 0 Çocuk', 'Furkan Turan', 'xxxxxxxxxxx', '05303218728', 'furkn.turan@hotmail.com', '3000'),
(2, 5, '03/03/2024 - 05/03/2024', 'İstanbul Hilltown Hotel', 'Ultra Herşey Dahil', '2', '1 Yetişkin 0 Çocuk', 'Ali Veli', 'xxxxxxxxxxx', '04853748392', 'aveli@email.com', '10000'),
(3, 8, '03/08/2024 - 05/08/2024', 'Hotel Giresun', 'Yarım Pansiyon', '2', '2 Yetişkin 1 Çocuk', 'Hakan Yılmaz', 'xxxxxxxxxxx', '03843728192', 'hyilmaz@email.com', '4400'),
(4, 14, '23/09/2024 - 28/09/2024', 'İzmir Deluxe Hotel', 'Oda Kahvaltı', '5', '2 Yetişkin 2 Çocuk', 'Gökhan Kalkan', 'xxxxxxxxxxx', '04839214839', 'gkalkan@email.com', '48000'),
(5, 9, '23/04/2024 - 24/04/2024', 'Ankara Grand Hotel', 'Herşey Dahil', '1', '2 Yetişkin 0 Çocuk', 'Zeynep Geçen', 'xxxxxxxxxxx', '03849283942', 'zgecen@email.com', '5000');

## User Table

### User Table Creation

CREATE TABLE `user` (
`id` int NOT NULL,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`type` enum('Admin','Agency') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### User Table Insertion

INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES
(1, 'Furkan Turan', 'fturan', '1234', 'Admin'),
(2, 'Patika Turizm', 'pturizm', '1234', 'Agency');