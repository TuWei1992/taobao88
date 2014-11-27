USE taobao;

ALTER TABLE `taobao`.`post_services` 
DROP FOREIGN KEY `fk_post_services_country`;
ALTER TABLE `taobao`.`post_services` 
CHANGE COLUMN `country_id` `country_id` INT(11) NULL ;
ALTER TABLE `taobao`.`post_services` 
ADD CONSTRAINT `fk_post_services_country`
  FOREIGN KEY (`country_id`)
  REFERENCES `taobao`.`country` (`country_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
