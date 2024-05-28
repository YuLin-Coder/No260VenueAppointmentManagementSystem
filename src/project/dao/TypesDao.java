package project.dao;
import java.util.List;
import project.model.Types;
public interface TypesDao {
    List<Types> queryForList(Types types);
    Integer countAll(Types types);
    int delete(Long id);
    Types getById(Long id);
    int update(Types types);
    int insert(Types types);
}
