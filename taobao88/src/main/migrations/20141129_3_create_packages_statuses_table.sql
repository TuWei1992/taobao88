USE taobao;

CREATE TABLE `taobao`.`packages_statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `package_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_packages_statuses_p_id_idx` (`package_id` ASC),
  INDEX `fk_packages_statuses_s_id_idx` (`status_id` ASC),
  CONSTRAINT `fk_packages_statuses_p_id`
    FOREIGN KEY (`package_id`)
    REFERENCES `taobao`.`package` (`idpackage`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_packages_statuses_s_id`
    FOREIGN KEY (`status_id`)
    REFERENCES `taobao`.`statuses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
