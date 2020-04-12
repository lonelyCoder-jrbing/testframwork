package redis.redisdemo.service;

import redis.redisdemo.pojo.User;

public interface UserService {

    User save(User user);

    void delete(int id);

    User get(Integer id);
}