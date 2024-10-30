
# javafx-test-app

## components

- java app
- mysql (start with ```cd database/ && docker compose up``` )

## external components used

https://dev.mysql.com/downloads/connector/j/

https://gluonhq.com/products/javafx/

https://gluonhq.com/products/scene-builder/

## notes

You can delete docker volume data this way:
```bash
docker run --rm -v "${PWD}/mariadb:/var/lib/mysql" busybox rm -rf /var/lib/mysql/ 
```
