USE taobao;

ALTER TABLE `taobao`.`statuses` 
ADD COLUMN `parent_status` INT NULL DEFAULT NULL AFTER `status_name`;