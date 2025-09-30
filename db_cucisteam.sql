-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               12.0.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.11.0.7065
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
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `tanggal_masuk` date NOT NULL,
  PRIMARY KEY (`id_karyawan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_cucisteam.data_karyawan: ~3 rows (approximately)
INSERT INTO `data_karyawan` (`id_karyawan`, `nama_karyawan`, `jenis_kelamin`, `no_telp`, `alamat`, `jabatan`, `shift`, `password`, `tanggal_masuk`) VALUES
	('K001', 'Arie', 'Laki-laki', '0987523', 'Indonesia', 'Admin', 'Pagi', 'admin123', '2025-04-28'),
	('K002', 'bayu', 'Laki-laki', '01234', 'brazil', 'Washerman', 'Malam', 'karyawan123', '2025-04-30'),
	('K003', 'Fardan', 'Laki-laki', '0987765432', 'Indonesiadsdsaa', 'Admin', 'Pagi', 'admin123', '2025-06-29');

-- Dumping structure for table db_cucisteam.data_kendaraan
CREATE TABLE IF NOT EXISTS `data_kendaraan` (
  `id_kendaraan` varchar(10) NOT NULL,
  `id_pelanggan` varchar(10) NOT NULL,
  `jenis_kendaraan` enum('Motor','Mobil') NOT NULL,
  `model_kendaraan` varchar(50) DEFAULT NULL,
  `plat_nomor` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_kendaraan`),
  KEY `fk_kendaraan_pelanggan` (`id_pelanggan`),
  CONSTRAINT `fk_kendaraan_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `data_pelanggan` (`id_pelanggan`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_cucisteam.data_kendaraan: ~7 rows (approximately)
INSERT INTO `data_kendaraan` (`id_kendaraan`, `id_pelanggan`, `jenis_kendaraan`, `model_kendaraan`, `plat_nomor`) VALUES
	('KD001', 'CS001', 'Motor', 'Motor Kecil', 'b123fvdsf'),
	('KD002', 'CS002', 'Mobil', 'Mobil MPV', 'b1234bd'),
	('KD003', 'CS001', 'Mobil', 'Mobil Sedan', 'B 4 YU'),
	('KD004', 'CS002', 'Motor', 'Motor Besar', 'N 0 Pal'),
	('KD005', 'CS003', 'Motor', 'Motor Besar', 'B 123BDa'),
	('KD006', 'CS003', 'Mobil', 'Mobil Hatchback', 'd12334BS'),
	('KD007', 'CS005', 'Motor', 'Motor Kecil', 'B1231BS');

-- Dumping structure for table db_cucisteam.data_pelanggan
CREATE TABLE IF NOT EXISTS `data_pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  PRIMARY KEY (`id_pelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_cucisteam.data_pelanggan: ~5 rows (approximately)
INSERT INTO `data_pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telp`, `alamat`) VALUES
	('CS001', 'Bayu', '122345455', 'Indonesia'),
	('CS002', 'nopal', '012445', 'bogor'),
	('CS003', 'ucok', '121123', 'asdada'),
	('CS004', 'Alip', '12313', 'sdfsf'),
	('CS005', 'Fardan', '121323', 'Indds');

-- Dumping structure for table db_cucisteam.isi
CREATE TABLE IF NOT EXISTS `isi` (
  `id_transaksi` varchar(50) NOT NULL,
  `id_layanan` varchar(50) NOT NULL,
  `jenis_layanan` varchar(50) NOT NULL,
  `model_kendaraan` varchar(50) DEFAULT NULL,
  `harga` decimal(15,2) NOT NULL,
  KEY `fk_isi_trx` (`id_transaksi`),
  KEY `fk_isi_layanan` (`id_layanan`),
  CONSTRAINT `fk_isi_layanan` FOREIGN KEY (`id_layanan`) REFERENCES `layanan_cuci` (`id_layanan`) ON UPDATE CASCADE,
  CONSTRAINT `fk_isi_trx` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_cucisteam.isi: ~6 rows (approximately)
INSERT INTO `isi` (`id_transaksi`, `id_layanan`, `jenis_layanan`, `model_kendaraan`, `harga`) VALUES
	('IN0001', 'L002', 'Cuci Motor Premium', 'Motor Kecil', 25000.00),
	('IN0003', 'L004', 'Cuci Motor Premium', 'Motor Besar', 30000.00),
	('IN0002', 'L006', 'Cuci Mobil Premium', 'Mobil Sedan', 45000.00),
	('IN0004', 'L012', 'Cuci Mobil Premium', 'Mobil MPV', 60000.00),
	('IN0005', 'L001', 'Cuci Motor Biasa', 'Motor Kecil', 15000.00),
	('IN0006', 'L006', 'Cuci Mobil Premium', 'Mobil Sedan', 45000.00);

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

-- Dumping data for table db_cucisteam.layanan_cuci: ~14 rows (approximately)
INSERT INTO `layanan_cuci` (`id_layanan`, `jenis_kendaraan`, `model_kendaraan`, `jenis_layanan`, `keterangan`, `harga`) VALUES
	('L001', 'motor', 'Motor Kecil', 'Cuci Motor Biasa', '1. Cuci seluruh bagian luar motor\n2. Pembersihan detail: sela bodi, \n3. kolong spakbor, velg\n4. Pembersihan rantai\n5. Semprotan air bertekanan tinggi\n6. Pengeringan dengan lap microfiber', 15000.00),
	('L002', 'motor', 'Motor Kecil', 'Cuci Motor Premium', '1. Semua layanan dari "Cuci Motor Biasa"\n2. Poles ringan bodi motor (plastik & tangki)\n3. Coating pelindung cat\n4. Pelumasan rantai (jika ada)\n5. Cuci dan pengharum helm', 25000.00),
	('L003', 'motor', 'Motor Besar', 'Cuci Motor Biasa', '1. Cuci seluruh bagian luar motor\n2. Pembersihan detail: sela bodi, \n3. kolong spakbor, velg\n4. Pembersihan rantai\n5. Semprotan air bertekanan tinggi\n6. Pengeringan dengan lap microfiber', 20000.00),
	('L004', 'motor', 'Motor Besar', 'Cuci Motor Premium', '1. Semua layanan dari "Cuci Motor Biasa"\n2. Poles ringan bodi motor (plastik & tangki)\n3. Coating pelindung cat\n4. Pelumasan rantai (jika ada)\n5. Cuci dan pengharum helm', 30000.00),
	('L005', 'mobil', 'Mobil Sedan', 'Cuci Mobil Biasa', '1. Cuci seluruh bagian luar mobil ...', 35000.00),
	('L006', 'mobil', 'Mobil Sedan', 'Cuci Mobil Premium', '1. Semua layanan dari "Cuci Mobil Biasa" ...', 45000.00),
	('L007', 'mobil', 'Mobil Hatchback', 'Cuci Mobil Biasa', '...', 40000.00),
	('L008', 'mobil', 'Mobil Hatchback', 'Cuci Mobil Premium', '...', 50000.00),
	('L009', 'mobil', 'Mobil SUV', 'Cuci Mobil Biasa', '...', 55000.00),
	('L010', 'mobil', 'Mobil SUV', 'Cuci Mobil Premium', '...', 65000.00),
	('L011', 'mobil', 'Mobil MPV', 'Cuci Mobil Biasa', '...', 50000.00),
	('L012', 'mobil', 'Mobil MPV', 'Cuci Mobil Premium', '...', 60000.00),
	('L013', 'mobil', 'Mobil Pick-Up', 'Cuci Mobil Biasa', '...', 35000.00),
	('L014', 'mobil', 'Mobil Pick-Up', 'Cuci Mobil Premium', '...', 40000.00);

-- Dumping structure for table db_cucisteam.nota
CREATE TABLE IF NOT EXISTS `nota` (
  `id_transaksi` varchar(10) NOT NULL,
  `tgl_nota` date DEFAULT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `id_karyawan` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_cucisteam.nota: ~5 rows (approximately)
INSERT INTO `nota` (`id_transaksi`, `tgl_nota`, `id_pelanggan`, `id_karyawan`) VALUES
	('IN0001', '2025-06-10', 'CS002', 'K002'),
	('IN0002', '2025-06-15', 'CS001', 'K002'),
	('IN0003', '2025-06-16', 'CS002', 'K002'),
	('IN0004', '2025-06-20', 'CS002', 'K003'),
	('IN0005', '2025-07-01', 'CS001', 'K001');

-- Dumping structure for table db_cucisteam.pembayaran
CREATE TABLE IF NOT EXISTS `pembayaran` (
  `id_pembayaran` varchar(20) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `id_transaksi` varchar(20) NOT NULL,
  `harga` decimal(15,2) NOT NULL,
  `diskon` int(11) NOT NULL,
  `metode_pembayaran` varchar(50) NOT NULL,
  `total_bayar` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id_pembayaran`),
  KEY `fk_bayar_trx` (`id_transaksi`),
  CONSTRAINT `fk_bayar_trx` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_cucisteam.pembayaran: ~4 rows (approximately)
