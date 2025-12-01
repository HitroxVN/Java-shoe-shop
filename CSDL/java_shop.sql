-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 01, 2025 lúc 12:39 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `java_shop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `carts`
--

CREATE TABLE `carts` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `carts`
--

INSERT INTO `carts` (`id`, `user_id`, `product_id`, `quantity`) VALUES
(3, 2, 6, 2),
(25, 3, 4, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(2, 'Giày Công Sở'),
(3, 'Giày Dép Thường Ngày'),
(1, 'Giày Thể Thao'),
(4, 'Phụ Kiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `payment_method` enum('COD','BANKING') DEFAULT 'COD',
  `status` enum('pending','confirmed','shipped','delivered','cancelled') DEFAULT 'pending',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `shipping_address`, `total_amount`, `payment_method`, `status`, `created_at`, `updated_at`) VALUES
(1, 1, '123 Đường Lê Lợi, TP.HCM', 2750000.00, 'COD', 'pending', '2025-11-21 16:50:54', '2025-11-26 10:14:42'),
(2, 10, 'trytr', 150000.00, 'COD', 'confirmed', '2025-11-26 10:19:21', '2025-11-26 15:38:15'),
(3, 1, 'sfd', 14800000.00, 'COD', 'confirmed', '2025-11-26 14:39:31', '2025-11-26 15:38:14'),
(4, 1, 'dfshgfdhrtyj', 4200000.00, 'COD', 'confirmed', '2025-11-26 16:34:15', '2025-12-01 10:16:16'),
(5, 9, 'ada', 150000.00, 'COD', 'pending', '2025-11-26 18:02:54', '2025-11-26 18:02:54'),
(6, 9, 'hjkghu', 3200000.00, 'COD', 'confirmed', '2025-11-26 18:25:29', '2025-12-01 10:34:35'),
(7, 9, 'gfd', 2200000.00, 'COD', 'pending', '2025-11-26 18:38:59', '2025-11-26 18:38:59'),
(8, 9, 'rrrr', 50000.00, 'COD', 'pending', '2025-11-26 18:48:35', '2025-11-26 18:48:35'),
(9, 9, 'sss', 1800000.00, 'COD', 'pending', '2025-11-26 18:50:55', '2025-11-26 18:50:55'),
(10, 9, 'sadfwg', 1600000.00, 'COD', 'pending', '2025-11-27 00:43:39', '2025-11-27 00:43:39'),
(11, 13, 'hn', 50000.00, 'COD', 'pending', '2025-11-27 01:26:32', '2025-11-27 01:26:32'),
(12, 4, 'ssss', 9200000.00, 'COD', 'pending', '2025-12-01 10:29:21', '2025-12-01 10:29:21'),
(13, 8, 'assssa', 50000.00, 'COD', 'pending', '2025-12-01 10:48:29', '2025-12-01 10:48:29'),
(14, 8, 'aaa', 50000.00, 'COD', 'pending', '2025-12-01 11:06:30', '2025-12-01 11:06:30'),
(15, 4, 'sdfg', 2500000.00, 'COD', 'pending', '2025-12-01 11:39:16', '2025-12-01 11:39:16');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_items`
--

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `order_items`
--

