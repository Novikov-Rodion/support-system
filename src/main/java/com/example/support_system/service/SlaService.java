package com.example.support_system.service;

import com.example.support_system.model.SLA;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SlaService {
    private final Map<Long, SLA> slas = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<SLA> getAllSLAs() {
        return new ArrayList<>(slas.values());
    }

    public Optional<SLA> getSlaById(Long id) {
        return Optional.ofNullable(slas.get(id));
    }

    public Optional<SLA> getSlaByCategoryId(Long categoryId) {
        return slas.values().stream()
                .filter(sla -> sla.getCategory() != null && sla.getCategory().getId().equals(categoryId))
                .findFirst();
    }

    public SLA createSLA(SLA sla) {
        Long id = idCounter.getAndIncrement();
        sla.setId(id);
        slas.put(id, sla);
        return sla;
    }

    public Optional<SLA> updateSLA(Long id, SLA slaDetails) {
        if (slas.containsKey(id)) {
            SLA existingSLA = slas.get(id);
            existingSLA.setName(slaDetails.getName());
            existingSLA.setCategory(slaDetails.getCategory());
            existingSLA.setResponseTime(slaDetails.getResponseTime());
            existingSLA.setResolutionTime(slaDetails.getResolutionTime());
            return Optional.of(existingSLA);
        }
        return Optional.empty();
    }

    public boolean deleteSLA(Long id) {
        return slas.remove(id) != null;
    }
}