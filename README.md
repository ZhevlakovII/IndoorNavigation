![Language badge](https://img.shields.io/badge/Language-Kotlin-blue?style=flat=appveyor)
# Current version
### Client
Beta 0.2.0
### Creator
Alpha 0.1.0

# Language
1. [Русский](#русский)
2. [English](#english)

# Русский
1. [О приложении](#о-приложении)
2. [Стек](#стек)
   1. [Функции](#функции)
3. [Ошибки и проблемы](#ошибки-и-проблемы)
4. [Дальнейшее развитие](#дальнейшее-развитие)
5. [Скриншоты](#скриншоты)

## О приложении
Первая попытка разработки приложения под Android с амбициозной задачей AR-навигации внутри здания. Промежуточный итог в виде представленного на дипломе приложения [тут](https://github.com/ZhevlakovII/IndoorNavOld), эта же итерация призвана улучшить кодовую базу и изменить используемые инструменты на более сложные/подходящие (например, переделать инжект данных в БД). 
## Стек
- [Kotlin](https://kotlinlang.org/) + [Java](https://www.java.com/ru/)
- [MVVM](https://www.youtube.com/watch?v=rb0lobFwZbg&list=PL_RkZ4J60MDkkgTUjMVXHTpsxZW7QHQzt&index=2)
- [Room](https://developer.android.com/training/data-storage/room), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Coroutines](https://developer.android.com/kotlin/coroutines) -> [RxJava](https://github.com/ReactiveX/RxJava)
- [Jetpack navigation](https://developer.android.com/guide/navigation) ->? [Cicerone](https://github.com/terrakok/Cicerone)
- [Jetpack Compose](https://developer.android.com/jetpack/compose) + [Material](m2.material.io)
- [Dagger2](https://dagger.dev/)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
### Функции
- Выбор точки по нажатию на карту
- Поиск точек и просмотр истории поиска
- Выбор двух точек и построение между ними маршрута
- Навигация в AR

## Ошибки и проблемы
- [ ] Проблемы в UI
- [ ] Плохой код
- [ ] Всё сделано по Google Samples
- [ ] Отсутствует git flow, все изменения в мастер/девелоп ветках
- [ ] Нет AR-режима
- [ ] Отсутсвует неблокирующая реализация A*
- [ ] Данные о точках и возможность построения маршрута

## Дальнейшее развитие
Исправление багов, недочетов и плохого кода. Дальнейшее развитие не предусмотрено
#№ Скриншоты
![MainScreen](https://sun7-8.userapi.com/impg/EugZ16kNHyjO5JskWrex2CqEt8VXlfDTxr0PoQ/I_dx-oJdQz0.jpg?size=972x2160&quality=96&sign=c0eb675e41fc4bacae3ca126201d9581&type=album) | ![SearchHistory](https://sun9-16.userapi.com/impg/G__haH5RVnfmdKmciHvU_CXherIjT66VnJ73bQ/7BVrBaBP4x8.jpg?size=972x2160&quality=96&sign=4c1b8aa62a4d31a2e5fd07dbf77a429c&type=album) | ![PickedLocation](https://sun7-6.userapi.com/impg/Fer1aRZ9k-8ozKfn7XQ2ydYnriSa_s6RhOQtmw/xkqxxGyySbE.jpg?size=972x2160&quality=96&sign=765afd4a168da0c93965b5496a2c2df4&type=album)
|:---------------------------------------------:|:--------------------------------------------:|:------------------------------------------:|
# English
Coming soon...
