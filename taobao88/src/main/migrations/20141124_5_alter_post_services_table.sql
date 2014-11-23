USE taobao;

ALTER TABLE `taobao`.`post_services` 
DROP FOREIGN KEY `fk_post_services_region`;
ALTER TABLE `taobao`.`post_services` 
CHANGE COLUMN `post_region_id` `post_region_id` INT(11) NULL DEFAULT NULL ;
ALTER TABLE `taobao`.`post_services` 
ADD CONSTRAINT `fk_post_services_region`
  FOREIGN KEY (`post_region_id`)
  REFERENCES `taobao`.`post_regions` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
