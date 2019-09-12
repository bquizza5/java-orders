package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Agents;
import com.lambdaschool.crudyorders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentController {

    @Autowired
    private AgentService agentService;

    // http://localhost:2019/agents/agents

    @GetMapping(value = "/agents/agents", produces = {"application/json"})
    public ResponseEntity<?> listAllAgents() {

        List<Agents> myAgents = agentService.findAll();
        return new ResponseEntity<>(myAgents, HttpStatus.OK);
    }

    @DeleteMapping(value = "/agent/{id}")
    public ResponseEntity<?> deleteAgentById(
            @PathVariable
                    long id)
    {
        agentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
