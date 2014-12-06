USE taobao;

ALTER TABLE `taobao`.`balance_operations` 
CHANGE COLUMN `amount` `amount` INT NULL DEFAULT NULL ;
