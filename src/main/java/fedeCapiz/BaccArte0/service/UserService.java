package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.entities.User;
import fedeCapiz.BaccArte0.exceptions.NotFoundException;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public User findById(long id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }
}
