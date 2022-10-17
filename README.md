
Мастер проект для теста
Язык реализации: Java 14
База данных: PostgreSQL
Миграция: Liquidbase

Функционал

REST Controller:
GET /api/latest/web/Master/info возвращает JSON объект в котором находится текущая версия Явы + Текущая дата
POST /api/latest/web/Master/entity возвращает UUID созданого объекта. На вход: дата+время + название
GET /api/latest/web/Master/{entityId} возвращает JSON объект: дата+время + название
GET /api/latest/web/internal/{entityId} возвращает JSON объект: дата+время + название

Kafka Listener:
слушает очередь pits.yandexcloud.CreateNewEntity на вход объект UUID - дата+время + название + почта.
Результатом обработки новая запись в БД с заданным UUID и после этого отправляет письмо на поту указанную в топике

