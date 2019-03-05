CREATE TABLE `room_messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `room_name` varchar(64) NOT NULL,
  `content` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_messages_on_name` (`room_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `live_streams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(255),
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `user_id` bigint(20),
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `series_streams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `live_stream_id` bigint(20) NOT NULL,
  `series_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_live_stream` (`live_stream_id`),
  KEY `index_series` (`series_id`),
  UNIQUE KEY `index_series_stream` (`live_stream_id`, `series_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `chat_room_bans` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `ban_time` datetime,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY(`id`),
  UNIQUE KEY `index_bans_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;