use fruitables4month2024;

CREATE TABLE role(
	id bigint NOT NULL PRIMARY KEY auto_increment, #bigint = Long (java)
    name varchar(255) NOT NULL, #varchar(255) = String(java) -> quan tri/nguoi dung
    code varchar(255) NOT NULL, # admin/user
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby varchar(255) NULL,
    modifiedby varchar(255) NULL
);

CREATE TABLE user(
	id bigint not null primary key auto_increment,
    fullname varchar(255) NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    phone varchar(255) NOT NULL,
    address TEXT NULL,
    status int NOT NULL,
    roleid bigint NOT NULL,	
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby varchar(255) NULL,
    modifiedby varchar(255) NULL
);

ALTER TABLE user ADD CONSTRAINT  fk_user_role FOREIGN KEY (roleid) REFERENCES role(id);

CREATE TABLE product(
	id bigint not null primary key auto_increment,
    title varchar(255) NULL,
    thumbnail varchar(255) NULL,
    shortdescription TEXT NULL,
    content TEXT NULL,
    price double NOT NULL,
    origin varchar(255),
    categoryid bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby varchar(255) NULL,
    modifiedby varchar(255) NULL
);

CREATE TABLE bill(
	id bigint not null primary key auto_increment,
    shippingaddress TEXT NOT NULL,
    productid bigint NOT NULL,
    userid bigint NOT NULL,
    status varchar(255) NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby varchar(255) NULL,
    modifiedby varchar(255) NULL
);

ALTER TABLE bill ADD CONSTRAINT  fk_bill_user FOREIGN KEY (userid) REFERENCES user(id);
ALTER TABLE bill ADD CONSTRAINT  fk_bill_product FOREIGN KEY (productid) REFERENCES product(id);

CREATE TABLE billdetails(
	id bigint not null primary key auto_increment,
    productid bigint NOT NULL,
    numberofproducts INT NOT NULL, 
    billid bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby varchar(255) NULL,
    modifiedby varchar(255) NULL
);

ALTER TABLE billdetails ADD CONSTRAINT  fk_billdetails_bill FOREIGN KEY (billid) REFERENCES user(id);
ALTER TABLE billdetails ADD CONSTRAINT  fk_billdetails_product FOREIGN KEY (productid) REFERENCES product(id);

CREATE TABLE category(
	id bigint not null primary key auto_increment,
    name varchar(255) NOT NULL,
    code varchar(255) NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby varchar(255) NULL,
    modifiedby varchar(255) NULL
);

ALTER TABLE product ADD CONSTRAINT  fk_product_category FOREIGN KEY (categoryid) REFERENCES category(id);
	
CREATE TABLE comment(
	id bigint not null primary key auto_increment,
    content TEXT NOT NULL,	
    user_id bigint NOT NULL,
    product_id bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby varchar(255) NULL,
    modifiedby varchar(255) NULL
);

insert into category(code,name) values ('trai-cay', 'Trái cây');
insert into category(code,name) values ('rau', 'Rau');
insert into category(code,name) values ('san-pham-khac', 'Sản phẩm khác');