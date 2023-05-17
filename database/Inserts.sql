-- Sedes
/*
INSERT INTO dbo.Sedes (idSede, nombre)
VALUES ('CA', 'Central de Cartago'),
('SJ', 'San Jose'),
('LI', 'Limon'),
('AL', 'Alajuela'),
('SC', 'San Carlos')

select * from dbo.Sedes
*/
GO


-- Estudiantes
/*
INSERT INTO dbo.Estudiantes (idSede, carne, apellido1, apellido2, nombre, segundoNombre, correo, numeroCelular, contraseña)
VALUES
    ('CA', 2020087412, 'Salazar', 'Rojas', 'Alexander', 'Javier', 'asalazar@estudiantec.cr', 60408135, '18374087'),
    ('CA', 2023153905, 'Alvarado', 'Arguedas', 'Luis', 'Esteban', 'lalvarado@estudiantec.cr', 81159042, '90827312'),
    ('CA', 2021500501, 'Gonzalez', 'Sanchez', 'Gabriel', 'Alejandro', 'ggonzalez@estudiantec.cr', 63149265, '56783940'),
    ('CA', 2023728806, 'Martinez', 'Hernandez', 'Daniel', 'Sebastian', 'dmartinez@estudiantec.cr', 77822347, '68904527'),
    ('CA', 2022970142, 'Rodriguez', 'Gomez', 'Sofia', 'Fernanda', 'srodriguez@estudiantec.cr', 88146780, '26794518'),
    ('CA', 2023862865, 'Lopez', 'Torres', 'Valentina', 'Carolina', 'vlopez@estudiantec.cr', 86122258, '76954260'),
    ('CA', 2023486379, 'Hernandez', 'Flores', 'Sebastian', 'Jose', 'shernandez@estudiantec.cr', 84261294, '85691340'),
    ('CA', 2021234404, 'Garcia', 'Vargas', 'Mateo', 'Andres', 'mgarcia@estudiantec.cr', 74238440, '21374839'),
    ('CA', 2021205513, 'Perez', 'Jimenez', 'Isabella', 'Valeria', 'iperez@estudiantec.cr', 75698815, '83921645'),
    ('CA', 2023927665, 'Torres', 'Diaz', 'Camila', 'Fernanda', 'ctorres@estudiantec.cr', 67135585, '54899227'),
    ('SJ', 2021202852, 'Gomez', 'Vargas', 'Juan', 'Carlos', 'jgomez@estudiantec.cr', 69728473, '32653495'),
    ('SJ', 2022994383, 'Lopez', 'Chacon', 'Mariana', 'Isabel', 'mlopez@estudiantec.cr', 78499136, '57192384'),
    ('SJ', 2023523698, 'Hernandez', 'Rojas', 'Diego', 'Andres', 'dhernandez@estudiantec.cr', 86061875, '49283757'),
    ('SJ', 2021741207, 'Rodriguez', 'Jimenez', 'Santiago', 'Ignacio', 'srodriguez@estudiantec.cr', 79456481, '35418682'),
    ('SJ', 2021503893, 'Gonzalez', 'Sanchez', 'Gabriel', 'Alejandro', 'ggonzalez@estudiantec.cr', 63149265, '56783940'),
    ('SJ', 2023005894, 'Martinez', 'Hernandez', 'Daniel', 'Sebastian', 'dmartinez@estudiantec.cr', 77822347, '68904527'),
    ('SJ', 2022403947, 'Rodriguez', 'Gomez', 'Sofia', 'Fernanda', 'srodriguez@estudiantec.cr', 88146780, '26794518'),
    ('SJ', 2023854678, 'Lopez', 'Torres', 'Valentina', 'Carolina', 'vlopez@estudiantec.cr', 86122258, '76954260'),
    ('SJ', 2023357896, 'Hernandez', 'Flores', 'Sebastian', 'Jose', 'shernandez@estudiantec.cr', 84261294, '85691340'),
    ('SJ', 2021801546, 'Garcia', 'Vargas', 'Mateo', 'Andres', 'mgarcia@estudiantec.cr', 74238440, '21374839'),
    ('LI', 2021957523, 'Perez', 'Jimenez', 'Isabella', 'Valeria', 'iperez@estudiantec.cr', 75698815, '83921645'),
    ('LI', 2023356814, 'Torres', 'Diaz', 'Camila', 'Fernanda', 'ctorres@estudiantec.cr', 67135585, '54899227'),
    ('LI', 2021948259, 'Gomez', 'Vargas', 'Juan', 'Carlos', 'jgomez@estudiantec.cr', 69728473, '32653495'),
    ('LI', 2023974767, 'Lopez', 'Chacon', 'Mariana', 'Isabel', 'mlopez@estudiantec.cr', 78499136, '57192384'),
    ('LI', 2023357892, 'Hernandez', 'Rojas', 'Diego', 'Andres', 'dhernandez@estudiantec.cr', 86061875, '49283757'),
    ('LI', 2021946311, 'Rodriguez', 'Jimenez', 'Santiago', 'Ignacio', 'srodriguez@estudiantec.cr', 79456481, '35418682'),
    ('LI', 2021115491, 'Morales', 'Alfaro', 'Valeria', 'Daniela', 'vmorales@estudiantec.cr', 68639617, '53928475'),
    ('LI', 2023971800, 'Vargas', 'Sanchez', 'Javier', 'Andres', 'jvargas@estudiantec.cr', 82341053, '79356248'),
    ('LI', 2022147862, 'Sanchez', 'Mora', 'Maria', 'Fernanda', 'msanchez@estudiantec.cr', 68049587, '36589172'),
    ('LI', 2023693056, 'Mora', 'Soto', 'Valentina', 'Isabella', 'vmora@estudiantec.cr', 82544759, '59873146'),
    ('AL', 2022054919, 'Soto', 'Gonzalez', 'Gabriel', 'Alejandro', 'gsoto@estudiantec.cr', 68142937, '42657893'),
    ('AL', 2022041876, 'Cordero', 'Jimenez', 'Sofia', 'Fernanda', 'scordero@estudiantec.cr', 85730314, '94728135'),
    ('AL', 2023072457, 'Monge', 'Sanchez', 'Valentina', 'Carolina', 'vmonge@estudiantec.cr', 63124568, '75392467'),
    ('AL', 2023274866, 'Araya', 'Hernandez', 'Sebastian', 'Jose', 'saraya@estudiantec.cr', 88346409, '46271830'),
    ('AL', 2021983745, 'Valverde', 'Vargas', 'Mateo', 'Andres', 'mvalverde@estudiantec.cr', 78936514, '34782915'),
    ('AL', 2022194852, 'Brenes', 'Jimenez', 'Isabella', 'Valeria', 'ibrenes@estudiantec.cr', 72145035, '58472918'),
    ('AL', 2021227598, 'Serrano', 'Diaz', 'Camila', 'Fernanda', 'cserrano@estudiantec.cr', 86329578, '49285742'),
    ('AL', 2021759027, 'Guzman', 'Vargas', 'Juan', 'Carlos', 'jguzman@estudiantec.cr', 69847623, '32985645'),
    ('AL', 2023125774, 'Urena', 'Chacon', 'Mariana', 'Isabel', 'murena@estudiantec.cr', 79768254, '57293841'),
    ('AL', 2023779204, 'Alfaro', 'Rojas', 'Diego', 'Andres', 'dalfaro@estudiantec.cr', 86215478, '49827653'),
    ('SC', 2023249804, 'Bermudez', 'Jimenez', 'Santiago', 'Ignacio', 'sbermudez@estudiantec.cr', 79457289, '36819452'),
    ('SC', 2022109845, 'Cruz', 'Alfaro', 'Valeria', 'Daniela', 'vcruz@estudiantec.cr', 68932147, '52381749'),
    ('SC', 2021603569, 'Fonseca', 'Sanchez', 'Javier', 'Andres', 'jfonseca@estudiantec.cr', 82141984, '73645128'),
    ('SC', 2023101458, 'Solis', 'Mora', 'Maria', 'Fernanda', 'msolis@estudiantec.cr', 69258149, '35981246'),
    ('SC', 2021874590, 'Morales', 'Rojas', 'Lucas', 'Sebastian', 'lmorales@estudiantec.cr', 77532981, '46582173'),
    ('SC', 2023469250, 'Arce', 'Soto', 'Valentina', 'Isabella', 'varce@estudiantec.cr', 83145497, '59472851'),
    ('SC', 2023197784, 'Vargas', 'Gonzalez', 'Gabriel', 'Alejandro', 'gvargas@estudiantec.cr', 68945712, '41257839'),
    ('SC', 2022253811, 'Chaves', 'Jimenez', 'Sofia', 'Fernanda', 'schaves@estudiantec.cr', 85429673, '93281745'),
    ('SC', 2021384523, 'Solano', 'Sanchez', 'Valentina', 'Carolina', 'vsolano@estudiantec.cr', 63421254, '75892746'),
    ('SC', 2023729154, 'Fallas', 'Hernandez', 'Sebastian', 'Jose', 'sfallas@estudiantec.cr', 89545827, '43782135')

select * from dbo.Estudiantes
*/
GO


