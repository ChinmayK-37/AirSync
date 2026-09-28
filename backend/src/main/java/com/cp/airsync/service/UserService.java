package com.cp.airsync.service;
import com.cp.airsync.repository.UserRepository;
import com.cp.airsync.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
public class UserService
{
private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

    public User save(User user){
            
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        
        return userRepository.save(user);

    }
    public User findUserById(Long id) 
        {
            return userRepository.findById(id).orElse(null);
        }
    public List<User> listAll()
        {
            return userRepository.findAll();
        }

    public User update(User user)
        {
            return userRepository.save(user);
        }

    public void deleteById(Long id)
        {
            userRepository.deleteById(id);
        }

    public void deleteAll()
        {
            userRepository.deleteAll();
        }

}