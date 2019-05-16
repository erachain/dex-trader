# Erachain DEX Bot Trader
## Торговый робот для децентрализованной биржи в Erachain

Позволяет получать курсы обмена с разных бирж (настройка внутри кода),
 и выставлять на децентрализованную биржу Эрачейн свои заявки по заданному алгоритму.

![Erachain](TRADER/help/000.png)

Для работы бота нужно запустить на локальной машине полную ноду [Erachain](http://erachain.org)
 и открыть доступ по RPC (на локальный адрес 127.0.0.1) и задать пароль у кошелька более 8-ми символов.
  
  Перезадать пароль для кошелька можно так:
 + удалить из рабочей папки ноды папки walletKeys и dataWallet
 + запустить ноду и восстановить по СИДу кошелек и задать нужный пароль 

#### Пароль доступа к кошельку
 Задайте свой пароль доступа (хотя можно оставить и 123456789 - так как нода должна работать на защищенном компьютере) тут:  
 org.erachain.dextrader.traders.TradersManager.WALLET_PASSWORD

## См. как [Развернуть проект в IDEA](README-start.md)


#### Выбор сети - тестовая или боевая 
 Для выбора нужной сети блокчейн - отладочная или боевая используйте настройку в org.erachain.dextrader.controller.Controller.DEVELOP_USE

#### Циклический запуск
 Лучше всего запускать run.bat - так как там вставлен циклический запуск и другие настройки

#### Проверка жизнидеятельности
 Логи см в папке logs
 
#### Принудительный останов
 Нажмите в черном окошке, где крутится прога Crtl-C

#### Замечания
 Если доступа к полной ноде нет то прога выйдет сразу же.  
 Если курс для трейдера не найден (например связь с биржой пропала) трейдер снимет все заявки.
 
#### Настройка источников для получени курсов
 Есть 4 курсовика (настройка пар внутри кода):
  + RaterLiveCoin - за одинзапрос только одну пару
  + RaterLiveCoinRUR - за одинзапрос только одну пару
  + RaterPolonex - за один запрос выбирает все пары
  + RaterWEX - бывший BTC-e, сейчас не работает
 
 > Каждый курсовик использует АПИ соотвествующей биржи. Так как данные бесплатные,
  не рекомендую чаще чем раз в 10 минут получать курсы 


#### Настройки Трейдера
Файл настройки трейдеров содержит список настроек, каждый элемент котрого запускающий своего трейдера.

    {
    "absoluteAmount": false,
    "traderAddress": "7NhZBb8Ce1H2S2MkPerrMnKLZNf9ryNYtP",
    "sleepTime": 200,
    "cleanAllOnStart": true,
    "haveAssetKey": 1106,
    "wantAssetKey": 1108,
    "sourceExchange":"polonex",
    "limitUP": "0.01",
    "limitDown": "0.02",
    "scheme": {
      "1000":"0.1",
      "100":"0.03",
      "10":"0.001",
      "-10":"0.001",
      "-100":"0.03",
      "-1000":"0.1"
      }

**absoluteAmount** - разница в курсе везде заданы в абсолютных значениях, иначе в процентах    
**traderAddress** - счет с котрого будут создаваться ордера, должен быть в вашем кошельке  
**sleepTime** - время сна - лучше ставить не чаще чем один блок  
**cleanAllOnStart** - при запуске очищать стокан от всех своих ордеров. Очень удобно если у вас слетела прога  
**haveAssetKey** - номер актива в Erachain. Прога найдет его и возьмет Имя из блокчейна  
**wantAssetKey** - номер актива в Erachain  
**sourceExchange** - по курсу какого источника работаем  
**limitUP** - если превышена разница вверх нового курса от старого, то переносим все заявки в стакане  
**limitDown** - если превышена разница вних нового курса от старого, то переносим все заявки в стакане  
**scheme** - схема заявок в стакане:
 1. Количество продажи/покупки. Минус у количества означает покупку.
 2. Отклонение к среднему курсу.


 ## G
 Вы можете разрабатывать дальнейшие стратегии бота, создавая свои классы и внедряя их в org.erachain.dextrader.traders.TradersManager.start
