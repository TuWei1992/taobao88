CREATE TABLE `taobao`.`pages_content` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `page` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`));
