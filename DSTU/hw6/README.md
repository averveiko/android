### Самостоятельное практическое задание "Хранение данных приложения в базе данных SqLite"

#### Задание:
Обновить приложение «Менеджер задач», из предыдущей лабораторной работы. Требуется сохранить данные о задачах в базе данных. При клике на кнопку «плюс» (Floating Action Button) должно открываться активити, используя которою можно добавить в базу данных новую задачу. При возращении на главную активность список задач должен содержать обновленные данные, для это можно использовать метод адаптера notifyDataSetChange().При снятии галочки в чекбоксе, задача должна быть удалена из списка и базы данных.

[Ссылка на github](https://github.com/averveiko/android/tree/main/DSTU/hw6)

Скриншоты выполненного задания:

Главное activity. При клике на задачу внизу появляется Toast с полной информацией о задаче

![Main screen](scr/scr01.png)

Экран добавления новой задачи

![Main screen](scr/scr02.png)

Задача добавлена в базу, список обновлен

![Main screen](scr/scr03.png)

При установке check box'а задача удаляется из списка и базы

![Main screen](scr/scr04.png)