package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agents;

import java.util.List;


public interface AgentService {

    List<Agents> findAll();

    Agents findAgentById(long id);

    Agents findAgentByName(String name);

    void delete(long id);

    Agents save(Agents agent);

    Agents update(Agents agent, long id);
}
