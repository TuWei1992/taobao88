USE taobao;

ALTER TABLE `taobao`.`ordert` 
CHANGE COLUMN `full_price` `full_price` DECIMAL(7,2) NULL DEFAULT NULL ;
