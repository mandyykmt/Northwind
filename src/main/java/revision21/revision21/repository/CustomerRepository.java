package revision21.revision21.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import revision21.revision21.model.Customer;
import revision21.revision21.model.Order;

import static revision21.revision21.repository.DBQueries.*;

import java.util.ArrayList;
import java.util.List; 

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate template;
    
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>(); 
        SqlRowSet rs = template.queryForRowSet(SELECT_ALL_CUSTOMERS);
        while(rs.next()) {
            customers.add(Customer.populate(rs));
        }
        return customers; 
    }

    public List<Customer> getAllCustomersLimitOffset(Integer limit, Integer offset) {
        List<Customer> customers = new ArrayList<Customer>(); 
        SqlRowSet rs = template.queryForRowSet(SELECT_ALL_CUSTOMERS_LIMIT_OFFSET, limit, offset);
        while(rs.next()) {
            customers.add(Customer.populate(rs));
        }
        return customers; 
    }

    public List<Customer> getCustomerById(Integer id) {
        // Customer customer = new Customer(); 
        // SqlRowSet rs = template.queryForRowSet(SELECT_CUSTOMER_BY_ID, id); 
        // customer = customer.populate(rs); 
        // return customer; 
        List<Customer> customer = new ArrayList<Customer>(); 
        SqlRowSet rs = template.queryForRowSet(SELECT_CUSTOMER_BY_ID, id);
        while(rs.next()) {
            customer.add(Customer.populate(rs));
        }
        return customer; 
    }

    public List<Order> getOrdersByCustomerId(Integer id) {
        List<Order> orders = new ArrayList<Order>(); 
        SqlRowSet rs = template.queryForRowSet(SELECT_CUSTOMER_ID_ORDER, id);
        while(rs.next()) {
            orders.add(Order.populate(rs));
        }
        return orders; 
    }
}
