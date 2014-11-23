USE taobao;

CREATE TABLE post_services (
  id INT NOT NULL AUTO_INCREMENT,
  service_name VARCHAR(255) NOT NULL,
  post_region_id INT NOT NULL,
  price DOUBLE NOT NULL DEFAULT 0,
  image_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_post_services_region_idx (post_region_id ASC),
  INDEX fk_post_services_image_idx (image_id ASC),
  CONSTRAINT fk_post_services_region
    FOREIGN KEY (post_region_id)
    REFERENCES post_regions (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
