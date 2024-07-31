
#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE DATABASE monoliet;
  CREATE DATABASE roostering;
  CREATE DATABASE ruimtebeheer;
  CREATE DATABASE onderzoek;
EOSQL
