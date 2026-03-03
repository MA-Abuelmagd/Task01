package __8078.T_08.Mohamed_Ayman.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import __8078.T_08.Mohamed_Ayman.models.Note;
import __8078.T_08.Mohamed_Ayman.repositories.NoteRepository;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note getNoteById(String id) {
        Optional<Note> result = noteRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }
        return result.get();
    }
    public List<Note> getNotesByUserId(String userId) {
        List<Note> result = noteRepository.findByUserId(userId);
        if (result == null || result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No notes found for user with id: " + userId);
        }
        return result;
    }
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }
    public Note getNoteByTitle(String title) {
        Optional<Note> result = noteRepository.findByTitle(title);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with title: " + title);
        }
        return result.get();
    }
    public Note updateNote(String id, Note updatedNote) {
        Optional<Note> result = noteRepository.update(id, updatedNote);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with id: " + id);
        }
        return result.get();
    }
    public void deleteNote(String id) {
        boolean deleted = noteRepository.deleteById(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with id: " + id);
        }
    }
    
}
