package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.models.Orders;
import com.lambdaschool.crudyorders.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customersService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo custrepos;

    @Autowired
    private AgentService agentsService;

    @Override
    public List<Customers> findAllCustomers()
    {
        List<Customers> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findByCustomerName(String custname)
    {
        Customers customer = custrepos.findByCustname(custname);

        if (customer == null)
        {
            throw new EntityNotFoundException("Customer " + custname + " not found!");
        }

        return customer;
    }

    @Override
    public Customers findCustomersById(long id) throws EntityNotFoundException
    {
        return custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Transactional
    @Override
    public Customers save(Customers customer)
    {
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingpmt(customer.getOutstandingpmt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(agentsService.findAgentById(customer.getAgent().getAgentcode()));

        for (Orders o : customer.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrddescription()));
        }

        return custrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public Customers update(Customers customer, long id)
    {
        Customers currentCustomer = custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customer.getCustname() != null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustname() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.getOpeningamt()!= 0)
        {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.getReceiveamt() != 0)
        {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.getPaymentamt() != 0)
        {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.getOutstandingpmt() != 0)
        {
            currentCustomer.setOutstandingpmt(customer.getOutstandingpmt());
        }

        if (customer.getPhone() != null)
        {
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.getAgent() !=null)
        {
            currentCustomer.setAgent(customer.getAgent());
        }

        // adds new orders
        if (customer.getOrders().size() > 0)
        {
            for (Orders o : customer.getOrders())
            {
                currentCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), currentCustomer, o.getOrddescription()));
            }
        }

        return custrepos.save(currentCustomer);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}
