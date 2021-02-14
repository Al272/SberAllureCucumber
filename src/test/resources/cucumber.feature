#language: ru

@all
#noinspection NonAsciiCharacters
Функционал: Ипотека

  @smoke
  @checkEmailErrorMessage
  Сценарий: Заявка на ипотеку
    * Загружена стартовая страница
    * Переход в главное меню 'Ипотека'
    * Выбираем подменю 'Ипотека на готовое жильё'
    * Проверка открытия страницы 'Ипотека на готовое жилье — СберБанк'
    * Заполняем форму поле/значение
      | Стоимость недвижимости | 5180000 |
      | Первоначальный взнос   | 3058000 |
      | Срок кредита           | 30      |
    * Нажимаем на 'Ролики'
      | Страхование жизни и здоровья | false |
      | Молодая семья                | true  |
    * Проверяем корректность введенных данных
      | Ежемесячный платеж | 16017   |
      | Процентная ставка  | 11      |
      | Сумма кредита      | 2122000 |
      | Необходимый доход  | 20618   |

  @parameterized
  Структура сценария: Заявка на ипотеку <номер>
    * Загружена стартовая страница
    * Переход в главное меню 'Ипотека'
    * Выбираем подменю 'Ипотека на готовое жильё'
    * Проверка открытия страницы 'Ипотека на готовое жилье — СберБанк'
    * Заполняем форму поле/значение
      | Стоимость недвижимости | <Стоимость недвижимости> |
      | Первоначальный взнос   | <Первоначальный взнос> |
      | Срок кредита           | 30      |
    * Нажимаем на 'Ролики'
      | Страхование жизни и здоровья | false |
      | Молодая семья                | true  |
    * Проверяем корректность введенных данных
      | Ежемесячный платеж | 16017   |
      | Процентная ставка  | 11      |
      | Сумма кредита      | 2122000 |
      | Необходимый доход  | 20618   |

    Примеры:
      | номер | Стоимость недвижимости | Первоначальный взнос |
      | 1     | 5190000                | 3068000              |
      | 2     | 5170000                | 3048000              |