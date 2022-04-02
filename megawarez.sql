CREATE DATABASE megawarez

USE megawarez 

--CREANDO TABLA DE CATEGORIA--
CREATE TABLE category(
	id_cat INT IDENTITY NOT NULL PRIMARY KEY,
	cat_name VARCHAR (80) NOT NULL,
	cat_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
	winner VARCHAR (50) NULL,
	create_at DATE NULL
);

--CREANDO INDEX--
CREATE UNIQUE INDEX category_cat_name_Idx ON category (cat_name);


--CREANDO TABLA DE SUBCATEGORIA--
CREATE TABLE subcategory(
	id_scat INT IDENTITY NOT NULL PRIMARY KEY,
	scat_category_id INT NOT NULL,
	scat_name VARCHAR (80) NOT NULL,
	scat_crated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	FOREIGN KEY (scat_category_id) REFERENCES category (id_cat),
);

--CREANDO INDEX--
CREATE INDEX subcategory_scat_category_id_Idx ON subcategory (scat_category_id);
CREATE UNIQUE INDEX subcategory_scat_name_scat_category_id_Idx ON subcategory (scat_name, scat_category_id);


--CREANDO TABLA DE ITEM--
CREATE TABLE item(
	id_item INT IDENTITY NOT NULL PRIMARY KEY,
	item_subcategory_id INT NOT NULL,
	item_name VARCHAR (80) NOT NULL,
	item_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	FOREIGN KEY (item_subcategory_id) REFERENCES subcategory (id_scat)
);

--CREANDO INDEX--
CREATE UNIQUE INDEX item_item_name_Idx ON item (item_name);
CREATE INDEX item_item_subcategory_id_Idx ON item (item_subcategory_id);


--CREANDO TABLA DE USUARIO--
CREATE TABLE [user](
	id_user INT IDENTITY NOT NULL PRIMARY KEY,
	user_username VARCHAR (80) NOT NULL,
	user_password VARCHAR (32) NOT NULL,
	user_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	user_updated_at DATETIME NULL
);

--CREANDO INDEX--
CREATE UNIQUE INDEX usuario_usu_username_Idx ON [user] (user_username);
CREATE INDEX user_user_username_user_password_Idx ON [user] (user_username, user_password);


--CREANDO TABLA DE SESION--
CREATE TABLE session(
	id_ses INT IDENTITY NOT NULL PRIMARY KEY,
	ses_user_id INT NOT NULL,
	ses_token VARCHAR (32) NOT NULL,
	ses_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	FOREIGN KEY (ses_user_id) REFERENCES [user] (id_user)
);

--CREANDO INDEX--
CREATE INDEX session_ses_user_id_Idx ON session (ses_user_id);


--CREANDO TABLA DE DESCARGA--
CREATE TABLE download(
	id_dwn INT IDENTITY NOT NULL PRIMARY KEY,
	dwn_user_id INT NOT NULL,
	dwn_item_id INT NOT NULL,
	dwn_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	create_at DATE NULL
	FOREIGN KEY (dwn_user_id) REFERENCES [user] (id_user),
	FOREIGN KEY (dwn_item_id) REFERENCES item (id_item)
);

--CREANDO INDEX--
CREATE INDEX download_dwn_item_id_Idx ON download (dwn_item_id);
CREATE INDEX download_dwn_user_id_Idx ON download (dwn_user_id);
CREATE INDEX download_dwn_user_id_dwn_item_id_Idx ON download (dwn_user_id, dwn_item_id);