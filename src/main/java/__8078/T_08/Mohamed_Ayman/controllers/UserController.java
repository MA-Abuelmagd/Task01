package __8078.T_08.Mohamed_Ayman.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import __8078.T_08.Mohamed_Ayman.models.Note;
import __8078.T_08.Mohamed_Ayman.models.User;
import __8078.T_08.Mohamed_Ayman.services.UserSevice;
import __8078.T_08.Mohamed_Ayman.services.NoteService;






@RestController
@RequestMapping("/users")
public class UserController {
    private final UserSevice userService;
    private final NoteService noteService;
    public UserController(UserSevice userService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public User getUserByID(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/search")
    public User searchByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }
    

    @GetMapping("/{id}/notes")
    public List<Note> getNotesByUserId(@PathVariable String id) {
        return noteService.getNotesByUserId(id);
    }
    

}
