package pro.service;

import pro.domain.User;

import java.util.List;

public interface intService {
    List<User> FindAll();

    int AddUser(User user);

    int DelUser(String id);

    User Find(String id);

    int UpdateUser(User user);

    String FindAllbyJason();
}
