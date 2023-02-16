ДЗ 21. Робота з базами даних у Java

1. Створити таблицю Homework. Ця таблиця складається з атрибутів: id, name, description
2. Створити таблицю Lesson. Ця таблиця складається з атрибутів: id, name, updatedAt, homework_id (пов'язано з таблицею Homework)
3. Реалізувати клас DataBaseConnection.
   - налаштувати підключення до БД
   - метод getConnection() - повертає нове з'єднання з БД
   - метод close(Connection) - закриває передане з'єднання
4. Реалізувати клас Lesson, який містить такі властивості: id, name, homework
5. Реалізувати клас Homework, який містить такі властивості: id, name, description
6. Реалізувати клас LessonDao
   - метод додавання уроку
   - метод видалення уроку
   - метод отримання всіх уроків
   - метод отримання уроку за ID
7. Клас LessonDao взаємодіє з БД і повертає об'єкти типу Lesson