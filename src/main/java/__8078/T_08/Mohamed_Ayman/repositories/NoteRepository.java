package __8078.T_08.Mohamed_Ayman.repositories;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import __8078.T_08.Mohamed_Ayman.models.Note;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Repository
public class NoteRepository {
    private List<Note> notes;
    
    public NoteRepository() {
        // Initialize the notes list, possibly by reading from a file or database
        InputStream inputStream = getClass().getResourceAsStream("/notes.json");

        if (inputStream == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to read notes.json");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        this.notes = objectMapper.readValue(inputStream, new TypeReference<List<Note>>() {});
    }

    public List<Note> findAll() {
        return notes;
    }
    
    public Optional<Note> findById(String id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                return Optional.of(note);
            }
        }
        return Optional.empty();
    }

    public List<Note> findByUserId (String userId) {
        List<Note> userNotes = new java.util.ArrayList<>();
        for (Note note : notes) {
            if (note.getUserId().equals(userId)) {
                userNotes.add(note);
            }
        }
        return userNotes;
    }

    public Optional<Note> findByTitle(String title) {
        for (Note note : notes) {
            if (note.getTitle().equalsIgnoreCase(title)) {
                return Optional.of(note);
            }
        }
        return Optional.empty();
    }
    public Note save(Note note) {
        if (note.getId() == null) {
            note = new Note(note.getTitle(), note.getContent(), note.getUserId());
        }
        notes.add(note);
        new ObjectMapper().writeValue(new java.io.File(getClass().getResource("/notes.json").getFile()), notes);
        return note;
    }

    public Optional<Note> update(String id, Note updatedNote) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(id)) {
                updatedNote.setId(id);
                notes.set(i, updatedNote);
                new ObjectMapper().writeValue(new java.io.File(getClass().getResource("/notes.json").getFile()), notes);
                return Optional.of(updatedNote);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(String id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                notes.remove(note);
                new ObjectMapper().writeValue(new java.io.File(getClass().getResource("/notes.json").getFile()), notes);
                return true;
            }
        }
        return false;
    }
    
}
