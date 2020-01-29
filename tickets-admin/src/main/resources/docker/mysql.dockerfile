FROM mysql:5.7
LABEL Author="ArtemGeek", Version=0.1
ENV MYSQL_ROOT_PASSWORD=root \
       MYSQL_USER=germes \
       MYSQL_PASSWORD=germes \
       MYSQL_DATABASE=germes

RUN service mysql start && \
   mysql -uroot -p$MYSQL_ROOT_PASSWORD -e "CREATE DATABASE germes" && \
   mysql -uroot -p$MYSQL_ROOT_PASSWORD -e "CREATE USER 'germes'@'%' identified by 'germes';" && \
   mysql -uroot -p$MYSQL_ROOT_PASSWORD -e "GRANT ALL ON germes.* TO 'germes'@'%' ; FLUSH PRIVILEGES"

ADD src/main/resources/docker/grant.sql /docker-entrypoint-initdb.d/
