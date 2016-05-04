CREATE TABLE event  ( 
	id     	int(11) AUTO_INCREMENT NOT NULL,
	type   	char(1) NULL,
	year   	varchar(10) NULL,
	date   	varchar(10) NULL,
	message	varchar(500) NULL,
	PRIMARY KEY(id)
)