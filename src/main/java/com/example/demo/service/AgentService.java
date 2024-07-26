package com.example.demo.service;

import com.example.demo.model.Agent;
import com.example.demo.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentRepository;

    public List<Agent> findAllAgents() {
        return agentRepository.findAll();
    }

    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Agent findAgentById(Integer id) {
        return agentRepository.findById(id).orElse(null);
    }

    public void deleteAgent(Integer id) {
        agentRepository.deleteById(id);
    }
}
