package com.example.demo.db;

import com.example.demo.model.Note;
import com.example.demo.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByNotebook(Notebook notebook);

    @Query("select n from Note n inner join n.notebook b inner join b.user u where u.id=:userId")
    List<Note> findAllByUserId(@Param("userId") long userId);
}
