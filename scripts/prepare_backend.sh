service postgresql start
sleep 1
sudo -u postgres psql -f create_queapp.sql 
export POSTGRES_PASSWORD=queapp
sudo -u postgres PGPASSWORD=queapp psql -U queapp -d queapp -h 127.0.0.1 -f init_tables.sql
