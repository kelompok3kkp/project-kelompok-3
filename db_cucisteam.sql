-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_cucisteam
CREATE DATABASE IF NOT EXISTS `db_cucisteam` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `db_cucisteam`;

-- Dumping structure for table db_cucisteam.data_karyawan
CREATE TABLE IF NOT EXISTS `data_karyawan` (
  `id_karyawan` varchar(10) NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `jabatan` varchar(50) DEFAULT NULL,
  `shift` varchar(50) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tanggal_masuk` date NOT NULL,
  PRIMARY KEY (`id_karyawan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table db_cucisteam.data_kendaraan
CREATE TABLE IF NOT EXISTS `data_kendaraan` (
  `id_kendaraan` varchar(10) NOT NULL,
  `id_pelanggan` varchar(10) NOT NULL,
  `jenis_kendaraan` enum('Motor','Mobil') NOT NULL,
  `model_kendaraan` varchar(50) DEFAULT NULL,
  `plat_nomor` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table db_cucisteam.data_pelanggan
CREATE TABLE IF NOT EXISTS `data_pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  PRIMARY KEY (`id_pelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table db_cucisteam.isi
CREATE TABLE IF NOT EXISTS `isi` (
  `id_nota` varchar(50) NOT NULL,
  `id_layanan` varchar(50) NOT NULL,
  `jenis_layanan` varchar(50) NOT NULL,
  `harga` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id_nota`) USING BTREE,
  KEY `id_layanan` (`id_layanan`) USING BTREE,
  CONSTRAINT `fk_isi_ke_layanan_cuci` FOREIGN KEY (`id_layanan`) REFERENCES `layanan_cuci` (`id_layanan`),
  CONSTRAINT `fk_isi_ke_nota` FOREIGN KEY (`id_nota`) REFERENCES `nota` (`id_nota`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table db_cucisteam.layanan_cuci
CREATE TABLE IF NOT EXISTS `layanan_cuci` (
  `id_layanan` varchar(10) NOT NULL,
  `jenis_kendaraan` enum('motor','mobil') NOT NULL,
  `model_kendaraan` varchar(50) NOT NULL,
  `jenis_layanan` varchar(50) NOT NULL,
  `keterangan` text DEFAULT NULL,
  `harga` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_layanan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table db_cucisteam.nota
CREATE TABLE IF NOT EXISTS `nota` (
  `id_nota` varchar(10) NOT NULL,
  `tgl_nota` date DEFAULT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `id_karyawan` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_nota`),
  KEY `id_pelanggan` (`id_pelanggan`),
  KEY `id_karyawan` (`id_karyawan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table db_cucisteam.shift
CREATE TABLE IF NOT EXISTS `shift` (
  `id_shift` varchar(10) NOT NULL,
  `nama_shift` varchar(50) NOT NULL,
  `shift` enum('PAGI','MALAM') DEFAULT NULL,
  `jam_mulai` time DEFAULT NULL,
  `jam_selesai` time DEFAULT NULL,
  PRIMARY KEY (`id_shift`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
