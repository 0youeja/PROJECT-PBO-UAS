-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Jul 2024 pada 03.21
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `userdb`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_anggota`
--

CREATE TABLE `data_anggota` (
  `Nim` bigint(50) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Jenis_kelamin` varchar(15) NOT NULL,
  `Telepon` varchar(20) NOT NULL,
  `Kelas` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_anggota`
--

INSERT INTO `data_anggota` (`Nim`, `Nama`, `Jenis_kelamin`, `Telepon`, `Kelas`) VALUES
(1231313, 'weqe', 'Wanita', '2132131', 'ADMINISTRASI BISNIS TERAPAN'),
(2301841, 'asep chipmank', 'Wanita', '083123132', 'AKUTANSI MSU 2'),
(23012312, 'cilok hgeming', 'Wanita', '13213132', 'TEKNOLOGI BIOPROSES'),
(230741302, 'OptimumPrime', 'pria', '08312312421', 'ABT MSU 2'),
(2307412323, 'asep', 'pria', '08124213123', 'TMJ MSU 2'),
(2307413012, 'hafiz', 'pria', '08596065291', 'TI MSU 2'),
(2307413017, 'cuki', 'Wanita', '085960652905', 'ABT MSU 2'),
(2307413018, 'qqwe', 'pria', '08596052312', 'TMD MSU 2'),
(2307413021, 'Siregar', 'Pria', '085960652905', 'TMJ MSU 2'),
(2307413921, 'ambatronOptimum', 'pria', '0812321313', 'ABT MSU 2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_buku`
--

CREATE TABLE `data_buku` (
  `Kode_Buku` int(12) NOT NULL,
  `Judul_Buku` varchar(50) NOT NULL,
  `Pengarang` varchar(30) NOT NULL,
  `Penerbit` varchar(30) NOT NULL,
  `Tahun_Terbit` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_buku`
--

INSERT INTO `data_buku` (`Kode_Buku`, `Judul_Buku`, `Pengarang`, `Penerbit`, `Tahun_Terbit`) VALUES
(10002, 'Kisah 1000 Hamka', 'Hamka KasyironW', 'ERLANGGA', '1993-04-04'),
(10003, 'aseo', 'aseo', 'aaseo', '1993-04-04'),
(10004, 'Menikahi Janda', 'ambatron', 'Skibidi', '2024-07-06'),
(10005, 'Cara istri 10', 'APIS', 'APIS', '2024-07-19'),
(10007, 'Menikahi wanita ui', 'jon asep', 'Pt sejahtera', '2024-07-12'),
(123213, 'qwewqe', 'qwewq', 'eqwe', '2024-07-04');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_peminjaman`
--

CREATE TABLE `data_peminjaman` (
  `Ssn` bigint(50) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Kode_buku` int(12) NOT NULL,
  `Nama_Buku` varchar(30) NOT NULL,
  `Tanggal_Pinjam` date NOT NULL,
  `Tanggal_Kembali` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_peminjaman`
--

INSERT INTO `data_peminjaman` (`Ssn`, `Nama`, `Kode_buku`, `Nama_Buku`, `Tanggal_Pinjam`, `Tanggal_Kembali`) VALUES
(1231, '1231', 1231, '1232', '2024-07-05', '2024-07-05'),
(1232133, 'cuki bray', 21321, 'qwewq', '2024-07-04', '2024-07-04'),
(2312312, 'asep', 2132123, 'mencari wanita', '2024-07-10', '2024-07-12'),
(2307413017, 'ambatron', 2312, 'menikahi janda', '2024-07-04', '2024-07-25');

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`id`, `username`, `password`) VALUES
(0, 'asep@gmail.com', '12345'),
(1, 'siregar@gmail.com', '12345'),
(3, 'siregar', '1');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data_anggota`
--
ALTER TABLE `data_anggota`
  ADD PRIMARY KEY (`Nim`);

--
-- Indeks untuk tabel `data_buku`
--
ALTER TABLE `data_buku`
  ADD PRIMARY KEY (`Kode_Buku`);

--
-- Indeks untuk tabel `data_peminjaman`
--
ALTER TABLE `data_peminjaman`
  ADD PRIMARY KEY (`Ssn`);

--
-- Indeks untuk tabel `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
