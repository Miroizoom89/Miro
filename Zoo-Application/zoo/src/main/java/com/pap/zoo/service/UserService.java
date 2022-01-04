package com.pap.zoo.service;

import com.pap.zoo.entity.Task;
import com.pap.zoo.entity.User;
import com.pap.zoo.repository.TaskRepository;
import com.pap.zoo.repository.UserRepository;
import org.hibernate.tool.schema.spi.SchemaManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    //PUSH methods + validation

    public Pair<Boolean,String> saveUser(User user) {
        try {
            if(!(user.getRole().equals("ADMIN") || user.getRole().equals("USER"))){
                throw new SchemaManagementException("Podana rola użytownika jest niepoprawna.");
            }
            user.setRole(user.getRole().toUpperCase());
            user.setPass(encoder.encode(user.getPass()));
            userRepository.save(user);
            return Pair.of(true,"");
        } catch (Exception ex) {
            return Pair.of(false,ex.getMessage());
        }
    }

    //GET methods

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Pair<Boolean, String> isValidUser(String login, String pass) {
        try {
            return Pair.of(encoder.matches(pass, userRepository.findByLogin(login).getPass()), userRepository.findByLogin(login).getRole());
        } catch(Exception e) {
            return Pair.of(false, "");
        }
    }

    //UPDATE methods + validation

    public Pair<Boolean,String> updateUser(User user) {
        try {
            if(userRepository.findById(user.getId()).isEmpty()) {
                throw new SchemaManagementException("Nie odnalezionono takiego użytkownika.");
            }
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            assert existingUser != null;
            existingUser.setLogin(user.getLogin());
            existingUser.setPass(encoder.encode(user.getPass()));
            if(!existingUser.getRole().equals("ADMIN")) {
                if(existingUser.getRole().equals("USER") && user.getRole().equals("ADMIN")) {
                    throw new SchemaManagementException("Nie można zmienić USER na ADMIN.");
                }
                else if(!existingUser.getRole().equals("USER")) {
                    throw new SchemaManagementException("Niepoprawna rola.");
                }
            }
            existingUser.setRole(user.getRole().toUpperCase());
            userRepository.save(existingUser);
            return Pair.of(true,"");
        } catch(Exception ex) {
            return Pair.of(false,ex.getMessage());
        }
    }

    //DELETE methods + validation

    public Pair<Boolean,String> deleteUser(int id) {
        try {
            // if user deleted then change zookeeper filed to null
            for (Task task: taskRepository.findByZooKeeperId(Objects.requireNonNull(userRepository.findById(id).orElse(null)).getId())) {
                task.setZooKeeper(null);
                task.setLogin("");
            }
            userRepository.deleteById(id);
            return Pair.of(true,"");
        } catch(Exception ex) {
            return Pair.of(false,"Użytkownik o takim ID nie został znaleziony.");
        }
    }
}
