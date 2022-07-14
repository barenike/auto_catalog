package com.example.auto_catalog.controller;

import com.example.auto_catalog.exceptions.AutoAlreadyExistException;
import com.example.auto_catalog.exceptions.NotEnoughDataForStatisticsDump;
import com.example.auto_catalog.infrastructure.AutoCreationRequest;
import com.example.auto_catalog.model.entity.AutoEntity;
import com.example.auto_catalog.model.service.AutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AutoController {

    private final AutoService autoService;

    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping("/autos")
    public ResponseEntity<?> getAutos() {
        try {
            List<AutoEntity> autos = autoService.readAll();
            return new ResponseEntity<>(autos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/autos")
    public ResponseEntity<?> createAuto(@RequestBody AutoCreationRequest autoCreationRequest) {
        try {
            autoService.create(autoCreationRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AutoAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/autos/{id}")
    public ResponseEntity<?> deleteAuto(@PathVariable(name = "id") UUID id) {
        try {
            final boolean isDeleted = autoService.delete(id);
            return isDeleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> getStatistics() {
        try {
            return new ResponseEntity<>(autoService.getStatistics(), HttpStatus.OK);
        } catch (NotEnoughDataForStatisticsDump e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
