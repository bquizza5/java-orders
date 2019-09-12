package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agents;
import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.repos.AgentRepo;
import com.lambdaschool.crudyorders.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Transactional
@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepo agentrepos;

    @Autowired
    private CustomerRepo custrepos;

    @Override
    public List<Agents> findAll() {

        List<Agents> rtnList = new ArrayList<>();
        agentrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Agents findAgentById(long id) {
        return agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id " + id));
    }

    @Override
    public Agents findAgentByName(String name) {
        Agents agent = agentrepos.findByAgentname(name);

        if (agent == null)
        {
            throw new EntityNotFoundException("Restaurant Not Found " + name);
        }
        return agent;
    }

    @Override
    public void delete(long id) {

        if (agentrepos.findById(id).isPresent())
        {
            agentrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Id " + id);
        }
    }

    @Transactional
    @Override
    public Agents save(Agents agent) {

        Agents newAgent = new Agents();

        newAgent.setAgentname(agent.getAgentname());
        newAgent.setWorkingarea(agent.getWorkingarea());
        newAgent.setCommission(agent.getCommission());
        newAgent.setPhone(agent.getPhone());
        newAgent.setCountry(agent.getCountry());

        for (Customers c : agent.getCustomers())
        {
            newAgent.getCustomers().add(new Customers(c.getCustname(), c.getCustcity(), c.getWorkingarea(), c.getCustcountry(), c.getGrade(), c.getOpeningamt(), c.getReceiveamt(), c.getPaymentamt(), c.getOutstandingpmt(), c.getPhone(), c.getAgent()));
        }

        return agentrepos.save(newAgent);


    }

    @Transactional
    @Override
    public Agents update(Agents agent, long id) {

        Agents currentAgent = agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (agent.getAgentname() != null)
        {
            currentAgent.setAgentname(agent.getAgentname());
        }

        if (agent.getWorkingarea() != null)
        {
            currentAgent.setWorkingarea(agent.getWorkingarea());
        }

        if (agent.getCommission() != 0)
        {
            currentAgent.setCommission(agent.getCommission());
        }

        if (agent.getPhone() != null)
        {
            currentAgent.setPhone(agent.getPhone());
        }

        if (agent.getCountry() != null)
        {
            currentAgent.setCountry(agent.getCountry());
        }

        return agentrepos.save(currentAgent);
    }
}
