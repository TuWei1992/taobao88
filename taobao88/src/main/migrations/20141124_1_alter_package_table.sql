USE taobao;

ALTER TABLE `taobao`.`package` 
ADD COLUMN `tracknumber` VARCHAR(100) NULL DEFAULT NULL AFTER `message_id`,
ADD COLUMN `weight` DOUBLE NULL DEFAULT 0 AFTER `tracknumber`;


