CREATE TABLE `taobao`.`shipping_addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country_id` INT NOT NULL,
  `region` VARCHAR(255) NULL,
  `city` VARCHAR(255) NOT NULL,
  `post_index` VARCHAR(45) NULL,
  `address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

ALTER TABLE `taobao`.`shipping_addresses` 
CHARACTER SET = utf8 , COLLATE = utf8_unicode_ci;
