package org.virtu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.virtu.domain.Doc;
import org.virtu.dto.CreateDocDTO;
import org.virtu.dto.UpdateDocDTO;
import org.virtu.repository.DocRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocRepository docRepository;

    @GetMapping
    public ResponseEntity<List> getList() {
        List<Doc> all = docRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateDocDTO> get() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity create(CreateDocDTO dto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(UpdateDocDTO dto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity update() {
        return ResponseEntity.ok().build();
    }

}
