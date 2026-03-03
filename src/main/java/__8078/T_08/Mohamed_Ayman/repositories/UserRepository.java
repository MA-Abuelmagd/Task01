package __8078.T_08.Mohamed_Ayman.repositories;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import __8078.T_08.Mohamed_Ayman.models.User;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Repository
public class UserRepository {
    private List<User> users;

    public UserRepository() {
        InputStream inputStream = getClass().getResourceAsStream("/users.json");

        if (inputStream == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to read users.json");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        this.users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
    }
    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> save(User user) {
        if (user.getId() == null) {
            user = new User(user.getUsername(), user.getEmail());
        }
        users.add(user);
        new ObjectMapper().writeValue(new java.io.File(getClass().getResource("/users.json").getFile()), users);
        return Optional.of(user);
    }

    public Optional<User> update(String id, User updated) {
        Optional<User> existingUserOpt = findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUsername(updated.getUsername());
            existingUser.setEmail(updated.getEmail());
            return Optional.of(existingUser);
        }
        return Optional.empty();
    }

    public boolean deleteById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                new ObjectMapper().writeValue(new java.io.File(getClass().getResource("/users.json").getFile()), users);
                return true;
            }
        }
        return false;
    }
    
}
