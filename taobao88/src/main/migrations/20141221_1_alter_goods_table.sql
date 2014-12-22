ALTER TABLE `taobao`.`goods` 
DROP FOREIGN KEY `fk_recomendation_id`;
ALTER TABLE `taobao`.`goods` 
ADD CONSTRAINT `fk_recomendation_id`
  FOREIGN KEY (`recomendation_id`)
  REFERENCES `taobao`.`recomendations` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
