package project.dao;
import java.util.List;
import project.model.Product;
public interface ProductDao {
    List<Product> queryForList(Product product);
    Integer countAll(Product product);
    int delete(Long id);
    Product getById(Long id);
    int update(Product product);
    int insert(Product product);
}
