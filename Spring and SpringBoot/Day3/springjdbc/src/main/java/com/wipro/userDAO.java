package com.wipro;

import java.util.List;

public interface userDAO {
    void saveUser(UserModel user);
    List getUser();
}