-- Asistentes
/*
INSERT INTO dbo.Asistentes (nombre, idSede, correo, contraseña, celular) VALUES
('Juan Carlos Fernandez', 'CA', 'jfernandez@itcr.ac.cr', '12345678', '62123456'),
('Maria Perez Gomez', 'CA', 'mperez@itcr.ac.cr', '98765432', '63456789'),
('Luis Rodriguez Martinez', 'CA', 'lrodriguez@itcr.ac.cr', '45678901', '67891234'),
('Alejandra Torres Ramirez', 'SJ', 'atorres@itcr.ac.cr', '23456789', '71234567'),
('Andres Sanchez Mora', 'SJ', 'asanchez@itcr.ac.cr', '87654321', '74567890'),
('Camila Gonzalez Castro', 'SJ', 'cgonzalez@itcr.ac.cr', '34567890', '78901234'),
('Roberto Hernandez Solis', 'LI', 'rhernandez@itcr.ac.cr', '56789012', '82123456'),
('Valeria Alvarado Gomez', 'LI', 'valvarado@itcr.ac.cr', '90123456', '85678901'),
('Felipe Castillo Rojas', 'LI', 'fcastillo@itcr.ac.cr', '67890123', '89012345'),
('Juliana Morales Soto', 'AL', 'jmorales@itcr.ac.cr', '01234567', '92345678'),
('Santiago Navarro Chaves', 'AL', 'snavarro@itcr.ac.cr', '76543210', '95678901'),
('Isabela Vega Arias', 'AL', 'ivega@itcr.ac.cr', '23456789', '99012345'),
('Javier Castillo Solano', 'SC', 'jcastillo@itcr.ac.cr', '98765432', '42123456'),
('Fernanda Morales Rojas', 'SC', 'fmorales@itcr.ac.cr', '45678901', '45678901'),
('Daniel Herrera Araya', 'SC', 'dherrera@itcr.ac.cr', '12345678', '48901234');

select * from dbo.Asistentes
*/
GO