INSERT INTO `pembayaran` (`id_pembayaran`, `tanggal_transaksi`, `id_transaksi`, `harga`, `diskon`, `metode_pembayaran`, `total_bayar`) VALUES
	('TRX001', '2025-09-30', 'IN0003', 30000.00, 10, 'Tunai', 27000.00),
	('TRX002', '2025-09-30', 'IN0001', 25000.00, 10, 'Tunai', 22500.00),
	('TRX003', '2025-09-30', 'IN0002', 45000.00, 5, 'Tunai', 42750.00),
	('TRX004', '2025-09-30', 'IN0004', 60000.00, 10, 'Tunai', 54000.00);

-- Dumping structure for table db_cucisteam.shift
CREATE TABLE IF NOT EXISTS `shift` (
  `id_shift` varchar(10) NOT NULL,
  `shift` enum('Pagi','Malam') DEFAULT NULL,
  `jam_mulai` time DEFAULT NULL,
  `jam_selesai` time DEFAULT NULL,
  PRIMARY KEY (`id_shift`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_cucisteam.shift: ~2 rows (approximately)
INSERT INTO `shift` (`id_shift`, `shift`, `jam_mulai`, `jam_selesai`) VALUES
	('S001', 'Pagi', '09:00:00', '15:00:00'),
	('S002', 'Malam', '15:00:00', '21:00:00');

-- Dumping structure for table db_cucisteam.transaksi
CREATE TABLE IF NOT EXISTS `transaksi` (
  `id_transaksi` varchar(10) NOT NULL,
  `tgl_nota` date NOT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `id_karyawan` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `fk_trx_pelanggan` (`id_pelanggan`),
  KEY `fk_trx_karyawan` (`id_karyawan`),
  CONSTRAINT `fk_trx_karyawan` FOREIGN KEY (`id_karyawan`) REFERENCES `data_karyawan` (`id_karyawan`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_trx_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `data_pelanggan` (`id_pelanggan`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_cucisteam.transaksi: ~6 rows (approximately)
INSERT INTO `transaksi` (`id_transaksi`, `tgl_nota`, `id_pelanggan`, `id_karyawan`) VALUES
	('IN0001', '2025-06-10', 'CS002', 'K002'),
	('IN0002', '2025-06-15', 'CS001', 'K002'),
	('IN0003', '2025-06-16', 'CS002', 'K002'),
	('IN0004', '2025-06-20', 'CS002', 'K003'),
	('IN0005', '2025-07-01', 'CS001', 'K001'),
	('IN0006', '2025-09-30', 'CS001', 'K001');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
