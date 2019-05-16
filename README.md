# Erachain DEX Bot Trader
## Торговый робот для децентрализованной биржи в Erachain

Позволяет получать курсы обмена с разных бирж (настройка внутри кода),
 и выставлять на децентрализованную биржу Эрачейн свои заявки по заданному алгоритму.

Для работы бота нужно запустить на локальной машине полную ноду Эрачейн
 и открыть доступ по RPC (на порт 127.0.0.1) и задать пароль у кошелька более 8-ми символов.
  Перезадать пароль можно так:
 + удалить из рабочей папки ноды папки walletKeys и dataWallet
 + запустить ноду и восстановить по СИДу кошелек и задать нужный пароль 

## Разорачивание проекта в IDEA
Установите пакет разработки IDEA и java JDK 1.8


### Скопируйте ссылку с Git
![Скопируйте ссылку](TRADER/help/001.png)


### Начните новый проект в IDEA из репозитория Git
![Скопируйте ссылку](TRADER/help/002.png)


### Вставьте ссылку
![Скопируйте ссылку](TRADER/help/003.jpg)
И нажмите Clone - проект скопируется с репозитария и IDEA предложит настроить Gradle


### Нажмите "Настроить Gradle"
![Скопируйте ссылку](TRADER/help/004.jpg)


### Настроить Gradle
![Скопируйте ссылку](TRADER/help/005.jpg)
Задайте JDK 1.8
 

### Настройка библиотек
![Скопируйте ссылку](TRADER/help/007.jpg)
Откройте файл Start, в нем поставьте курсор на любое красное слово String
 и нажмите Att-Enter.

![Скопируйте ссылку](TRADER/help/008.jpg)

В появившемся списке выберите Задать JDK. Выберите JDK 1.8

![Скопируйте ссылку](TRADER/help/009.jpg)


### Создайте Запуск приложения
![Скопируйте ссылку](TRADER/help/010.jpg)


### Задайте класс запуска
![Скопируйте ссылку](TRADER/help/011.png)


### Настройте параметры запуска
![Скопируйте ссылку](TRADER/help/012.png)
Имя, Путь, Модуль

### Настройте торговцев
![Скопируйте ссылку](TRADER/help/013.png)
Скопируйте файл traders-orig.json в traders.json и задайте в нем настройки своих трейдеров.
Описание настроек находится в файле traders.readme.txt

### Запус программы из IDEA и выкладка и обновление на GitHub
![Скопируйте ссылку](TRADER/help/014.png)


### Создание JAR файла для запуска отдельно от IDEA
![Скопируйте ссылку](TRADER/help/016.png)
Откройте закладку Gradle и двойной клик по build - он запустит сборку JAR
 и создаст настройку запуска (правй верхний угол) - можно будет потом запускать эту сборку быстро по кнопку Запуск
 
 Там же вверху кнопка для отладки
 
 После того как сборка закончилась нужные файлы лежать в папке dex-trader\build\libs\DEXTrader
 
 Для авбора нужной сети блокчейн - отладочная или боевая используйте настройку в org.erachain.dextrader.controller.Controller.DEVELOP_USE
 
 
 