-- Profesores
/*
INSERT INTO dbo.Profesores (nombre, idSede, correo, numeroOficina, numeroCelular, foto, contraseña, darDeBaja) VALUES
('Luisa Herrera Fernandez', 'CA', 'lherrera@itcr.ac.cr', 12345678, 61234567, NULL, '87654321', 0),
('Ricardo Ramirez Gomez', 'CA', 'rramirez@itcr.ac.cr', 23456789, 62345678, NULL, '98765432', 0),
('Laura Torres Martinez', 'CA', 'ltorres@itcr.ac.cr', 34567890, 63456789, NULL, '23456789', 0),
('Jorge Sanchez Mora', 'CA', 'jsanchez@itcr.ac.cr', 45678901, 64567890, NULL, '76543210', 0),
('Marina Rodriguez Castro', 'CA', 'mrodriguez@itcr.ac.cr', 56789012, 65678901, NULL, '09876543', 0),
('Daniel Alvarado Gomez', 'SJ', 'dalvarado@itcr.ac.cr', 67890123, 66789012, NULL, '34567890', 0),
('Valentina Castro Ramirez', 'SJ', 'vcastro@itcr.ac.cr', 78901234, 67890123, NULL, '89012345', 0),
('Alejandro Hernandez Solis', 'SJ', 'ahernandez@itcr.ac.cr', 89012345, 68901234, NULL, '45678901', 0),
('Sofia Alvarado Mora', 'SJ', 'salvarado@itcr.ac.cr', 90123456, 69012345, NULL, '12345678', 0),
('Andres Mora Gomez', 'SJ', 'amora@itcr.ac.cr', 01234567, 70123456, NULL, '56789012', 0),
('Carolina Solis Fernandez', 'LI', 'csolis@itcr.ac.cr', 12345678, 71234567, NULL, '90123456', 0),
('Julio Vargas Martinez', 'LI', 'jvargas@itcr.ac.cr', 23456789, 72345678, NULL, '23456789', 0),
('Natalia Mora Castillo', 'LI', 'nmora@itcr.ac.cr', 34567890, 73456789, NULL, '67890123', 0),
('Fernando Gomez Solano', 'LI', 'fgomez@itcr.ac.cr', 45678901, 74567890, NULL, '34567890', 0),
('Gabriela Solis Hernandez', 'LI', 'gsolis@itcr.ac.cr', 56789012, 75678901, NULL, '89012345', 0),
('Mariano Herrera Rojas', 'AL', 'mherrera@itcr.ac.cr', 67890123, 76789012, NULL, '45678901', 0),
('Isabella Ramirez Gomez', 'AL', 'iramirez@itcr.ac.cr', 78901234, 77890123, NULL, '12345678', 0),
('Javier Torres Mora', 'AL', 'jtorres@itcr.ac.cr', 89012345, 78901234, NULL, '56789012', 0),
('Sara Fernandez Solis', 'AL', 'sfernandez@itcr.ac.cr', 90123456, 79012345, NULL, '23456789', 0),
('Carlos Mora Gomez', 'AL', 'cmora@itcr.ac.cr', 01234567, 80123456, NULL, '67890123', 0),
('Valeria Hernandez Mora', 'SC', 'vhernandez@itcr.ac.cr', 34567890, 83456789, NULL, '45678901', 0),
('Diego Solano Gomez', 'SC', 'dsolano@itcr.ac.cr', 45678901, 84567890, NULL, '12345678', 0),
('Camila Mora Fernandez', 'SC', 'cmora@itcr.ac.cr', 56789012, 85678901, NULL, '56789012', 0),
('Sebastian Gomez Solis', 'SC', 'sgomez@itcr.ac.cr', 67890123, 86789012, NULL, '90123456', 0),
('Isabel Solis Mora', 'SC', 'isolis@itcr.ac.cr', 78901234, 87890123, NULL, '23456789', 0)

select * from dbo.Profesores
*/
GO


