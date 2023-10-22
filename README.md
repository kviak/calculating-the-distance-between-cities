# Документация для проекта "Distance Calculator"

## Описание проекта
Данный проект представляет собой веб-сервис (REST) для вычисления расстояний между городами. В базе данных хранятся две сущности: город и расстояние между городами. Приложение позволяет рассчитывать расстояния двумя способами: "по прямой линии" (кратчайшее расстояние между двумя точками на сфере) и "через матрицу расстояний" (расстояния хранятся в базе данных).

## Используемые технологии
- Java 17
- Maven
- MySQL
- Liquibase

## Структура проекта
Проект следует структуре Maven и включает следующие элементы:

- `src`: Исходный код проекта.
- `pom.xml`: Файл конфигурации Maven для сборки проекта.
- `src/main/resources/db/changelog`: Здесь находятся файлы Liquibase для управления миграциями базы данных.
- `src/main/resources/upload-test.xml`: Файл для тестовой загрузки данных в бд. Представляет из себя XML-файл с городами и расстояния.

## Конфигурация базы данных
- Логин/пароль MySQL: `root/root`
- Имя базы данных: `distance-calculator`

## API Endpoints
Приложение предоставляет следующие API-эндпоинты:

1. **Получение списка всех городов в базе данных**

   - URL: `/api/v1/city`
   - Метод: `GET`
   - Выход:
     - `ID`: Идентификатор города.
     - `Name`: Название города.

2. **Расчет расстояния между городами**

   - URL: `/api/v1/distance-calculator`
   - Метод: `POST`
   - Входные параметры:
     - `calculation-type`: Тип расчета (&lt;Crowflight, Distance Matrix, All&gt;).
     - `from-city`: Начальный город (список городов).
     - `to-city`: Конечный город (список городов).
   - Выход:
     - Результаты расчета расстояния в соответствии с выбранным типом.
   - Пример запроса: `/api/v1/distance-calculator?from-city=3&to-city=4&calculation-type=All`

3. **Загрузка данных в базу данных**

   - URL: `/api/v1/upload`
   - Метод: `POST`
   - Вход:
     - Загрузка XML-файла с информацией о городах и расстояниях. Тестовый файл находится `/resources/upload-test.xml`
   - Выход:
     - HTTP-ответ с кодом 200 (без тела ответа).

*Проект разработан - Лапин Денис.*