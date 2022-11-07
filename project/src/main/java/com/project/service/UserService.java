package com.project.service;

import com.project.dto.UserRequest;
import com.project.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public ResponseEntity<User> addUser(UserRequest req);

    public ResponseEntity<List<User>> getAllUsers();

    public ResponseEntity<User> getUserById(int id);

    public ResponseEntity<User> updateUser(int id, UserRequest req);

    public ResponseEntity<String> deleteUser(int id);
}
