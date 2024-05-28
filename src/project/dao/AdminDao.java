package project.dao;

import java.util.List;

import project.model.Admin;


/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文 学习免费下载
 * 供大家 下载学习参考
 */
public interface AdminDao {
    List<Admin> isAdminExists(Admin admin);
    
    Admin getById(Integer id);
}