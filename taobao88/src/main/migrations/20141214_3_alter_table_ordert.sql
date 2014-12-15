ALTER TABLE `taobao`.`ordert` 
ADD COLUMN `changed` INT NULL DEFAULT NULL AFTER `date`;

ALTER TABLE `taobao`.`ordert` 
ADD COLUMN `purchased_amount` INT NULL DEFAULT NULL AFTER `changed`;
