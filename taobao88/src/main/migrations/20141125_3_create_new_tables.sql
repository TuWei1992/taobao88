USE taobao;

CREATE TABLE `taobao`.`post_services` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `service_name` VARCHAR(100) NOT NULL,
  `country_id` INT NOT NULL,
  `image_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_services_country_idx` (`country_id` ASC),
  CONSTRAINT `fk_post_services_country`
    FOREIGN KEY (`country_id`)
    REFERENCES `taobao`.`country` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
    
CREATE TABLE `taobao`.`post_services_prices` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_service_id` INT NOT NULL,
  `weight` DOUBLE NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_service_idx` (`post_service_id` ASC),
  CONSTRAINT `fk_post_service`
    FOREIGN KEY (`post_service_id`)
    REFERENCES `taobao`.`post_services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
