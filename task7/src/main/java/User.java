import javax.swing.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
public class User implements Serializable {

    private static int counter;
    private int id;
    static {
        counter = 0;
    }
    private String name, surname;
    private ImageIcon img;
    private List<String> messages;
    private Date birthDate;
    private String city;
    private List<User> friends;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        messages = new LinkedList<>();
        friends = new LinkedList<>();
        counter++;
        id = counter;
    }

    public User() {
        messages = new LinkedList<>();
        friends = new LinkedList<>();
        id++;
        id = counter;
    }


    public void addFriend(User user){
        friends.add(user);
    }

    @XmlElement
    public List<User> getFriends() {
        return friends;
    }
    @XmlElement
    public List<String> getMessages() {
        return messages;
    }

    public void sendMessage(User user, String message) {
        user.getMessages().add(message);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
