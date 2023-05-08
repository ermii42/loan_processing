
# Оформление кредита
Сервис, отвечающий за обработку кредитных заявок
## Использованные технологии:
* Spring Boot - платформа для создания веб-приложений
* Liquibase - система управления версиями базы данных
* Postgresql в качестве базы данных
* JdbcTemplate - для работы с реляционной базой данных
* ExceptionHandler - класс-обработчик исключений

## Основной функционал:
### Метод получения тарифов
```
  GET /loan-service/getTariﬀs
```
Логика метода:

Получить все данные из таблицы tariff и вернуть клиенту.

### Метод подачи заявки на кредит
```
  POST /loan-service/order
```
Пример входящих данных:
```
  {
    "user_id": 120356894755,
    "tariﬀ_id": 1
  }

```
Логика метода:

1.  Проверить, что тариф с входящим tariffId существует в таблице tariff. Если тариф не найден, ответить ошибкой TARIFF_NOT_FOUND. Метод заканчивает работу.


2.  Из таблицы loan_order по user_id = входящий userId получить заявки клиента.

      2.1  	Если есть заявка с tariff_id = tariffId и статусом IN_PROGRESS ответить ошибкой
LOAN_CONSIDERATION. Метод заканчивает работу.

      2.2	  Если есть заявка с tariff_id = tariffId и статусом APPROVED ответить ошибкой
LOAN_ALREADY_APPROVED. Метод заканчивает работу.

      2.3	  Если есть заявка с tariff_id = tariffId и статусом REFUSED, сравнить текущее время с time_update такой записи, если разница меньше двух минут ответить ошибкой TRY_LATER. Метод заканчивает работу.

3.	Если ни одно из условий п.2 не выполняется, создать новую строку в таблице
loan_order, заполнить следующими данными:

order_id = сгенерировать UUID user_id = входящий userId tariff_id = входящий tariffId
credit_rating = случайное число от 0.10 до 0.90, точность 2 знака после запятой
status = IN_PROGRESS
time_insert = текущая дата/время Ответить значением order_id.

### Метод получения статуса заявки
```
  GET /loan-service/getStatusOrder?orderId=${orderId}
```
Логика метода

Проверить, что заявка с входящим orderId существует в таблице loan_order. Если заявка не найдена, ответить ошибкой ORDER_NOT_FOUND, иначе ответить значением status у этой заявки.

### Метод удаления заявки
```
  DELETE /loan-service/deleteOrder
```
Логика метода
1.	Проверить, что заявка с входящими userId и orderId существует в таблице loan_order. Если заявка не найдена, ответить ошибкой ORDER_NOT_FOUND. Метод завершает работу.

2.	Если у найденной заявки status = IN_PROGRESS удалить заявку, иначе ответить ошибкой ORDER_IMPOSSIBLE_TO_DELETE.

### Рассмотрение заявки
Метод, который самостоятельно запускается с периодичностью раз в 2 минуты, из таблицы loan_order получает все заявки, у которых status = IN_PROGRESS. С вероятностью равной значению 50% одобряет или отклоняет заявку. В случае одобрения ставит status = APPROVED, в случае отклонения status = REFUSED, в обоих случаях ставит time_update = текущая дата/время.



