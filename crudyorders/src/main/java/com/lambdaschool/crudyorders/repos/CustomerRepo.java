package com.lambdaschool.crudyorders.repos;

import com.lambdaschool.crudyorders.models.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customers, Long> {

    Customers findByCustname(String name);

    Customers findByAgent_Agentcode(long agentid);
}
