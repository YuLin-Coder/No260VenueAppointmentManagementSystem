package project.dao;
import java.util.List;
import project.model.Productlist;
public interface ProductlistDao {
    List<Productlist> queryForList(Productlist productlist);
    Integer countAll(Productlist productlist);
    int delete(Long id);
    Productlist getById(Long id);
    int update(Productlist productlist);
    int insert(Productlist productlist);
}
