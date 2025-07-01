-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Jul 2025 pada 11.15
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

CREATE TABLE `data_karyawan` (
  `id_karyawan` varchar(10) NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `jabatan` varchar(50) DEFAULT NULL,
  `shift` varchar(50) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tanggal_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_karyawan`
--

INSERT INTO `data_karyawan` (`id_karyawan`, `nama_karyawan`, `jenis_kelamin`, `no_telp`, `alamat`, `jabatan`, `shift`, `password`, `tanggal_masuk`) VALUES
('K001', 'Arie', 'Laki-laki', '0987523', 'Indonesia', 'Admin', 'Pagi', 'admin123', '2025-04-28'),
('K002', 'bayu', 'Laki-laki', '01234', 'brazil', 'Washerman', 'Malam', 'karyawan123', '2025-04-30'),
('K003', 'Fardan', 'Laki-laki', '0987765432', 'Indonesia', 'Admin', 'PAGI', 'admin123', '2025-06-29');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_kendaraan`
--

CREATE TABLE `data_kendaraan` (
  `id_kendaraan` varchar(10) NOT NULL,
  `id_pelanggan` varchar(10) NOT NULL,
  `jenis_kendaraan` enum('Motor','Mobil') NOT NULL,
  `model_kendaraan` varchar(50) DEFAULT NULL,
  `plat_nomor` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_kendaraan`
--

INSERT INTO `data_kendaraan` (`id_kendaraan`, `id_pelanggan`, `jenis_kendaraan`, `model_kendaraan`, `plat_nomor`) VALUES
('KD001', 'CS001', 'Motor', 'Motor Kecil', 'b123fvdsf'),
('KD002', 'CS002', 'Mobil', 'Mobil MPV', 'b1234bd'),
('KD003', 'CS001', 'Mobil', 'Mobil Sedan', 'B 4 YU'),
('KD004', 'CS002', 'Motor', 'Motor Besar', 'N 0 Pal'),
('KD005', 'CS003', 'Motor', 'Motor Besar', 'B 123BDa'),
('KD006', 'CS003', 'Mobil', 'Mobil Hatchback', 'd12334BS');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_pelanggan`
--

CREATE TABLE `data_pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_pelanggan`
--

INSERT INTO `data_pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telp`, `alamat`) VALUES
('CS001', 'Bayu', '122345455', 'Indonesia'),
('CS002', 'nopal', '012445', 'bogor'),
('CS003', 'ucok', '121123', 'asdada'),
('CS004', 'Alip', '12313', 'sdfsf'),
('CS005', 'Fardan', '121323', 'Indds');

-- --------------------------------------------------------

--
-- Struktur dari tabel `isi`
--

CREATE TABLE `isi` (
  `id_transaksi` varchar(50) NOT NULL,
  `id_layanan` varchar(50) NOT NULL,
  `jenis_layanan` varchar(50) NOT NULL,
  `model_kendaraan` varchar(50) DEFAULT NULL,
  `harga` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `isi`
--

INSERT INTO `isi` (`id_transaksi`, `id_layanan`, `jenis_layanan`, `model_kendaraan`, `harga`) VALUES
('IN0001', 'L002', 'Cuci Motor Premium', 'Motor Kecil', 25000.00),
('IN0003', 'L004', 'Cuci Motor Premium', 'Motor Besar', 30000.00),
('IN0002', 'L006', 'Cuci Mobil Premium', 'Mobil Sedan', 45000.00),
('IN0004', 'L012', 'Cuci Mobil Premium', 'Mobil MPV', 60000.00),
('IN0005', 'L001', 'Cuci Motor Biasa', 'Motor Kecil', 15000.00);

-- --------------------------------------------------------

--
-- Struktur dari tabel `layanan_cuci`
--

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
('L003', 'motor', 'Motor Besar', 'Cuci Motor Biasa', '1. Cuci seluruh bagian luar motor\n2. Pembersihan detail: sela bodi, \n3. kolong spakbor, velg\n4. Pembersihan rantai\n5. Semprotan air bertekanan tinggi\n6. Pengeringan dengan lap microfiber', 20000.00),
('L004', 'motor', 'Motor Besar', 'Cuci Motor Premium', '1. Semua layanan dari \"Cuci Motor Biasa\"\n2. Poles ringan bodi motor (plastik & tangki)\n3. Coating pelindung cat\n4. Pelumasan rantai (jika ada)\n5. Cuci dan pengharum helm', 30000.00),
('L005', 'mobil', 'Mobil Sedan', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 35000.00),
('L006', 'mobil', 'Mobil Sedan', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 45000.00),
('L007', 'mobil', 'Mobil Hatchback', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 40000.00),
('L008', 'mobil', 'Mobil Hatchback', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 50000.00),
('L009', 'mobil', 'Mobil SUV', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 55000.00),
('L010', 'mobil', 'Mobil SUV', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 65000.00),
('L011', 'mobil', 'Mobil MPV', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 50000.00),
('L012', 'mobil', 'Mobil MPV', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 60000.00),
('L013', 'mobil', 'Mobil Pick-Up', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 35000.00),
('L014', 'mobil', 'Mobil Pick-Up', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\"\n2. Poles ringan bodi mobil (terutama bagian plastik, krom, dan kap mesin)\n3. Coating pelindung cat sementara (wax atau sealant dasar)\n4. Pelumasan engsel pintu dan karet kaca (door trim)\n5. Semprotan parfum mobil (aroma segar atau sesuai pilihan)\n6. Semir ban dan poles velg ringan', 40000.00);

-- --------------------------------------------------------

--
-- Struktur dari tabel `nota`
--

CREATE TABLE `nota` (
  `id_transaksi` varchar(10) NOT NULL,
  `tgl_nota` date DEFAULT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `id_karyawan` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `nota`
--

INSERT INTO `nota` (`id_transaksi`, `tgl_nota`, `id_pelanggan`, `id_karyawan`) VALUES
('IN0001', '2025-06-10', 'CS002', 'K002'),
('IN0002', '2025-06-15', 'CS001', 'K002'),
('IN0003', '2025-06-16', 'CS002', 'K002'),
('IN0004', '2025-06-20', 'CS002', 'K003'),
('IN0005', '2025-07-01', 'CS001', 'K001');

-- --------------------------------------------------------

--
-- Struktur dari tabel `shift`
--

CREATE TABLE `shift` (
  `id_shift` varchar(10) NOT NULL,
  `shift` enum('Pagi','Malam') DEFAULT NULL,
  `jam_mulai` time DEFAULT NULL,
  `jam_selesai` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `shift`
--

INSERT INTO `shift` (`id_shift`, `shift`, `jam_mulai`, `jam_selesai`) VALUES
('S001', 'Pagi', '09:00:00', '15:00:00'),
('S002', 'Malam', '15:00:00', '21:00:00');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data_karyawan`
--
ALTER TABLE `data_karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indeks untuk tabel `data_kendaraan`
--
ALTER TABLE `data_kendaraan`
  ADD PRIMARY KEY (`id_kendaraan`);

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

--
-- Indeks untuk tabel `nota`
--
ALTER TABLE `nota`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indeks untuk tabel `shift`
--
ALTER TABLE `shift`
  ADD PRIMARY KEY (`id_shift`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
