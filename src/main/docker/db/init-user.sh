#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE USER timelogtest WITH PASSWORD '1HaventChangedItYet!';
CREATE DATABASE timelogtest;
GRANT ALL PRIVILEGES ON DATABASE timelogtest TO timelogtest;
EOSQL
