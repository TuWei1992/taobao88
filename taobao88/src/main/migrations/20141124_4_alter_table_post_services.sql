USE taobao;

ALTER TABLE post_services 
CHANGE COLUMN price minsk_price DOUBLE NOT NULL DEFAULT '0' ,
ADD COLUMN moscow_price DOUBLE NOT NULL DEFAULT 0 AFTER minsk_price;
