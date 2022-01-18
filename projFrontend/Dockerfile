FROM httpd:2.4-alpine

COPY ./httpd.conf /usr/local/apache2/conf/

COPY ./dist/projFrontend /usr/local/apache2/htdocs/
COPY ./.htaccess /usr/local/apache2/htdocs/