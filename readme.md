
# javafx-test-app

## components

- java app
- mysql (start with ```cd database/ && docker compose up``` )

## external components used

https://dev.mysql.com/downloads/connector/j/

- copy .jar to /libs/ directory
- right click on jar
- add as library

---

https://gluonhq.com/products/javafx/

https://gluonhq.com/products/scene-builder/

## notes

You can delete docker volume data this way:
```bash
docker run --rm -v "${PWD}/mariadb:/var/lib/mysql" busybox rm -rf /var/lib/mysql/ 
```

---
To install dependencies
in IDEA:
- right click on project root
- maven
- Download Sources and Documentation
