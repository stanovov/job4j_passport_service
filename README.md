[![Build Status](https://app.travis-ci.com/stanovov/job4j_passport_service.svg?branch=master)](https://app.travis-ci.com/stanovov/job4j_passport_service)

![](https://img.shields.io/badge/Maven-=_3-red)
![](https://img.shields.io/badge/Java-=_14-orange)
![](https://img.shields.io/badge/Spring-=_5-darkorange)
![](https://img.shields.io/badge/Liquibase-=_3-f02a18)
![](https://img.shields.io/badge/PostgerSQL-=_9-blue)
![](https://img.shields.io/badge/Checkstyle-lightgrey)

# job4j_passport_service

+ [О проекте](#О-проекте)
+ [Технологии](#Технологии)
+ [Использование](#Использование)
+ [Контакты](#Контакты)

## О проекте

Сервис использует Spring Boot и архитектуру REST. Для хранения используется PostgreSQL. Сервис занимается управлением 
паспортами.

## Технологии

+ **Java 17**, **Spring Boot**, **Kafka**;
+ **PostgreSQL**, **Liquibase**;
+ Сборщик проектов **Maven**;
+ Инструмент для анализа стиля кода - **Checkstyle**;

## Использование

### Паспорта

`POST /passports/save` - сохранить данные паспорта

`PUT /passports/update` - обновить данные паспорта

`DELETE /passports/delete/{id}` - удалить данные паспорта

`GET /passports/find` - загрузить все паспорта

`GET /passports/find-by-series?series={series}` - загрузить паспорт с определенной серией

`GET /passports/find-expired` - загрузить паспорта чей срок вышел

`GET /passports/find-replaceable` - загрузить паспорта, которые нужно заменить в ближайшие 3 месяца

## Контакты

Становов Семён Сергеевич

Email: sestanovov@gmail.com

Telegram: [@stanovovss](https://t.me/stanovovss)