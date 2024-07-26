package com.example.demo.controller;


import com.example.demo.model.Agent;
import com.example.demo.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.findAllAgents();
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.saveAgent(agent);
    }

    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable Integer id) {
        return agentService.findAgentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAgent(@PathVariable Integer id) {
        agentService.deleteAgent(id);
    }
}
