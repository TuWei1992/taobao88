ALTER TABLE `taobao`.`package` 
ADD COLUMN `shipping_address` INT NULL DEFAULT NULL AFTER `post_service_id`,
ADD INDEX `fk_shipping_address_idx` (`shipping_address` ASC);
ALTER TABLE `taobao`.`package` 
ADD CONSTRAINT `fk_shipping_address`
  FOREIGN KEY (`shipping_address`)
  REFERENCES `taobao`.`shipping_addresses` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
