package revision21.revision21.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer {
    private Integer id;
    private String company;
    private String last_name;
    private String first_name;
    private String email_address;
    private String job_title;
    private String business_phone;
    private String home_phone;
    private String mobile_phone;
    private String address;
    private String state_province;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getEmail_address() {
        return email_address;
    }
    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }
    public String getJob_title() {
        return job_title;
    }
    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
    public String getBusiness_phone() {
        return business_phone;
    }
    public void setBusiness_phone(String business_phone) {
        this.business_phone = business_phone;
    }
    public String getHome_phone() {
        return home_phone;
    }
    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }
    public String getMobile_phone() {
        return mobile_phone;
    }
    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getState_province() {
        return state_province;
    }
    public void setState_province(String state_province) {
        this.state_province = state_province;
    }
    public Customer() {
    }
    public Customer(Integer id, String company, String last_name, String first_name, String email_address,
            String job_title, String business_phone, String home_phone, String mobile_phone, String address,
            String state_province) {
        this.id = id;
        this.company = company;
        this.last_name = last_name;
        this.first_name = first_name;
        this.email_address = email_address;
        this.job_title = job_title;
        this.business_phone = business_phone;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
        this.address = address;
        this.state_province = state_province;
    }
    @Override
    public String toString() {
        return "Customer [id=" + id + ", company=" + company + ", last_name=" + last_name + ", first_name=" + first_name
                + ", email_address=" + email_address + ", job_title=" + job_title + ", business_phone=" + business_phone
                + ", home_phone=" + home_phone + ", mobile_phone=" + mobile_phone + ", address=" + address
                + ", state_province=" + state_province + "]";
    } 

    public static Customer populate(SqlRowSet rs) {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setCompany(rs.getString("company"));
        customer.setLast_name(rs.getString("last_name"));
        customer.setFirst_name(rs.getString("first_name"));
        customer.setEmail_address(rs.getString("email_address"));
        customer.setJob_title(rs.getString("job_title"));
        customer.setBusiness_phone(rs.getString("business_phone"));
        customer.setHome_phone(rs.getString("home_phone"));
        customer.setMobile_phone(rs.getString("mobile_phone"));
        customer.setAddress(rs.getString("address"));
        customer.setState_province(rs.getString("state_province"));
        return customer; 
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                    .add("id", getId())
                    .add("company",getCompany())
                    .add("last_name", getLast_name())
                    .add("first_name", getFirst_name())
                    .add("email_address", getEmail_address())
                    .add("job_title", getJob_title())
                    .add("business_phone", getBusiness_phone())
                    .add("home_phone", getHome_phone())
                    .add("mobile_phone", getMobile_phone())
                    .add("address", getAddress())
                    .add("state_province", getState_province())
                    .build();
    }
}