-- database: ../DataBase/Datos.sqlite

-- Eliminar tablas si existen
DROP TABLE IF EXISTS Hormiga;
DROP TABLE IF EXISTS Sexo;
DROP TABLE IF EXISTS GenoAlimento;
DROP TABLE IF EXISTS IngestaNativa;
DROP TABLE IF EXISTS Provincia;
DROP TABLE IF EXISTS Region;
DROP TABLE IF EXISTS Pais;
DROP TABLE IF EXISTS Registro;

-- Crear las tablas
CREATE TABLE Pais (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL UNIQUE
    ,Estado VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica DATETIME
);

CREATE TABLE Region (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL UNIQUE
    ,pais_id INTEGER
    ,Estado VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica DATETIME
    ,FOREIGN KEY (pais_id) REFERENCES Pais(id)
);

CREATE TABLE Provincia (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL UNIQUE
    ,region_id INTEGER
    ,Estado VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica DATETIME
    ,FOREIGN KEY (region_id) REFERENCES Region(id)
);

CREATE TABLE IngestaNativa (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL UNIQUE
    ,Estado VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica DATETIME
);

CREATE TABLE GenoAlimento (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL UNIQUE
    ,Estado VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica DATETIME
);

CREATE TABLE Sexo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL UNIQUE
    ,Estado VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica DATETIME
);

CREATE TABLE Hormiga (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo_hormiga TEXT NOT NULL
    ,sexo_id INTEGER
    ,provincia_id INTEGER
    ,geno_alimento_id INTEGER
    ,ingesta_nativa_id INTEGER
    ,EstadoHorm TEXT NOT NULL
    ,Estado VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica DATETIME
    ,FOREIGN KEY (sexo_id) REFERENCES Sexo(id)
    ,FOREIGN KEY (provincia_id) REFERENCES Provincia(id)
    ,FOREIGN KEY (geno_alimento_id) REFERENCES GenoAlimento(id)
    ,FOREIGN KEY (ingesta_nativa_id) REFERENCES IngestaNativa(id)
);

CREATE TABLE Registro (
        IdRegistro      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT     
        ,Username       VARCHAR(10) NOT NULL UNIQUE
        ,Password       VARCHAR(30)
        ,Estado         VARCHAR(1)  NOT NULL DEFAULT('A')
        ,FechaCreacion  DATETIME    DEFAULT(datetime('now','locatime'))
        ,FechaMod       DATETIME
);

