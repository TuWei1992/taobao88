USE taobao;

CREATE TABLE post_regions (
  id INT NOT NULL AUTO_INCREMENT,
  region_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;