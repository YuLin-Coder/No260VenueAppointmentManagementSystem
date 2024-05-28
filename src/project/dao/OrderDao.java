package project.dao;
import java.util.List;
import project.model.Order;
public interface OrderDao {
    List<Order> queryForList(Order order);
    Integer countAll(Order order);
    int delete(Long id);
    Order getById(Long id);
    int update(Order order);
    int insert(Order order);
}
