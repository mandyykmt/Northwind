package revision21.revision21.repository;

public class DBQueries {
    public static final String SELECT_ALL_CUSTOMERS = "select * from customer";
    public static final String SELECT_ALL_CUSTOMERS_LIMIT_OFFSET = "select * from customer limit ? offset ?"; 
    public static final String SELECT_CUSTOMER_BY_ID = "select * from customer where id = ?"; 
    public static final String SELECT_CUSTOMER_ID_ORDER = "select c.id as customer_id, c.company, o.id as order_id, o.order_date, o.shipping_date, o.ship_name, o.shipping_fee from customer c, orders o where c.id = o.customer_id and customer_id = ?";
}
