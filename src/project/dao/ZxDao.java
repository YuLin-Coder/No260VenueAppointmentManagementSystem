package project.dao;
import java.util.List;
import project.model.Zx;
public interface ZxDao {
    List<Zx> queryForList(Zx zx);
    Integer countAll(Zx zx);
    int delete(Long id);
    Zx getById(Long id);
    int update(Zx zx);
    int insert(Zx zx);
}
