ALTER TABLE `taobao`.`shipping_addresses` 
ADD INDEX `fk_shipping_address_country_idx` (`country_id` ASC);
ALTER TABLE `taobao`.`shipping_addresses` 
ADD CONSTRAINT `fk_shipping_address_country`
  FOREIGN KEY (`country_id`)
  REFERENCES `taobao`.`country` (`country_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;