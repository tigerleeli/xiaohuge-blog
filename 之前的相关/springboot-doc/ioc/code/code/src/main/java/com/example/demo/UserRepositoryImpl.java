package com.example.demo;

@Component
class UserRepositoryImpl implements UserRepository {
    @Override
    public String getInfo() {
        return "Hello, World!";
    }
}
