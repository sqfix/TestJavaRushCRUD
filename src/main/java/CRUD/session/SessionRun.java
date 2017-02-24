package CRUD.session;

import CRUD.com.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makc on 15.02.2017.
 */
public class SessionRun {

    public void addUser(User user)
    {
        try (Session session = SessionCreate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public void deleteUser(Integer id)
    {
        try (Session session = SessionCreate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    public List<User> getAll(){
        try(Session session = SessionCreate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            return session.createCriteria(User.class).list();
        }
    }

    public User getOneById(Integer id)
    {
        try(Session session = SessionCreate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            return (User) session.get(User.class, id);
        }
    }

    public ArrayList<User> getOneByName(String thoseName) {
        ArrayList<User> users = new ArrayList<>();
        try(Session session = SessionCreate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for(User user : getAll())
            {
                if(user.getName().equals(thoseName))
                    users.add(user);
            }
            return users;
        }
    }

    // not used. As u can see..
    public void editUser(User user)
    {
        try(Session session = SessionCreate.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User existingUser = (User) getOneById(user.getId());
            existingUser.setName(user.getName());
            existingUser.setAge(user.getAge());
            existingUser.setFlag(user.isFlag());
            existingUser.setCreatedDate(user.getCreatedDate());
            session.save(existingUser);
        }
    }
}
