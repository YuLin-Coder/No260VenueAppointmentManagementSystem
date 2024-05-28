package project.dao;

import java.util.List;
import java.util.Map;

/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学习免费下 载
 * 供大家下载学习 参考
 */
public interface PublicDao {
    List<Map> queryForList(String sql);
    int update(String sql);
    Map queryForMap(String sql);
}