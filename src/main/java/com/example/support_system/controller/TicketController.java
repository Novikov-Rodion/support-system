package com.example.support_system.controller;

import com.example.support_system.model.Ticket;
import com.example.support_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        Optional<Ticket> updatedTicket = ticketService.updateTicket(id, ticketDetails);
        return updatedTicket.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        if (ticketService.deleteTicket(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{ticketId}/assign/{agentId}")
    public ResponseEntity<Ticket> assignTicket(@PathVariable Long ticketId, @PathVariable Long agentId) {
        Optional<Ticket> assignedTicket = ticketService.assignTicket(ticketId, agentId);
        return assignedTicket.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<Ticket> resolveTicket(@PathVariable Long id, @RequestBody String solution) {
        Optional<Ticket> resolvedTicket = ticketService.resolveTicket(id, solution);
        return resolvedTicket.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Ticket> closeTicket(@PathVariable Long id) {
        Optional<Ticket> closedTicket = ticketService.closeTicket(id);
        return closedTicket.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<Ticket> getTicketsByStatus(@PathVariable Ticket.TicketStatus status) {
        return ticketService.getTicketsByStatus(status);
    }

    @GetMapping("/agent/{agentId}")
    public List<Ticket> getTicketsByAgent(@PathVariable Long agentId) {
        return ticketService.getTicketsByAgent(agentId);
    }
}