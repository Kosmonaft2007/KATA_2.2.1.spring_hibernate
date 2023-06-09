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
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("User1", "Lastname1", "user11@mail.ru", new Car("Mers", 350)));
        userService.add(new User("User2", "Lastname2", "user12@mail.ru", new Car("Bexa", 725)));
        userService.add(new User("User3", "Lastname3", "user13@mail.ru", new Car("Tazic", 2112)));
        userService.add(new User("User4", "Lastname4", "user14@mail.ru", new Car("Cruzak", 200)));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        System.out.println("The car belongs to the user with the data: " + userService.getUserByCar("Mers", 350));

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getUserCar());
        }
        System.out.println(userService.getUserByCar("Mers", 350));
        try {
            userService.getUserByCar("Kamaz", 100);
        } catch (NoResultException e) {
            System.out.println("User not found");
        }

        context.close();
    }
}
