package com.example.support_system.service;

import com.example.support_system.model.Ticket;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final Map<Long, Ticket> tickets = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets.values());
    }

    public Optional<Ticket> getTicketById(Long id) {
        return Optional.ofNullable(tickets.get(id));
    }

    public List<Ticket> getTicketsByStatus(Ticket.TicketStatus status) {
        return tickets.values().stream()
                .filter(ticket -> ticket.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Ticket> getTicketsByAgent(Long agentId) {
        return tickets.values().stream()
                .filter(ticket -> ticket.getAssignedAgent() != null &&
                        ticket.getAssignedAgent().getId().equals(agentId))
                .collect(Collectors.toList());
    }

    public Ticket createTicket(Ticket ticket) {
        Long id = idCounter.getAndIncrement();
        ticket.setId(id);
        tickets.put(id, ticket);
        return ticket;
    }

    public Optional<Ticket> updateTicket(Long id, Ticket ticketDetails) {
        if (tickets.containsKey(id)) {
            Ticket existingTicket = tickets.get(id);
            existingTicket.setTitle(ticketDetails.getTitle());
            existingTicket.setDescription(ticketDetails.getDescription());
            return Optional.of(existingTicket);
        }
        return Optional.empty();
    }

    public boolean deleteTicket(Long id) {
        return tickets.remove(id) != null;
    }

    public Optional<Ticket> assignTicket(Long ticketId, Long agentId) {
        Ticket ticket = tickets.get(ticketId);
        if (ticket != null) {
            ticket.setStatus(Ticket.TicketStatus.IN_PROGRESS);
            return Optional.of(ticket);
        }
        return Optional.empty();
    }

    public Optional<Ticket> resolveTicket(Long ticketId, String solution) {
        Ticket ticket = tickets.get(ticketId);
        if (ticket != null) {
            ticket.setSolution(solution);
            ticket.setStatus(Ticket.TicketStatus.RESOLVED);
            return Optional.of(ticket);
        }
        return Optional.empty();
    }

    public Optional<Ticket> closeTicket(Long ticketId) {
        Ticket ticket = tickets.get(ticketId);
        if (ticket != null && ticket.getStatus() == Ticket.TicketStatus.RESOLVED) {
            ticket.setStatus(Ticket.TicketStatus.CLOSED);
            return Optional.of(ticket);
        }
        return Optional.empty();
    }
}