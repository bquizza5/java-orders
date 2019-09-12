package com.lambdaschool.crudyorders.repos;

import com.lambdaschool.crudyorders.models.Agents;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepo extends CrudRepository<Agents, Long> {

    Agents findByAgentname(String name);



}
