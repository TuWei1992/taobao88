USE taobao;

ALTER TABLE `taobao`.`balance_operations` 
CHANGE COLUMN `amount` `amount` DECIMAL(8,2) NOT NULL ;
