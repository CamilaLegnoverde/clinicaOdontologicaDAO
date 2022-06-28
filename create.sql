DROP TABLE IF EXISTS domicilios;
CREATE TABLE domicilios(ID INT AUTO_INCREMENT PRIMARY KEY,
CALLE varchar(200) NOT NULL,
NUMERO INT NOT NULL,
LOCALIDAD varchar(200) NOT NULL,
PROVINCIA varchar(200) NOT NULL);

DROP TABLE IF EXISTS odontologos;
CREATE TABLE odontologos(ID INT AUTO_INCREMENT PRIMARY KEY,
APELLIDO varchar(100) NOT NULL,
NOMBRE varchar(100) NOT NULL,
MATRICULA varchar(200) NOT NULL);

DROP TABLE IF EXISTS pacientes;
CREATE TABLE pacientes(ID INT AUTO_INCREMENT PRIMARY KEY,
APELLIDO varchar(100) NOT NULL,
NOMBRE varchar(100) NOT NULL,
EMAIL varchar(200) NOT NULL,
DNI INT NOT NULL,
FECHA_INGRESO DATE NOT NULL,
DOMICILIO_ID INT NOT NULL,
ODONTOLOGO_ID INT NOT NULL);


INSERT INTO domicilios (calle, numero, localidad, provincia) VALUES ('Calle A', 474, 'La Plata', 'Buenos Aires');
INSERT INTO domicilios (calle, numero, localidad, provincia) VALUES ('Calle B', 567, 'La Plata', 'Buenos Aires');

INSERT INTO odontologos (apellido, nombre, matricula) VALUES ('Pasteris', 'Lucas', 'ABC123');
INSERT INTO odontologos (apellido, nombre, matricula) VALUES ('Calabró', 'Victor', 'DEF456');

INSERT INTO pacientes (apellido, nombre, email, dni, fecha_ingreso, domicilio_id, odontologo_id) VALUES ('Legnoverde', 'Camila', 'cami@gmail.com', '43805762', '2022-03-02', 1, 1);
INSERT INTO pacientes (apellido, nombre, email, dni, fecha_ingreso, domicilio_id, odontologo_id) VALUES ('Legnoverde', 'Florencia', 'flor@gmail.com', '23805751', '2021-07-08', 2, 2);


