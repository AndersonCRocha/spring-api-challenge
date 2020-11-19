CREATE SEQUENCE sq_roles;
CREATE TABLE roles (
	id BIGINT PRIMARY KEY, 
	name VARCHAR(255) NOT NULL
);

CREATE SEQUENCE sq_users;
CREATE TABLE users (
	id BIGINT PRIMARY KEY, 
	username VARCHAR(255) NOT NULL, 
	password VARCHAR(255) NOT NULL, 
	email VARCHAR(255) NOT NULL
);

CREATE TABLE users_roles(
	user_id BIGINT NOT NULL, 
	role_id BIGINT NOT NULL, 
	PRIMARY KEY (user_id, role_id),
	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE SEQUENCE sq_brands;
CREATE TABLE brands (
	id BIGINT PRIMARY KEY, 
	name VARCHAR(255) NOT NULL
);

CREATE SEQUENCE sq_patrimonies;
CREATE TABLE patrimonies (
	id BIGINT PRIMARY KEY, 
	name VARCHAR(255) NOT NULL, 
	description VARCHAR(255), 
	brand_id BIGINT NOT NULL,
	register_number BIGINT NOT NULL,
	CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brands(id)
);

CREATE UNIQUE INDEX unique_brands_name ON brands(name);
CREATE UNIQUE INDEX unique_users_email ON users(email);