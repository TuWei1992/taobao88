USE taobao;

ALTER TABLE `taobao`.`package` 
ADD COLUMN `purchased` INT NULL DEFAULT 0 AFTER `post_service_id`;
