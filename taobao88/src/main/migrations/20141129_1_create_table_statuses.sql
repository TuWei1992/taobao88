USE taobao;

CREATE TABLE `taobao`.`statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
