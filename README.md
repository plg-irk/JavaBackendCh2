**Чек-лист проверки CRUD-запросов мини-маркета 
http://80.78.248.82:8189/market/** 

**Описание проверки CategoryTest.**

_1.1 getCategoryByIdTest() - запрос ID категории_

Ожидаемый результат.

1.2 Возвращается ID=2 для категории ELECTRONIC

1.3 Возвращается название категории ELECTRONIC для категории ELECTRONIC


**Описание проверки ProductTests.**

_2.1 setUp() - подготовка данных, создание продукта_

Ожидаемый результат.

2.2 Создается продукт с названием, ценой произвольного содержания и категорией FOOD.

2.3 Фиксируется ID продукта


_3.1 getProductTest() - запрос о продукте фиксированного ID._

Ожидаемый результат

3.2 Возвращаемое значение Title соответствует Title продукта

3.3 Возвращаемое значение Price соответствует Price продукта

3.4 Возвращаемое значение Category соответствует Category продукта


_4.1 putProductTest() - изменение данных о продукте фиксированного ID_

Ожидаемый результат

4.2 Успешое изменение данных. Код возврата 200.


_5.1 teardown() - удаление продукта с фиксированным ID._

Ожидаемый результат

5.2 Успешное удаление продукта. Код возврата 200.
