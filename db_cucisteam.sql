-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 05 Des 2025 pada 15.25
-- Versi server: 12.0.2-MariaDB
-- Versi PHP: 8.1.29

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
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `tanggal_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_karyawan`
--

INSERT INTO `data_karyawan` (`id_karyawan`, `nama_karyawan`, `jenis_kelamin`, `no_telp`, `alamat`, `jabatan`, `password`, `tanggal_masuk`) VALUES
('K001', 'Arie', 'Laki-laki', '0987523', 'Indonesia', 'Admin', 'admin123', '2025-04-28'),
('K002', 'bayu', 'Laki-laki', '01234', 'brazil', 'Washerman', 'karyawan123', '2025-04-30'),
('K003', 'Fardan', 'Laki-laki', '0987765432', 'Indonesiadsdsaa', 'Admin', 'admin123', '2025-06-29');

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
('KD006', 'CS003', 'Mobil', 'Mobil Hatchback', 'd12334BS'),
('KD007', 'CS005', 'Motor', 'Motor Kecil', 'B1231BS');

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
-- Struktur dari tabel `grup_kerja`
--

CREATE TABLE `grup_kerja` (
  `id_grup` varchar(10) NOT NULL,
  `id_karyawan` varchar(10) NOT NULL,
  `nama_karyawan` varchar(100) DEFAULT NULL,
  `grup_jadwal` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `grup_kerja`
--

INSERT INTO `grup_kerja` (`id_grup`, `id_karyawan`, `nama_karyawan`, `grup_jadwal`) VALUES
('G001', 'K001', 'Arie', 'Grup A');

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
('IN0005', 'L001', 'Cuci Motor Biasa', 'Motor Kecil', 15000.00),
('IN0006', 'L006', 'Cuci Mobil Premium', 'Mobil Sedan', 45000.00);

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
('L005', 'mobil', 'Mobil Sedan', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil ...', 35000.00),
('L006', 'mobil', 'Mobil Sedan', 'Cuci Mobil Premium', '1. Semua layanan dari \"Cuci Mobil Biasa\" ...', 45000.00),
('L007', 'mobil', 'Mobil Hatchback', 'Cuci Mobil Biasa', '...', 40000.00),
('L008', 'mobil', 'Mobil Hatchback', 'Cuci Mobil Premium', '...', 50000.00),
('L009', 'mobil', 'Mobil SUV', 'Cuci Mobil Biasa', '...', 55000.00),
('L010', 'mobil', 'Mobil SUV', 'Cuci Mobil Premium', '...', 65000.00),
('L011', 'mobil', 'Mobil MPV', 'Cuci Mobil Biasa', '...', 50000.00),
('L012', 'mobil', 'Mobil MPV', 'Cuci Mobil Premium', '...', 60000.00),
('L013', 'mobil', 'Mobil Pick-Up', 'Cuci Mobil Biasa', '...', 35000.00),
('L014', 'mobil', 'Mobil Pick-Up', 'Cuci Mobil Premium', '...', 40000.00);

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
-- Struktur dari tabel `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` varchar(20) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `id_transaksi` varchar(20) NOT NULL,
  `harga` decimal(15,2) NOT NULL,
  `diskon` int(11) NOT NULL,
  `metode_pembayaran` varchar(50) NOT NULL,
  `total_bayar` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `tanggal_transaksi`, `id_transaksi`, `harga`, `diskon`, `metode_pembayaran`, `total_bayar`) VALUES
('TRX001', '2025-09-30', 'IN0003', 30000.00, 10, 'Tunai', 27000.00),
('TRX002', '2025-09-30', 'IN0001', 25000.00, 10, 'Tunai', 22500.00),
('TRX003', '2025-09-30', 'IN0002', 45000.00, 5, 'Tunai', 42750.00),
('TRX004', '2025-09-30', 'IN0004', 60000.00, 10, 'Tunai', 54000.00),
('TRX005', '2025-11-05', 'IN0005', 15000.00, 10, 'Tunai', 13500.00);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(10) NOT NULL,
  `tgl_nota` date NOT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `id_karyawan` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tgl_nota`, `id_pelanggan`, `id_karyawan`) VALUES
('IN0001', '2025-06-10', 'CS002', 'K002'),
('IN0002', '2025-06-15', 'CS001', 'K002'),
('IN0003', '2025-06-16', 'CS002', 'K002'),
('IN0004', '2025-06-20', 'CS002', 'K003'),
('IN0005', '2025-07-01', 'CS001', 'K001'),
('IN0006', '2025-09-30', 'CS001', 'K001');

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
  ADD PRIMARY KEY (`id_kendaraan`),
  ADD KEY `fk_kendaraan_pelanggan` (`id_pelanggan`);

--
-- Indeks untuk tabel `data_pelanggan`
--
ALTER TABLE `data_pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `grup_kerja`
--
ALTER TABLE `grup_kerja`
  ADD PRIMARY KEY (`id_grup`) USING BTREE,
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indeks untuk tabel `isi`
--
ALTER TABLE `isi`
  ADD KEY `fk_isi_trx` (`id_transaksi`),
  ADD KEY `fk_isi_layanan` (`id_layanan`);

--
-- Indeks untuk tabel `layanan_cuci`
--
ALTER TABLE `layanan_cuci`
  ADD PRIMARY KEY (`id_layanan`);

--
-- Indeks untuk tabel `nota`
--
ALTER TABLE `nota`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `fk_bayar_trx` (`id_transaksi`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_trx_pelanggan` (`id_pelanggan`),
  ADD KEY `fk_trx_karyawan` (`id_karyawan`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `data_kendaraan`
--
ALTER TABLE `data_kendaraan`
  ADD CONSTRAINT `fk_kendaraan_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `data_pelanggan` (`id_pelanggan`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `grup_kerja`
--
ALTER TABLE `grup_kerja`
  ADD CONSTRAINT `grup_kerja_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `data_karyawan` (`id_karyawan`);

--
-- Ketidakleluasaan untuk tabel `isi`
--
ALTER TABLE `isi`
  ADD CONSTRAINT `fk_isi_layanan` FOREIGN KEY (`id_layanan`) REFERENCES `layanan_cuci` (`id_layanan`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_isi_trx` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `fk_bayar_trx` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_trx_karyawan` FOREIGN KEY (`id_karyawan`) REFERENCES `data_karyawan` (`id_karyawan`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_trx_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `data_pelanggan` (`id_pelanggan`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
