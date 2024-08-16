-- database: ../DataBase/Datos.sqlite



INSERT INTO Pais 
(nombre) VALUES 
('Ecuador');

INSERT INTO Region 
(nombre        ,pais_id) VALUES 
 ('Costa'       ,1)
,('Sierra'      ,1)
,('Oriente'     ,1)
,('Galápagos'   ,1);


INSERT INTO Provincia 
(nombre             ,region_id) VALUES 
 ('Guayas'          ,1)
,('Manabí'          ,1)
,('Santa Elena'     ,1)

,('Pichincha'       ,2)
,('Azuay'           ,2)
,('Carchi'          ,2)

,('Sucumbíos'       ,3)
,('Orellana'        ,3)
,('Napo'            ,3)

,('San Cristóbal'   ,4)
,('Santa Cruz'      ,4)
,('Isabela'         ,4);


INSERT INTO IngestaNativa 
(tipo) VALUES 
 ('Carnívoro')
,('Herbívoro')
,('Omnívoro')
,('Insectívoros');


INSERT INTO GenoAlimento 
(tipo) VALUES 
 ('X')
,('XX')
,('XY');


INSERT INTO Sexo 
(tipo) VALUES 
 ('Macho')
,('Hembra')
,('Asexual');


INSERT INTO Hormiga 
(tipo_hormiga         ,sexo_id ,provincia_id ,geno_alimento_id ,ingesta_nativa_id  ,EstadoHorm) VALUES 
 ('Hormiga 1'          ,1       ,5            ,1                ,1                 ,'Activa')
,('Hormiga 2'          ,1       ,4            ,2                ,2                 ,'Inactiva')
,('Hormiga 3'          ,3       ,8            ,3                ,3                 ,'Activa');

DELETE FROM Hormiga;



SELECT  ROW_NUMBER () OVER (ORDER BY id) RowNum
,id, 
tipo_hormiga, 
sexo_id, 
provincia_id, 
geno_alimento_id, 
ingesta_nativa_id, 
EstadoHorm, 
Estado, 
FechaCrea, 
FechaModifica 
FROM Hormiga 
WHERE Estado = 'A';


 -- Insertar LocalidadTipo
-- INSERT INTO LocalidadTipo (Nombre, Descripcion) VALUES
--   ('Pais', 'pais'),
--   ('Regiones', 'regiones del pais'),
--   ('Provincias', 'provincias del pais');

-- -- Insertar País
-- INSERT INTO Localidad 
-- (IdCatalogoTipo, Nombre            ,Descripcion) VALUES
--   (1           ,'Ecuador'          ,'pais'),                -- 1

--   (2           ,'Costa'            ,'regiones del Ecuador'),      -- 2
--   (2           ,'Sierra'           ,'regiones del Ecuador'),      -- 3
--   (2           ,'Oriente'          ,'regiones del Ecuador'),     -- 4
--   (2           ,'Insular'          ,'regiones del Ecuador'), -- 5

--   (3           ,'Azuay'            ,'provincia de la Sierra'),     -- 6
--   (3           ,'Bolívar'          ,'provincia de la Sierra'),   -- 7
--   (3           ,'Cañar'            ,'provincia de la Sierra'),     -- 8
--   (3           ,'Carchi'           ,'provincia de la Sierra'),    -- 9
--   (3           ,'Chimborazo'       ,'provincia de la Sierra'),-- 10
--   (3           ,'Cotopaxi'         ,'provincia de la Sierra'),  -- 11
--   (3           ,'El Oro'           ,'provincia de la Costa'),     -- 12
--   (3           ,'Esmeraldas'       ,'provincia de la Costa'), -- 13
--   (3           ,'Guayas'           ,'provincia de la Costa'),     -- 14
--   (3           ,'Imbabura'         ,'provincia de la Sierra'),  -- 15
--   (3           ,'Loja'             ,'provincia de la Sierra'),      -- 16
--   (3           ,'Los Ríos'         ,'provincia de la Costa'),   -- 17
--   (3           ,'Manabí'           ,'provincia de la Costa'),     -- 18
--   (3           ,'Morona Santiago'  ,'provincia del Oriente'), -- 19
--   (3           ,'Napo'             ,'provincia del Oriente'),       -- 20
--   (3           ,'Orellana'         ,'provincia del Oriente'),   -- 21
--   (3           ,'Pastaza'          ,'provincia del Oriente'),    -- 22
--   (3           ,'Pichincha'        ,'provincia de la Sierra'), -- 23
--   (3           ,'Santa Elena'      ,'provincia de la Costa'),-- 24
--   (3           ,'Sucumbíos'        ,'provincia del Oriente'),  -- 25
--   (3           ,'Tungurahua'       ,'provincia de la Sierra'),-- 26
--   (3           ,'Zamora-Chinchipe' ,'provincia del Oriente'); -- 27