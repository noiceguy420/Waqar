create table `client_session`(
    `id` int auto_increment NOT NULL PRIMARY KEY comment 'table id',
    `token_hash` binary(32) NOT NULL UNIQUE comment 'refresh token hash',
    `client_id` int NOT NULL comment 'client that logged in',
    `created_at` datetime NOT NULL DEFAULT NOW() comment 'date of creation, date of expiry computed from this',
    `active` boolean NULL DEFAULT TRUE comment 'for the upcomming constraint, NOTE: set inactive to null not false, used also to prevent accidental expiry extensions',
    CONSTRAINT `client_session_frk` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `only_one_active_client` UNIQUE KEY (`client_id`, `active`) comment 'ony one active client session allowed',
    CONSTRAINT `active_true_or_null` CHECK (`active` IS NULL OR `active` = TRUE)
);