/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.5-10.4.32-MariaDB : Database - cursos_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cursos_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `cursos_db`;

/*Table structure for table `carreras` */

DROP TABLE IF EXISTS `carreras`;

CREATE TABLE `carreras` (
  `id_carrera` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_carrera`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `carreras` */

insert  into `carreras`(`id_carrera`,`nombre`) values (6,'Ingeniería Civil'),(1,'Ingeniería en Agrobiotecnología'),(3,'Ingeniería en Desarrollo y Gestión de Software'),(4,'Ingeniería en Mecatrónica'),(5,'Ingeniería en Metal Mecánica'),(2,'Ingeniería en Procesos Alimentarios'),(9,'Licenciatura en Gastronomía'),(7,'Licenciatura en Gestión de Negocios y Proyectos'),(8,'Licenciatura en Innovación de Negocios y Mercadotecnia'),(10,'TSU en Tecnologías de la Información (Área Software Multiplataforma)');

/*Table structure for table `cursos` */

DROP TABLE IF EXISTS `cursos`;

CREATE TABLE `cursos` (
  `id_curso` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `espacio` varchar(100) NOT NULL,
  `salon` varchar(20) NOT NULL,
  `total_horas` int(11) NOT NULL,
  `dias_atencion` varchar(100) NOT NULL,
  `horario` varchar(50) NOT NULL,
  `cupo_maximo` int(11) NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `id_carrera` int(11) NOT NULL,
  PRIMARY KEY (`id_curso`),
  UNIQUE KEY `codigo` (`codigo`),
  KEY `id_carrera` (`id_carrera`),
  CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`id_carrera`) REFERENCES `carreras` (`id_carrera`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `cursos` */

insert  into `cursos`(`id_curso`,`codigo`,`nombre`,`descripcion`,`espacio`,`salon`,`total_horas`,`dias_atencion`,`horario`,`cupo_maximo`,`imagen`,`id_carrera`) values (1,'AGR101','Biotecnología Vegetal','Curso orientado al estudio y aplicación de técnicas biotecnológicas en especies vegetales.','Edificio A','A-101',60,'Lunes, Miércoles','08:00 - 10:00',30,'upload/cursos/biotecnologia_vegetal.jpg',1),(2,'AGR102','Cultivo de Tejidos Vegetales','Curso práctico sobre técnicas de propagación y cultivo de tejidos vegetales.','Edificio A','A-102',50,'Martes, Jueves','10:00 - 12:00',25,'upload/cursos/cultivo_tejidos.jpg',1),(3,'ALI101','Control de Calidad Alimentaria','Curso enfocado en métodos de evaluación y control de calidad de productos alimenticios.','Edificio B','B-101',60,'Lunes, Miércoles','10:00 - 12:00',30,'upload/cursos/control_calidad.jpg',2),(4,'ALI102','Procesamiento de Alimentos','Curso sobre técnicas y procesos utilizados para la transformación y conservación de alimentos.','Edificio B','B-102',70,'Martes, Jueves','08:00 - 10:00',30,'upload/cursos/procesamiento_alimentos.jpg',2),(5,'SW101','Desarrollo de Aplicaciones Móviles','Curso enfocado en el diseño y desarrollo de aplicaciones para dispositivos móviles.','Edificio C','C-101',80,'Lunes, Miércoles, Viernes','10:00 - 12:00',35,'upload/cursos/desarrollo_movil.jpg',3),(6,'SW102','Bases de Datos','Curso sobre diseño, administración y consulta de bases de datos relacionales.','Edificio C','C-102',70,'Martes, Jueves','12:00 - 14:00',35,'upload/cursos/bases_datos.jpg',3),(7,'MEC101','Automatización Industrial','Curso enfocado en sistemas automatizados utilizados dentro de procesos industriales.','Edificio D','D-101',80,'Lunes, Miércoles','08:00 - 10:00',30,'upload/cursos/automatizacion.jpg',4),(8,'MEC102','Sistemas de Control','Curso introductorio al análisis y funcionamiento de sistemas de control.','Edificio D','D-102',70,'Martes, Jueves','10:00 - 12:00',30,'upload/cursos/sistemas_control.jpg',4),(9,'MET101','Procesos de Manufactura','Curso sobre procesos y técnicas utilizados en la fabricación de componentes industriales.','Edificio E','E-101',70,'Lunes, Miércoles','12:00 - 14:00',25,'upload/cursos/manufactura.jpg',5),(10,'MET102','Diseño de Elementos Mecánicos','Curso enfocado en fundamentos para el diseño y análisis de elementos mecánicos.','Edificio E','E-102',80,'Martes, Jueves','08:00 - 10:00',25,'upload/cursos/elementos_mecanicos.jpg',5),(11,'CIV101','Diseño Asistido por Computadora','Curso para la elaboración e interpretación de diseños técnicos mediante herramientas digitales.','Edificio F','F-101',60,'Lunes, Miércoles','10:00 - 12:00',30,'upload/cursos/diseno_asistido.jpg',6),(12,'CIV102','Topografía Aplicada','Curso sobre técnicas básicas de medición y representación de terrenos.','Edificio F','F-102',60,'Martes, Jueves','12:00 - 14:00',30,'upload/cursos/topografia.jpg',6),(13,'NEG101','Gestión de Proyectos','Curso enfocado en la planeación, organización y seguimiento de proyectos.','Edificio G','G-101',50,'Lunes, Miércoles','08:00 - 10:00',35,'upload/cursos/gestion_proyectos.jpg',7),(14,'NEG102','Planeación Estratégica','Curso sobre herramientas y estrategias para la planeación dentro de las organizaciones.','Edificio G','G-102',50,'Martes, Jueves','10:00 - 12:00',35,'upload/cursos/planeacion_estrategica.jpg',7),(15,'MKT101','Marketing Digital','Curso sobre estrategias de promoción y comunicación utilizando medios digitales.','Edificio H','H-101',50,'Lunes, Miércoles','12:00 - 14:00',35,'upload/cursos/marketing_digital.jpg',8),(16,'MKT102','Investigación de Mercados','Curso orientado a la obtención y análisis de información para la toma de decisiones comerciales.','Edificio H','H-102',60,'Martes, Jueves','08:00 - 10:00',35,'upload/cursos/investigacion_mercados.jpg',8),(17,'GAS101','Técnicas de Cocina','Curso práctico sobre técnicas fundamentales utilizadas en la preparación de alimentos.','Edificio I','I-101',80,'Lunes, Miércoles','10:00 - 12:00',25,'upload/cursos/tecnicas_cocina.jpg',9),(18,'GAS102','Panadería y Repostería','Curso práctico sobre elaboración de productos básicos de panadería y repostería.','Edificio I','I-102',70,'Martes, Jueves','12:00 - 14:00',25,'upload/cursos/panaderia_reposteria.jpg',9),(19,'TI101','Programación Orientada a Objetos','Curso enfocado en los fundamentos de programación orientada a objetos y desarrollo de software.','Edificio J','J-101',80,'Lunes, Miércoles, Viernes','08:00 - 10:00',35,'upload/cursos/programacion_objetos.jpg',10),(20,'TI102','Desarrollo Web','Curso sobre fundamentos y herramientas para la creación de aplicaciones y sitios web.','Edificio J','J-102',80,'Martes, Jueves','10:00 - 12:00',35,'upload/cursos/desarrollo_web.jpg',10);

/*Table structure for table `inscripciones` */

DROP TABLE IF EXISTS `inscripciones`;

CREATE TABLE `inscripciones` (
  `id_inscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_inscripcion` date NOT NULL,
  `id_curso` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_inscripcion`),
  UNIQUE KEY `id_curso` (`id_curso`,`id_usuario`),
  KEY `id_alumno` (`id_usuario`),
  CONSTRAINT `inscripciones_ibfk_1` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`),
  CONSTRAINT `inscripciones_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `inscripciones` */

