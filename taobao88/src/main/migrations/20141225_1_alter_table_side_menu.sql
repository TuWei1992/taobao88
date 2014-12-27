ALTER TABLE `taobao`.`side_menu` 
ADD COLUMN `level` INT NULL DEFAULT 0 AFTER `parent_id`;