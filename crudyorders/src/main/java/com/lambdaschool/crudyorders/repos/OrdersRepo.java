package com.lambdaschool.crudyorders.repos;

import com.lambdaschool.crudyorders.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepo extends CrudRepository<Orders, Long> {

}

