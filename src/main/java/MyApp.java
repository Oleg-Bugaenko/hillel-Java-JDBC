import entities.Homework;
import entities.Lesson;
import repository.LessonDao;
import repository.LessonsRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyApp {
    public final static String url = "jdbc:mysql://localhost:3306/school";
    public final static String username = "hillel";
    public final static String password = "hillel";


    public static void main(String[] args) {

        Connection connection = DataBaseConnection.getConnection(url, username, password);
        LessonsRepository repository = new LessonDao(connection);

    //Створення таблиць homework та lesson
       createTable(connection);

    //Створення списку уроків та завдань
        List<Lesson> lessons = createLessonList();

    //Додавання даних в базу
        for (Lesson lesson: lessons) {
            repository.addLesson(lesson);
        }

    //Отримання всіх уроків з бази даних
        List<Lesson> allLessons = repository.getAllLessons();
        allLessons.forEach(System.out::println);

    //Отримання уроку за id
        System.out.println(repository.getLesson(3));

    //Видалення уроку за вказаним id
        repository.deleteLesson(3);

    // Видалення уроку
        Lesson lesson = new Lesson("Математика",
                new Homework("Геометрія", "Вправи 88-98"));
        repository.deleteLesson(lesson);



        DataBaseConnection.close(connection);
    }

    public static void createTable(Connection connection) {
        if (connection != null) {
            String homeworkTableQuery = "CREATE TABLE homework (" +
                    " id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    " name VARCHAR(255) NOT NULL," +
                    " description TEXT);";

            String lessonTableQuery = "CREATE TABLE lesson (" +
                    " id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    " name VARCHAR(255) NOT NULL," +
                    " homework_id INT REFERENCES homework(id) ON DELETE CASCADE);";

            try {
                Statement statement = connection.createStatement();

                boolean homework = false, lesson = false;
                ResultSet resultSet = statement.executeQuery("SHOW TABLES;");
               while (resultSet.next()) {
                    String string = resultSet.getString(1);
                    if (string.equals("homework")) homework=true;
                    if (string.equals("lesson")) lesson=true;
                }
               if (!homework) statement.executeLargeUpdate(homeworkTableQuery);
               if (!lesson) statement.executeLargeUpdate(lessonTableQuery);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static List<Lesson> createLessonList() {
        List<Lesson> lessons = new ArrayList<>();

        Lesson lesson1 = new Lesson("Математика",
                new Homework("Алгебра", "Вправи 115-120"));

        Lesson lesson2 = new Lesson("Математика",
                new Homework("Геометрія", "Вправи 88-98"));

        Lesson lesson3 = new Lesson("Фізика",
                new Homework("Механіка", "Вправи 15-25"));

        Lesson lesson4 = new Lesson("Біологія",
                new Homework("Будова клітини", "Параграф 28, 29"));

        lessons.add(lesson1);
        lessons.add(lesson2);
        lessons.add(lesson3);
        lessons.add(lesson4);

        return lessons;
    }








}