INSERT INTO `order_items` (`id`, `order_id`, `product_id`, `quantity`, `price`, `created_at`, `updated_at`) VALUES
(1, 1, 1, 1, 2500000.00, '2025-11-21 16:50:54', '2025-11-21 16:50:54'),
(2, 1, 7, 1, 50000.00, '2025-11-21 16:50:54', '2025-11-21 16:50:54'),
(3, 2, 3, 30, 300.00, '2025-11-26 10:19:57', '2025-11-26 10:19:57'),
(4, 3, 1, 6, 2200000.00, '2025-11-26 14:39:31', '2025-11-26 14:39:31'),
(5, 3, 1, 1, 1600000.00, '2025-11-26 14:39:31', '2025-11-26 14:39:31'),
(6, 4, 1, 12, 350000.00, '2025-11-26 16:34:15', '2025-11-26 16:34:15'),
(8, 5, 6, 30, 400000.00, '2025-11-26 18:21:29', '2025-11-26 18:21:29'),
(10, 7, 2, 1, 2200000.00, '2025-11-26 18:38:59', '2025-11-26 18:38:59'),
(11, 8, 7, 1, 50000.00, '2025-11-26 18:48:35', '2025-11-26 18:48:35'),
(12, 9, 3, 1, 1800000.00, '2025-11-26 18:50:55', '2025-11-26 18:50:55'),
(13, 10, 4, 1, 1600000.00, '2025-11-27 00:43:39', '2025-11-27 00:43:39'),
(14, 11, 8, 1, 50000.00, '2025-11-27 01:26:32', '2025-11-27 01:26:32'),
(15, 12, 4, 1, 1600000.00, '2025-12-01 10:29:21', '2025-12-01 10:29:21'),
(16, 12, 1, 3, 2500000.00, '2025-12-01 10:29:21', '2025-12-01 10:29:21'),
(17, 12, 8, 2, 50000.00, '2025-12-01 10:29:21', '2025-12-01 10:29:21'),
(18, 13, 8, 1, 50000.00, '2025-12-01 10:48:29', '2025-12-01 10:48:29'),
(19, 14, 8, 1, 50000.00, '2025-12-01 11:06:30', '2025-12-01 11:06:30'),
(20, 15, 1, 1, 2500000.00, '2025-12-01 11:39:16', '2025-12-01 11:39:16');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT 0.00,
  `image` varchar(255) DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  `stock` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `category_id`, `name`, `description`, `price`, `image`, `size`, `stock`) VALUES
(1, 1, 'Giày Thể Thao Nike Air', 'Giày thể thao Nike Air, thoáng khí, chống trượt', 2500000.00, 'nike_air.jpg', '42', 50),
(2, 1, 'Giày Thể Thao Adidas Run', 'Giày chạy Adidas siêu nhẹ, êm chân', 2200000.00, 'adidas_run.jpg', '43', 40),
(3, 2, 'Giày Công Sở Da Nam', 'Giày da nam cao cấp, lịch lãm', 1800000.00, 'giay_da_nam.jpg', '42', 30),
(4, 2, 'Giày Công Sở Da Nữ', 'Giày da nữ thời trang, thoải mái', 1600000.00, 'giay_da_nu.jpg', '38', 25),
(5, 3, 'Dép Sandal Nam', 'Dép sandal nam đi chơi, du lịch', 350000.00, 'dep_sandal_nam.jpg', '41', 60),
(6, 3, 'Dép Sandal Nữ', 'Dép sandal nữ thoải mái, thời trang', 300000.00, 'dep_sandal_nu.jpg', '39', 70),
(7, 4, 'Tất Nam', 'Tất nam cotton mềm mại', 50000.00, 'tat_nam.jpg', '41,42,43', 200),
(8, 4, 'Tất Nữ', 'Tất nữ nhiều màu sắc', 50000.00, '1764171324428_e465e8de-97c8-4c94-8ee4.png', '37', 180);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role` enum('customer','admin') DEFAULT 'customer'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `full_name`, `email`, `password`, `phone`, `address`, `role`) VALUES
(1, 'Nguyễn Văn A', 'nguyenvana@example.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '0912345678', '123 Đường Lê Lợi, TP.HCM', 'customer'),
(2, 'Trần Thị B', 'tranthib@example.com', 'password123', '0987654321', '456 Đường Hai Bà Trưng, Hà Nội', 'customer'),
(3, '', 'nhanvien@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '0112233445', '1900 Đường Trần Phú, Đà Nẵng', 'customer'),
(4, 'Admin Quản Trị', 'test@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '0112233445', '789 Đường Trần Phú, Đà Nẵng', 'admin'),
(8, 'mai', 'test1@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '0564774568', 'ad1r', 'customer'),
(9, 'Truong Hoang Van', 'hui86851@jioso.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '0905123456', 'ad15432', 'customer'),
(10, 'Truong Hoang', 'truongsina2005@gmail.com', '571f5206ffcaf5e66a0506deea9e971059b7d852ac3d23261f1d50ee9d45079f', '0333824720', 'ad1', 'admin'),
(11, 'Truong Hoang', 'dzy45077@toaik.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '0905123456', 'ad1 ảhez', 'customer'),
(13, NULL, 'check@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, 'customer');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_user_product` (`user_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `email_2` (`email`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `carts`
--
ALTER TABLE `carts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `carts_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL;

--
-- Các ràng buộc cho bảng `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE SET NULL;

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
