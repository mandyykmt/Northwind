package revision21.revision21.model;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {
    private Integer id;
    private Date order_date;
    private Date shipping_date;
    private String ship_name;
    private Double shipping_fee;
    private Integer customer_id;
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getShipping_date() {
        return shipping_date;
    }

    public void setShipping_date(Date shipping_date) {
        this.shipping_date = shipping_date;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public Double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(Double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", order_date=" + order_date + ", shipping_date=" + shipping_date + ", ship_name="
                + ship_name + ", shipping_fee=" + shipping_fee + ", customer_id=" + customer_id + ", customer="
                + customer + "]";
    }

    public Order() {
    }

    public Order(Integer id, Date order_date, Date shipping_date, String ship_name, Double shipping_fee,
            Integer customer_id, Customer customer) {
        this.id = id;
        this.order_date = order_date;
        this.shipping_date = shipping_date;
        this.ship_name = ship_name;
        this.shipping_fee = shipping_fee;
        this.customer_id = customer_id;
        this.customer = customer;
    }

    // select c.id as customer_id, c.company, o.id as order_id, o.order_date, o.shipping_date, o.ship_name, 
    // o.shipping_fee from customer c, orders o where c.id = o.customer_id and customer_id = ?
    public static Order populate(SqlRowSet rs) {
        Order order = new Order();
        Customer customer = new Customer();

        customer.setId(rs.getInt("customer_id"));
        customer.setCompany(rs.getString("company"));

        order.setCustomer(customer);
        order.setOrder_date(rs.getDate("order_date"));
        order.setShipping_date(rs.getDate("shipping_date"));
        order.setShip_name(rs.getString("ship_name"));
        order.setShipping_fee(rs.getDouble("shipping_fee"));
        order.setCustomer_id(rs.getInt("customer_id"));
        return order;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                    .add("customer_id", getCustomer().getId())
                    .add("company", getCustomer().getCompany())
                    .add("order_date", getOrder_date().toString())
                    .add("shipping_date", getShipping_date().toString())
                    .add("ship_name", getShip_name())
                    .add("shipping_fee", getShipping_fee())
                    .add("customer_id", getCustomer_id())
                    .build();
    }
}
