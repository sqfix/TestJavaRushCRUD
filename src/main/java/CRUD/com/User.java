package CRUD.com;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Makc on 15.02.2017.
 */
@Entity
@Table(name = "User", schema = "UserDB")
public class User implements Serializable{
    private int id;
    private String name;
    private int age;
    private boolean flag;
    private Timestamp createdDate;


    public User() {
    }

    public User(int id, boolean flag, String name, int age) {
        this.id = id;
        this.flag = flag;
        this.name = name;
        this.age = age;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "isAdmin", nullable = false)
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "createdDate", nullable = false)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (age != that.age) return false;
        if (flag != that.flag) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (flag ? 1 : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", iAdmin=" + flag +
                ", createdDate=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdDate);
    }
}