insert  into `inscripciones`(`id_inscripcion`,`fecha_inscripcion`,`id_curso`,`id_usuario`) values (1,'2026-10-20',1,1),(2,'2026-10-20',1,2),(3,'2026-10-20',1,3),(4,'2026-10-21',2,4),(5,'2026-10-21',2,5),(6,'2026-10-21',5,6),(7,'2026-10-21',5,7),(8,'2026-10-22',5,1),(9,'2026-10-22',6,8),(10,'2026-10-22',6,9),(11,'2026-10-22',19,10),(12,'2026-10-22',20,10);

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido_paterno` varchar(100) NOT NULL,
  `apellido_materno` varchar(100) DEFAULT NULL,
  `correo_institucional` varchar(150) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `fecha_creacion` date NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `matricula` (`matricula`),
  UNIQUE KEY `correo_institucional` (`correo_institucional`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `usuarios` */

insert  into `usuarios`(`id_usuario`,`matricula`,`nombre`,`apellido_paterno`,`apellido_materno`,`correo_institucional`,`contrasena`,`fecha_creacion`) values (1,'20260001','Ana','Hernandez','Martinez','20260001@uthh.edu.mx','prueba123','2026-10-24'),(2,'20260002','Carlos','Ramirez','Lopez','20260002@uthh.edu.mx','prueba123','2026-10-24'),(3,'20260003','Maria','Cruz','Hernandez','20260003@uthh.edu.mx','prueba123','2026-10-24'),(4,'20260004','Jose','Martinez','Sanchez','20260004@uthh.edu.mx','prueba123','2026-10-24'),(5,'20260005','Daniela','Lopez','Garcia','20260005@uthh.edu.mx','prueba123','2026-10-24'),(6,'20260006','Luis','Hernandez','Cruz','20260006@uthh.edu.mx','prueba123','2026-10-24'),(7,'20260007','Fernanda','Sanchez','Ramirez','20260007@uthh.edu.mx','prueba123','2026-10-24'),(8,'20260008','Miguel','Garcia','Martinez','20260008@uthh.edu.mx','prueba123','2026-10-24'),(9,'20260009','Sofia','Reyes','Hernandez','20260009@uthh.edu.mx','prueba123','2026-10-24'),(10,'20260010','Diego','Morales','Cruz','20260010@uthh.edu.mx','prueba123','2026-10-24'),(11,'20260011','Valeria','Torres','Lopez','20260011@uthh.edu.mx','prueba123','2026-10-24'),(12,'20260012','Jorge','Santos','Ramirez','20260012@uthh.edu.mx','prueba123','2026-10-24'),(13,'20260013','Camila','Mendoza','Garcia','20260013@uthh.edu.mx','prueba123','2026-10-24'),(14,'20260014','Fernando','Vazquez','Hernandez','20260014@uthh.edu.mx','prueba123','2026-10-24'),(15,'20260015','Andrea','Castillo','Martinez','20260015@uthh.edu.mx','prueba123','2026-10-24'),(16,'20260016','Eduardo','Flores','Sanchez','20260016@uthh.edu.mx','prueba123','2026-10-24'),(17,'20260017','Regina','Gonzalez','Cruz','20260017@uthh.edu.mx','prueba123','2026-10-24'),(18,'20260018','Alejandro','Ortiz','Lopez','20260018@uthh.edu.mx','prueba123','2026-10-24'),(19,'20260019','Ximena','Rivera','Hernandez','20260019@uthh.edu.mx','prueba123','2026-10-24'),(20,'20260020','Sebastian','Aguilar','Martinez','20260020@uthh.edu.mx','prueba123','2026-10-24');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
