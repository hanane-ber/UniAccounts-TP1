package repository;

import model.StudentAccount;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static AccountRepository instance;
    private final List<StudentAccount> accounts;

    private AccountRepository() {
        accounts = new ArrayList<>();
    }

    public static synchronized AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public boolean addAccount(StudentAccount account) {
        for (StudentAccount acc : accounts) {
            if (acc.getId().equals(account.getId())) {
                System.out.println("خطأ: رقم التسجيل " + account.getId() + " موجود مسبقاً!");
                return false;
            }
        }
        accounts.add(account);
        return true;
    }

    public List<StudentAccount> getAllAccounts() {
        return accounts;
    }
}