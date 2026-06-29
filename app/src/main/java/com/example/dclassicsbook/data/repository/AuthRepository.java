package com.example.dclassicsbook.data.repository;

import com.example.dclassicsbook.data.model.User;

// Account storage. Concrete impl is chosen in AuthRepositoryProvider.
public interface AuthRepository {

    boolean isRegistered();

    AuthResult register(String name, String email, String rawPassword);

    AuthResult login(String email, String rawPassword);

    User getUserByEmail(String email);
}
