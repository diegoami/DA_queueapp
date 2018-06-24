FROM postgres:9.3
ENV POSTGRES_USER queapp
ENV POSTGRES_PASSWORD queapp
ENV POSTGRES_DB queapp
EXPOSE 5432
ADD create_db.sql /docker-entrypoint-initdb.d/


