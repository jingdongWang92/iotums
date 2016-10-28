package com.jcnetwork.iotums.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jcnetwork.iotums.entity.User;

/**
 * @author Jingdong Wang
 */

@Repository
public interface UserMapper {

    User findByGuid(String guid);
    
    List<String> findPrivilegesByUserId(int userId);
    
    void saveUser(User user);
    
    void savePrivilege(Map<String,Object> map);

    void updateUser(User user);

    User findByUsername(String username);

    List<User> findUsersByUsername(String username);

	User findUserByEmail(String email);
}