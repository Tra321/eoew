package dao;

import model.Account;
import java.util.List;

public interface AccountDao {
    Account findById(int id);
    List<Account> findByUserId(int userId);
    List<Account> searchByAccountName(int userId, String keyword);
    List<Account> searchByCategory(int userId, String keyword);
    List<Account> searchByDescription(int userId, String keyword);
    boolean save(Account account);
    boolean update(Account account);
    boolean delete(int id);
} 