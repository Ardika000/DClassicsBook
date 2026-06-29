package com.example.dclassicsbook.data.repository;

import android.content.Context;

public final class AuthRepositoryProvider {

    private AuthRepositoryProvider() { }

    public static AuthRepository get(Context context) {
        return new RoomAuthRepository(context);
    }
}
