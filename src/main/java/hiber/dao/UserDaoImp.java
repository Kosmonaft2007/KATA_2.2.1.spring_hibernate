package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUser(String carModel, int carSeries) {
        return null;
    }

    @Override
    public List<User> getUserByCar(String model, int series) {

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where userCar.model = :model and userCar.series = :series", User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList();
    }
}
