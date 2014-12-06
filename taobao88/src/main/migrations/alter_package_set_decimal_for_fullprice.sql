USE taobao;

ALTER TABLE `taobao`.`package` 
CHANGE COLUMN `full_price` `full_price` DECIMAL(8,2) NULL DEFAULT NULL ;