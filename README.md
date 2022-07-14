# Тестовое задание для embedika
RESTful архитекура c Spring MVC

БД - PostgreSQL, есть дамп в корне репозитория, user - "postgres", password - "postgres"

Ссылка на Postman - https://go.postman.co/workspace/My-Workspace~90bdc7ac-424b-43cd-8acc-3a2d417fad02/collection/17929303-21902ade-d40f-4230-8828-8de3680e65f5?action=share&creator=17929303

1 метод GET ("/autos") - получение всех автомобилей в виде массива json-ов с полями: UUID id, String plateNumber, String brand, String color, Integer manufacturingYear, Date creationDate.

2 метод POST ("/autos") - создание автомобиля с передачей следующих полей в виде json-a: plateNumber, brand, color, manufacturingYear.

3 метод DELETE ("/autos/{id}") - удаление автомобиля по переданному в url id объекта.

4 метод GET ("/statistics") - получение статистики по базе данных в виде json c полями: count (кол-во записей в таблице), firstRecordCreationData (дата создания первой записи), lastRecordCreationData (дата создания последней записи).