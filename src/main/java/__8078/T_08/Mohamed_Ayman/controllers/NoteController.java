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
import __8078.T_08.Mohamed_Ayman.services.NoteService;


@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.findAll();
    }
    
    @GetMapping("/{id}")
    public Note getNoteByID(@PathVariable String id) {
        return noteService.getNoteById(id);
    }

    @PostMapping()
    public Note createNote(@RequestBody Note newNote) {
        return noteService.createNote(newNote);
    }

    @GetMapping("/search")
    public Note getNotesByTitle(@RequestParam String title) {
        return noteService.getNoteByTitle(title);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note updatedNote) {
        return noteService.updateNote(id, updatedNote);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
    }

}
