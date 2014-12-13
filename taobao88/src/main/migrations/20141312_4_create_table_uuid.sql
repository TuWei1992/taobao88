CREATE TABLE `taobao`.`uuid` (
 `uuid` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`uuid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
 
 ALTER TABLE `taobao`.`uuid` 
ADD UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC);

