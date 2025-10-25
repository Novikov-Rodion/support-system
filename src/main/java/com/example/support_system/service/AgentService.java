package com.example.support_system.service;

import com.example.support_system.model.Agent;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AgentService {
    private final Map<Long, Agent> agents = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Agent> getAllAgents() {
        return new ArrayList<>(agents.values());
    }

    public List<Agent> getActiveAgents() {
        return agents.values().stream()
                .filter(Agent::isActive)
                .collect(Collectors.toList());
    }

    public Optional<Agent> getAgentById(Long id) {
        return Optional.ofNullable(agents.get(id));
    }

    public Agent createAgent(Agent agent) {
        Long id = idCounter.getAndIncrement();
        agent.setId(id);
        agents.put(id, agent);
        return agent;
    }

    public Optional<Agent> updateAgent(Long id, Agent agentDetails) {
        if (agents.containsKey(id)) {
            Agent existingAgent = agents.get(id);
            existingAgent.setName(agentDetails.getName());
            existingAgent.setEmail(agentDetails.getEmail());
            existingAgent.setDepartment(agentDetails.getDepartment());
            existingAgent.setActive(agentDetails.isActive());
            return Optional.of(existingAgent);
        }
        return Optional.empty();
    }

    public boolean deleteAgent(Long id) {
        return agents.remove(id) != null;
    }
}