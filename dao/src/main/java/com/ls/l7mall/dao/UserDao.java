package com.ls.l7mall.dao;

import com.ls.l7mall.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laijs
 * @date 2020-3-14-19:38
 */
@Repository("userDao")
public interface UserDao {
    // 校验用户名是否存在
    public int checkUsername(String username);
    // 根据用户名和用户密码查找用户
    public User selectToLogin(@Param("username") String username, @Param("password")String password);
    // 校验email是否存在
    public int checkEmail(String email);
    // 注册用户
    public int insert(User user);
    // 查找安全问题
    public String selectQuestion(String username);
    // 查找答案
    public int selectAnswer(@Param("username")String username, @Param("question")String question, @Param("answer")String answer);
    // 修改密码
    public int updatePassword(@Param("username")String username,@Param("password")String password);
    // 原密码是否正确
    public int checkPasword(@Param("userId") Integer userId,@Param("password") String password);
    // 校验新的email是否被使用
    public int checkEmailById(@Param("userId") Integer userId,@Param("email") String email);
    // 更新用户（忽略空信息）
    public int updateById(User user);
    // 获取用户信息
    public User selectById(Integer id);
    // 获取所有的用户信息
    public List<User> selectAll();
    
}
