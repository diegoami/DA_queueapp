CREATE DATABASE queapp;
CREATE USER queapp WITH ENCRYPTED PASSWORD 'queapp';
GRANT ALL PRIVILEGES ON DATABASE queapp TO queapp;
COMMIT;
