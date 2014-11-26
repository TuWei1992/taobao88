USE taobao;

ALTER TABLE `taobao`.`package` 
ADD COLUMN `post_service_id` INT NULL DEFAULT 0 AFTER `weight`,
ADD INDEX `fk_package_post_service_idx` (`post_service_id` ASC);
ALTER TABLE `taobao`.`package` 
ADD CONSTRAINT `fk_package_post_service`
  FOREIGN KEY (`post_service_id`)
  REFERENCES `taobao`.`post_services` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
