package com.diakogiannis.uel.moviebook.service;

import com.diakogiannis.uel.moviebook.exceptions.UserExistsException;
import com.diakogiannis.uel.moviebook.model.dto.UserDetailsDTO;
import com.diakogiannis.uel.moviebook.model.entity.users.Users;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    Users registerUser(Users users) throws UserExistsException;

    UserDetailsDTO getUserDetails(String username);

    Users findUserByUsername(String username);
}
