package com.example.support_system.controller;

import com.example.support_system.model.SLA;
import com.example.support_system.service.SlaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/slas")
public class SlaController {

    @Autowired
    private SlaService slaService;

    @GetMapping
    public List<SLA> getAllSLAs() {
        return slaService.getAllSLAs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SLA> getSlaById(@PathVariable Long id) {
        Optional<SLA> sla = slaService.getSlaById(id);
        return sla.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<SLA> getSlaByCategory(@PathVariable Long categoryId) {
        Optional<SLA> sla = slaService.getSlaByCategoryId(categoryId);
        return sla.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SLA createSLA(@RequestBody SLA sla) {
        return slaService.createSLA(sla);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SLA> updateSLA(@PathVariable Long id, @RequestBody SLA slaDetails) {
        Optional<SLA> updatedSLA = slaService.updateSLA(id, slaDetails);
        return updatedSLA.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSLA(@PathVariable Long id) {
        if (slaService.deleteSLA(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}