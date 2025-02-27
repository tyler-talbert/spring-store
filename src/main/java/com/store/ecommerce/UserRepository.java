package com.store.ecommerce;

public interface UserRepository {

    void save(User user);
    User findByEmail(String email);

}
