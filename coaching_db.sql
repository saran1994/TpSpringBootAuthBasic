-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 01 juil. 2024 à 17:44
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `coaching_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `base_de_connaissances`
--

CREATE TABLE `base_de_connaissances` (
  `id` bigint(20) NOT NULL,
  `contenu` varchar(1000) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `base_de_connaissances`
--

INSERT INTO `base_de_connaissances` (`id`, `contenu`, `title`) VALUES
(1, 'lien tuto', 'article1');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `email_log`
--

CREATE TABLE `email_log` (
  `id` bigint(20) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `recipient_email` varchar(255) DEFAULT NULL,
  `sent_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL,
  `date_envoi` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `ticket_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `our_user`
--

CREATE TABLE `our_user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `our_user`
--

INSERT INTO `our_user` (`id`, `email`, `password`, `roles`, `username`) VALUES
(1, 'user1@example.com', '$2a$10$HjVf3ieXoAdi2pv1wqcWKe8Qxph6HktF4hv9NU./x.DlHI4bocIqq', NULL, 'user1'),
(2, 'admin@gmail.com', '$2a$10$AAjjyci1zfpZVaD7.184GOtOQ2PEASvNJ0zbJgUJIK6iTa9jtXn0q', 'ADMIN', 'admin'),
(4, 'mah@gmail.com', '$2a$10$1Vhj0q4.j9A.D.dKAz5lAuQmEMnC8AKtVoGYosSk41uPgio6z/k/W', 'APPRENANT', 'mah'),
(5, 'saran@gmail.com', '$2a$10$26r.WZYOmfTe4iVPLgTBouOueT/85ymRROgiOTodwIoj0CpGyvVt6', 'APPRENANT', 'saran'),
(6, 'soumbounou.saran@gmail.com', '$2a$10$4bVdGopsQvD8terhBLaOheyhnohf0FaTPk/JAFcojHnTHsXArad62', 'FORMATEUR', 'mme');

-- --------------------------------------------------------

--
-- Structure de la table `priority`
--

CREATE TABLE `priority` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tickets`
--

CREATE TABLE `tickets` (
  `id` bigint(20) NOT NULL,
  `category` enum('AUTRE','PRATIQUE','TECHNIQUE','THEORIQUE') DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `priority` enum('BASSE','HAUTE','MOYENNE') DEFAULT NULL,
  `status` enum('En_attente','Ouvert','Terminer') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `tickets`
--

INSERT INTO `tickets` (`id`, `category`, `created_at`, `description`, `priority`, `status`, `title`, `user_id`) VALUES
(17, 'PRATIQUE', '2024-06-24 15:18:01.000000', 'Je ne peux pas me connecter à mon compte.', 'MOYENNE', 'En_attente', ' notification', 6),
(18, 'PRATIQUE', '2024-06-24 15:46:16.000000', 'Je ne peux pas me connecter à mon compte.', 'MOYENNE', 'En_attente', ' notification', 6),
(19, 'PRATIQUE', '2024-06-24 15:51:56.000000', 'Je ne peux pas me connecter à mon compte.', 'MOYENNE', 'En_attente', ' notification', 6),
(20, 'PRATIQUE', '2024-06-24 15:55:07.000000', 'Je ne peux pas me connecter à mon compte.', 'MOYENNE', 'En_attente', ' notification', 6),
(21, 'PRATIQUE', NULL, 'Je ne peux pas me connecter à mon compte.', 'MOYENNE', 'Terminer', ' voici', 6),
(22, 'PRATIQUE', '2024-06-26 16:35:21.000000', 'Je ne peux pas me connecter à mon compte.', 'MOYENNE', 'En_attente', ' mytickett', 5),
(23, 'TECHNIQUE', '2024-06-26 16:49:49.000000', 'ceci est un test.', 'MOYENNE', 'En_attente', ' mytickett', 5);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `base_de_connaissances`
--
ALTER TABLE `base_de_connaissances`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `email_log`
--
ALTER TABLE `email_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe7si5nsic35ebt89hw78c9x16` (`user_id`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1urdwwsh2ti15ta6f6p5dbdcp` (`user_id`),
  ADD KEY `FK34i79ruk7qv6w00yb39t1naej` (`ticket_id`);

--
-- Index pour la table `our_user`
--
ALTER TABLE `our_user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `priority`
--
ALTER TABLE `priority`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg4gepaef1bobyvjxs4x7hqi3c` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `base_de_connaissances`
--
ALTER TABLE `base_de_connaissances`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `email_log`
--
ALTER TABLE `email_log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `our_user`
--
ALTER TABLE `our_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `priority`
--
ALTER TABLE `priority`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `email_log`
--
ALTER TABLE `email_log`
  ADD CONSTRAINT `FKe7si5nsic35ebt89hw78c9x16` FOREIGN KEY (`user_id`) REFERENCES `our_user` (`id`);

--
-- Contraintes pour la table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK34i79ruk7qv6w00yb39t1naej` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`),
  ADD CONSTRAINT `FKsww0mgb8xyfbgyu80bjr2ysws` FOREIGN KEY (`user_id`) REFERENCES `our_user` (`id`);

--
-- Contraintes pour la table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `FKg4gepaef1bobyvjxs4x7hqi3c` FOREIGN KEY (`user_id`) REFERENCES `our_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