-- ProfesoresGuias
/*
INSERT INTO dbo.ProfesoresGuias (idProfesor, fechaInicio, fechaFinal, codigo, coordinador)
VALUES (1, CONVERT(date, GETDATE()), NULL, 'CA-1', 1),
(6, CONVERT(date, GETDATE()), NULL, 'SJ-1', 0),
(11, CONVERT(date, GETDATE()), NULL, 'LI-1', 0),
(16, CONVERT(date, GETDATE()), NULL, 'AL-1', 0),
(21, CONVERT(date, GETDATE()), NULL, 'SC-1', 0)

select * from dbo.ProfesoresGuias
inner join Profesores on ProfesoresGuias.idProfesor = Profesores.idProfesor
*/
GO


-- Modificaciones
/*
INSERT INTO dbo.Modificaciones (descripcion)
VALUES ('Registro de nuevo ingreso de profesor(a)'),
('Dar de baja a profesor(a)'),
('Alteracion de informacion de profesor(a)')

select * from dbo.Modificaciones
*/
GO


-- ModificacionesProfesoresGuias
/*
INSERT INTO dbo.ModificacionesProfesoresGuias (idProfesor, idAsistente, idModificacion, fecha)
VALUES (1, 1, 1, CONVERT(date, GETDATE())),
(6, 4, 1, CONVERT(date, GETDATE())),
(11, 7, 1, CONVERT(date, GETDATE())),
(16, 10, 1, CONVERT(date, GETDATE())),
(21, 13, 1, CONVERT(date, GETDATE()))

select * from dbo.ModificacionesProfesoresGuias
inner join ProfesoresGuias on ModificacionesProfesoresGuias.idProfesor = ProfesoresGuias.idProfesor
*/
GO


