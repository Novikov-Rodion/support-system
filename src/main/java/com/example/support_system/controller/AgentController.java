package com.example.support_system.controller;

import com.example.support_system.model.Agent;
import com.example.support_system.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    @GetMapping("/active")
    public List<Agent> getActiveAgents() {
        return agentService.getActiveAgents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable Long id) {
        Optional<Agent> agent = agentService.getAgentById(id);
        return agent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.createAgent(agent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable Long id, @RequestBody Agent agentDetails) {
        Optional<Agent> updatedAgent = agentService.updateAgent(id, agentDetails);
        return updatedAgent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        if (agentService.deleteAgent(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}