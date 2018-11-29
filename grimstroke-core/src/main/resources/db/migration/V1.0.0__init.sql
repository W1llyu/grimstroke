CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `cover_images` varchar(255) NOT NULL DEFAULT '[]',
  `type` varchar(255) NOT NULL,
  `game_id` int(10),
  `tag_id` bigint(20),
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `associate_id` bigint(20),
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `deleted_at` datetime,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_tag_id` (`tag_id`),
  KEY `index_game_id` (`game_id`),
  KEY `index_associate_id` (`associate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `videos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `game_id` int(10),
  `tag_id` bigint(20),
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `deleted_at` datetime,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_tag_id` (`tag_id`),
  KEY `index_game_id` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `info_collections` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `cover_images` varchar(255) NOT NULL DEFAULT '[]',
  `game_id` int(10),
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `deleted_at` datetime,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_game_id` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `info_collections_informations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `info_collection_id` bigint(20) NOT NULL,
  `information_type` varchar(255) NOT NULL,
  `information_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_information_collection_id` (`info_collection_id`),
  KEY `index_information` (`information_type`, `information_id`),
  UNIQUE KEY `index_info_collection_id_and_information` (`info_collection_id`, `information_type`, `information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `root_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `root_id` bigint(20) NOT NULL,
  `target_type` varchar(255) NOT NULL,
  `target_id` bigint(20) NOT NULL,
  `content` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `deleted_at` datetime,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_comments_on_user_id` (`user_id`),
  KEY `index_comments_on_root_type_and_root_id` (`root_type`,`root_id`),
  KEY `index_comments_on_target_type_and_target_id` (`target_type`,`target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `likes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `target_type` varchar(255) NOT NULL,
  `target_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_target_type_and_target_id` (`target_type`, `target_id`),
  UNIQUE KEY `index_user_id_and_target_type_and_target_id` (`user_id`, `target_type`, `target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users_informations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `information_type` varchar(255) NOT NULL,
  `information_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_information` (`information_type`, `information_id`),
  UNIQUE KEY `index_user_id_and_information` (`user_id`, `information_type`, `information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `parent_tag_id` bigint(20),
  `description` varchar(255),
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `deleted_at` datetime,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_tag_id` (`parent_tag_id`),
  UNIQUE KEY `index_name_on_parent_tag_id` (`name`, `parent_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;