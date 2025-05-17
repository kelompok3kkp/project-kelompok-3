-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Bulan Mei 2025 pada 11.21
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_cucisteam`
--
CREATE DATABASE IF NOT EXISTS `db_cucisteam` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_cucisteam`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_karyawan`
--

DROP TABLE IF EXISTS `data_karyawan`;
CREATE TABLE `data_karyawan` (
  `id_karyawan` varchar(10) NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `jabatan` varchar(50) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tanggal_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_karyawan`
--

INSERT INTO `data_karyawan` (`id_karyawan`, `nama_karyawan`, `jenis_kelamin`, `no_telp`, `alamat`, `jabatan`, `password`, `tanggal_masuk`) VALUES
('K001', 'Alip', 'Laki-laki', '0987523', 'Indonesia', 'Admin', 'admin123', '2025-04-28'),
('K002', 'bayu', 'Laki-laki', '01234', 'brazil', 'Karyawan', 'karyawan123', '2025-04-30');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_pelanggan`
--

DROP TABLE IF EXISTS `data_pelanggan`;
CREATE TABLE `data_pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `jenis_kendaraan` enum('Motor','Mobil') NOT NULL,
  `model_kendaraan` varchar(50) DEFAULT NULL,
  `plat_nomor` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tanggal_cuci` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_pelanggan`
--

INSERT INTO `data_pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telp`, `alamat`, `jenis_kendaraan`, `model_kendaraan`, `plat_nomor`, `tanggal_cuci`) VALUES
('CS001', 'Bayu', '122345455', 'Indonesia', 'Motor', 'Matic', 'A 1234 BCD', '2025-04-28'),
('CS002', 'nopal', '012445', 'bogor', 'Motor', 'Moge', 'B 1222 BDE', '2025-04-30');

-- --------------------------------------------------------

--
-- Struktur dari tabel `layanan_cuci`
--

DROP TABLE IF EXISTS `layanan_cuci`;
CREATE TABLE `layanan_cuci` (
  `id_layanan` varchar(10) NOT NULL,
  `jenis_kendaraan` enum('motor','mobil') NOT NULL,
  `model_kendaraan` varchar(50) NOT NULL,
  `jenis_layanan` varchar(50) NOT NULL,
  `keterangan` text DEFAULT NULL,
  `harga` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `layanan_cuci`
--

INSERT INTO `layanan_cuci` (`id_layanan`, `jenis_kendaraan`, `model_kendaraan`, `jenis_layanan`, `keterangan`, `harga`) VALUES
('L001', 'motor', 'Motor Kecil', 'Cuci Motor Biasa', '1. Cuci seluruh bagian luar motor\n2. Pembersihan detail: sela bodi, \n3. kolong spakbor, velg\n4. Pembersihan rantai\n5. Semprotan air bertekanan tinggi\n6. Pengeringan dengan lap microfiber', 15000.00),
('L002', 'motor', 'Motor Kecil', 'Cuci Motor Premium', '1. Semua layanan dari \"Cuci Motor Biasa\"\n2. Poles ringan bodi motor (plastik & tangki)\n3. Coating pelindung cat\n4. Pelumasan rantai (jika ada)\n5. Cuci dan pengharum helm', 25000.00),
('L003', 'motor', 'Motor Kecil', 'Cuci Motor Biasa', '1. Cuci seluruh bagian luar motor\n2. Pembersihan detail: sela bodi, \n3. kolong spakbor, velg\n4. Pembersihan rantai\n5. Semprotan air bertekanan tinggi\n6. Pengeringan dengan lap microfiber', 20000.00),
('L004', 'motor', 'Motor Besar', 'Cuci Motor Premium', '1. Semua layanan dari \"Cuci Motor Biasa\"\n2. Poles ringan bodi motor (plastik & tangki)\n3. Coating pelindung cat\n4. Pelumasan rantai (jika ada)\n5. Cuci dan pengharum helm', 30000.00),
('L005', 'mobil', 'Sedan', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 35000.00),
('L006', 'mobil', 'Sedan', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 45000.00),
('L007', 'mobil', 'Hatchback', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 40000.00),
('L008', 'mobil', 'Hatchback', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 50000.00),
('L009', 'mobil', 'SUV', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 55000.00),
('L010', 'mobil', 'SUV', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 65000.00),
('L011', 'mobil', 'MPV', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 50000.00),
('L012', 'mobil', 'MPV', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 60000.00),
('L013', 'mobil', 'Pick-Up', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 35000.00),
('L014', 'mobil', 'Pick-Up', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 40000.00);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data_karyawan`
--
ALTER TABLE `data_karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indeks untuk tabel `data_pelanggan`
--
ALTER TABLE `data_pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `layanan_cuci`
--
ALTER TABLE `layanan_cuci`
  ADD PRIMARY KEY (`id_layanan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
