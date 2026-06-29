package com.example.dclassicsbook.data.repository;

import android.content.Context;

import com.example.dclassicsbook.data.db.AppDatabase;
import com.example.dclassicsbook.data.db.UserDao;
import com.example.dclassicsbook.data.model.User;
import com.example.dclassicsbook.util.PasswordHasher;

public class RoomAuthRepository implements AuthRepository {

    private final UserDao userDao;

    public RoomAuthRepository(Context context) {
        this.userDao = AppDatabase.getInstance(context).userDao();
    }

    @Override
    public boolean isRegistered() {
        return userDao.count() > 0;
    }

    @Override
    public AuthResult register(String name, String email, String rawPassword) {
        String key = email.trim();
        if (userDao.findByEmail(key) != null) {
            return AuthResult.failure("Email is already registered. Please sign in.");
        }
        userDao.insert(new User(key, name, PasswordHasher.hash(rawPassword)));
        return AuthResult.success();
    }

    @Override
    public AuthResult login(String email, String rawPassword) {
        User user = userDao.findByEmail(email.trim());
        if (user == null) {
            return AuthResult.failure("Account not found. Please register first.");
        }
        if (!user.getPasswordHash().equals(PasswordHasher.hash(rawPassword))) {
            return AuthResult.failure("Incorrect email or password.");
        }
        return AuthResult.success();
    }

    @Override
    public User getUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        return userDao.findByEmail(email.trim());
    }
}
