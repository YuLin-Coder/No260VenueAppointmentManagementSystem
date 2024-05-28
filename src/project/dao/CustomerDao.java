package project.dao;
import java.util.List;
import project.model.Customer;
public interface CustomerDao {
    List<Customer> queryForList(Customer customer);
    Integer countAll(Customer customer);
    int delete(Long id);
    Customer getById(Long id);
    int update(Customer customer);
    int insert(Customer customer);
}
