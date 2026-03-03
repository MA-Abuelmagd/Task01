package __8078.T_08.Mohamed_Ayman.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import __8078.T_08.Mohamed_Ayman.models.User;
import __8078.T_08.Mohamed_Ayman.repositories.UserRepository;

@Service
public class UserSevice {

    private final UserRepository userRepository;
    public UserSevice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }
        else {
            return result.get();
        }
    }
    
    public User getUserByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username);
        }
        return result.get();
    }

    public User createUser(User user) {
        return userRepository.save(user).get();
    }

    public User updateUser(String id, User updatedUser) {
        User result = userRepository.update(id, updatedUser).get();
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }
        return result;
    }

    public void deleteUser (String id) {
        boolean deleted = userRepository.deleteById(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }
    }
}
