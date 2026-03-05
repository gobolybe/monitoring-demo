DROP TABLE IF EXISTS user_sample;
CREATE TABLE IF NOT EXISTS
    user_sample 
    ( 
        id SERIAL, 
		name                CHARACTER VARYING NOT NULL, 
        email                     CHARACTER VARYING,  
        CONSTRAINT pk_user PRIMARY KEY (id)
    );
-- Felhasználó létrehozása
CREATE USER postgres_exporter WITH PASSWORD 'postgres';

-- Olvasási jog adása a statisztikákhoz (PostgreSQL 10+)
GRANT pg_monitor TO postgres_exporter;