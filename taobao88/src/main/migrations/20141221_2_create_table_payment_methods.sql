CREATE TABLE `taobao`.`payment_methods` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `method_name` VARCHAR(255) NOT NULL,
  `method_description` TEXT NOT NULL,
  PRIMARY KEY (`id`));
