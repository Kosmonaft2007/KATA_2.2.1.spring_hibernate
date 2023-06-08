package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru", new Car("Mers", 350));
        User user2 = new User("User2", "Lastname2", "user2@mail.ru", new Car("Bexa", 725));
        User user3 = new User("User3", "Lastname3", "user3@mail.ru", new Car("Tazic", 2112));
        User user4 = new User("User4", "Lastname4", "user4@mail.ru", new Car("Cruzak", 200));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car: " + user.getUserCar());
            System.out.println();
        }

        List<User> userList = userService.listUsersByModelAndSeries("car1", 123);
        for (User user : userList) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        }
        context.close();
    }
}
