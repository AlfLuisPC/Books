Requerimientos
1. Java 8
2. Maven 
3. Mysql

Base de datos

create database Books;

use Books;

create table usuario (id int primary key, nombre_usuario varchar(255), clave_usuario varchar(255));

create table sesion_usuario (id int primary key, id_usuario int, fecha_creacion date, hora_creacion time, fecha_hora_expiracion date, activo boolean, token varchar(255),
FOREIGN KEY (id_usuario) REFERENCES usuario(id));

create table list_book (id int primary key, nombre varchar(255), fecha_creacion date, id_usuario int,
FOREIGN KEY (id_usuario) REFERENCES usuario(id));

create table books (id int primary key, nombre varchar(255),autor varchar(255), titulo varchar(255), editorial varchar(255), google_book_id varchar(255), id_list_book int,
FOREIGN KEY (id_list_book) REFERENCES list_book(id));


