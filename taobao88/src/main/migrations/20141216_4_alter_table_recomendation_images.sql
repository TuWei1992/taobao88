ALTER TABLE `taobao`.`recomendations_images` 
 ADD CONSTRAINT `image_id_fk`
  FOREIGN KEY (`image_id`)
  REFERENCES `taobao`.`images` (`image_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION