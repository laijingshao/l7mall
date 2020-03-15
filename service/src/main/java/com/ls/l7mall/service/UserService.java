package com.ls.l7mall.service;

import com.ls.l7mall.entity.User;
import com.ls.l7mall.global.ResponseEntity;

/**
 * @author laijs
 * @date 2020-3-14-19:59
 */
public interface UserService {
    public ResponseEntity<User> login(String username, String password);
    public ResponseEntity<String> checkValid(String str, String type);
    public ResponseEntity<String> register(User user);
    public ResponseEntity<String> getQuestion(String username);
    public ResponseEntity<String> checkAnswer(String username,String question,String answer);
    public ResponseEntity<String> forgetResetPassword(String username,String newPassword,String forgetToken);
    public ResponseEntity<String> resetPassword(String oldPassword,String newPassword,User user);
    public ResponseEntity<User> updateInformation(User user);
    public ResponseEntity<User> getInformation(Integer userId);
    public ResponseEntity getUserList(Integer pageSize,Integer pageNum);
}
