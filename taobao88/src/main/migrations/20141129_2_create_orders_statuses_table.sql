USE taobao;

CREATE TABLE `taobao`.`orders_statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_statuses_s_id_idx` (`status_id` ASC),
  INDEX `fk_orders_statuses_o_id_idx` (`order_id` ASC),
  CONSTRAINT `fk_orders_statuses_s_id`
    FOREIGN KEY (`status_id`)
    REFERENCES `taobao`.`statuses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_statuses_o_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `taobao`.`ordert` (`orderT_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;