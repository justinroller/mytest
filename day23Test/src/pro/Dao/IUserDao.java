package pro.Dao;

import pro.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> FindUsers();

    int Add(User user);

    int Del(String id);

    User Fine(int id);

    int update(User user);
}
