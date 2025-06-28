-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Jun 2025 pada 11.36
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
('K001', 'Alip', 'Laki-laki', '0987523', 'Indonesia', 'Admin', 'Pagi', 'admin123', '2025-04-28'),
('K002', 'bayu', 'Laki-laki', '01234', 'brazil', 'Washerman', 'Malam', 'karyawan123', '2025-04-30');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_kasir`
--

CREATE TABLE `data_kasir` (
  `id_kasir` varchar(10) NOT NULL,
  `nama_kasir` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `tgl_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
('KD002', 'CS002', 'Mobil', 'Mobil MPV', 'b1234bd');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_pelanggan`
--

CREATE TABLE `data_pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `jenis_kendaraan` enum('Motor','Mobil') NOT NULL,
  `model_kendaraan` varchar(50) DEFAULT NULL,
  `jenis_layanan` varchar(50) DEFAULT NULL,
  `plat_nomor` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tanggal_cuci` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_pelanggan`
--

INSERT INTO `data_pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telp`, `alamat`, `jenis_kendaraan`, `model_kendaraan`, `jenis_layanan`, `plat_nomor`, `tanggal_cuci`) VALUES
('CS001', 'Bayu', '122345455', 'Indonesia', 'Motor', 'Motor Besar', 'Cuci Motor Biasa', 'A 1234 BCD', '2025-04-28'),
('CS002', 'nopal', '012445', 'bogor', 'Motor', 'Motor Kecil', 'Cuci Motor Premium', 'B 1222 BDE', '2025-04-30'),
('CS003', 'sdad', '121123', 'asdada', 'Motor', 'Motor Besar', 'Cuci Motor Premium', 'B 121233 VDS', '2025-05-20'),
('CS004', 'sdd', '12313', 'sdfsf', 'Mobil', 'Mobil Sedan', 'Cuci Mobil Premium', 'B 23123 DDS', '2025-05-20');

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
('L003', 'motor', 'Motor Kecil', 'Cuci Motor Biasa', '1. Cuci seluruh bagian luar motor\n2. Pembersihan detail: sela bodi, \n3. kolong spakbor, velg\n4. Pembersihan rantai\n5. Semprotan air bertekanan tinggi\n6. Pengeringan dengan lap microfiber', 20000.00),
('L004', 'motor', 'Motor Besar', 'Cuci Motor Premium', '1. Semua layanan dari \"Cuci Motor Biasa\"\n2. Poles ringan bodi motor (plastik & tangki)\n3. Coating pelindung cat\n4. Pelumasan rantai (jika ada)\n5. Cuci dan pengharum helm', 30000.00),
('L005', 'mobil', 'Mobil Sedan', 'Cuci Motor Biasa', '1. Cuci seluruh bagian luar mobil (body, kaca, spion, kap, dan bumper)\n2. Pembersihan detail: sela-sela pintu, handle pintu, wiper\n3. Pembersihan kolong spakbor dan velg\n4. Pembersihan dasar eksterior grill dan emblem\n5. Semprotan air bertekanan tinggi (steam pressure washer)\n6. Vacuum interior: jok, lantai, dan bagasi\n7. Pengeringan dengan lap microfiber', 35000.00),
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
  `id_nota` varchar(10) NOT NULL,
  `tgl_nota` date DEFAULT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `shift`
--

CREATE TABLE `shift` (
  `id_shift` varchar(10) NOT NULL,
  `nama_shift` varchar(50) NOT NULL,
  `shift` enum('PAGI','MALAM') DEFAULT NULL,
  `jam_mulai` time DEFAULT NULL,
  `jam_selesai` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `shift`
--

INSERT INTO `shift` (`id_shift`, `nama_shift`, `shift`, `jam_mulai`, `jam_selesai`) VALUES
('S01', 'Bayu', 'PAGI', '18:32:46', '15:00:00'),
('S02', 'Alif', 'MALAM', '15:00:00', '21:00:00');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_cuci`
--

CREATE TABLE `transaksi_cuci` (
  `id_transaksi` varchar(10) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `id_karyawan` varchar(10) NOT NULL,
  `id_pelanggan` varchar(10) NOT NULL,
  `id_layanan` varchar(10) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `diskon` int(11) DEFAULT 0,
  `metode_pembayaran` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi_cuci`
--

INSERT INTO `transaksi_cuci` (`id_transaksi`, `tanggal_transaksi`, `id_karyawan`, `id_pelanggan`, `id_layanan`, `harga`, `diskon`, `metode_pembayaran`) VALUES
('TRX001', '2025-05-20', 'K001', 'CS004', 'L006', 45000.00, 0, 'Tunai');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data_karyawan`
--
ALTER TABLE `data_karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indeks untuk tabel `data_kasir`
--
ALTER TABLE `data_kasir`
  ADD PRIMARY KEY (`id_kasir`);

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
  ADD PRIMARY KEY (`id_nota`);

--
-- Indeks untuk tabel `shift`
--
ALTER TABLE `shift`
  ADD PRIMARY KEY (`id_shift`);

--
-- Indeks untuk tabel `transaksi_cuci`
--
ALTER TABLE `transaksi_cuci`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_karyawan` (`id_karyawan`),
  ADD KEY `id_layanan` (`id_layanan`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi_cuci`
--
ALTER TABLE `transaksi_cuci`
  ADD CONSTRAINT `transaksi_cuci_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `data_pelanggan` (`id_pelanggan`),
  ADD CONSTRAINT `transaksi_cuci_ibfk_2` FOREIGN KEY (`id_karyawan`) REFERENCES `data_karyawan` (`id_karyawan`),
  ADD CONSTRAINT `transaksi_cuci_ibfk_3` FOREIGN KEY (`id_layanan`) REFERENCES `layanan_cuci` (`id_layanan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
