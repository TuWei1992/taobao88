ALTER TABLE `taobao`.`messages` 
ADD COLUMN `readed` INT NULL DEFAULT 0 AFTER `updated_at`;