-- Estados
/*
INSERT INTO dbo.Estados (estado)
VALUES ('Planeada'),
('Notificada'),
('Realizada'),
('Cancelada')

select * from dbo.Estados
*/
GO


-- TiposActividad
/*
INSERT INTO dbo.TiposActividad (tipo)
VALUES ('Orientadoras'),
('Motivacionales'),
('De apoyo a la vida estudiantil'),
('De orden tecnico'),
('De recreacion')

select * from dbo.TiposActividad
*/
GO


-- Modalidades
/*
INSERT INTO dbo.Modalidades (modalidad)
VALUES ('Presencial'),
('Virtual')

select * from dbo.Modalidades
*/
GO


-- PlanDeTrabajo
/*
INSERT INTO dbo.PlanDeTrabajo (nombre, idProfesorCoordinador)
VALUES ('1er Semestre 2023', 1)

select * from dbo.PlanDeTrabajo
*/
GO

-- Actividades
/*
INSERT INTO dbo.Actividades (idPlanDeTrabajo, semana, idTipoActividad, nombre, fechaHora,
cantidadDiasAnuncio, fechaPublicacion, cantidadRecordatorios, idModalidad, enlace, afiche, idEstado)
VALUES (1, 1, 1, 'Semana 1', DATEADD(day, 12, GETDATE()), 10, DATEADD(day, 2, GETDATE()), 3, 1, NULL,
CAST(NEWID() AS varbinary(max)), 1)

select * from dbo.Actividades
*/
GO


-- ProfesoresActividades
/*
INSERT INTO dbo.ProfesoresActividades (idProfesor, idActividad)
VALUES (1, 1),
(2, 1),
(6, 1)

select * from dbo.ProfesoresActividades
*/
GO


-- Evidencias 
/*
INSERT INTO dbo.Evidencias (evidencia, idActividad)
VALUES (CAST(NEWID() AS varbinary(max)), 2),
(CAST(NEWID() AS varbinary(max)), 2)

select * from dbo.Evidencias
*/
GO


-- Recordatorios
/*
INSERT INTO dbo.Recordatorios (idActividad, fecha)
VALUES (1, '2023-05-27'),
(1, '2023-05-26'),
(1, '2023-05-25')

select * from dbo.Recordatorios
*/
GO


-- Comentarios
/*
INSERT INTO dbo.Comentarios (idActividad, idProfesor, fecha, idComentarioOriginal, comentario)
VALUES (1, 1, GETDATE(), NULL, 'Estoy muy emocionada'),
(1, 6, GETDATE(), 1, 'Yo igual'),
(1, 11, GETDATE(), NULL, 'Lastima que no podre asistir')

select * from dbo.Comentarios
*/
GO