package prototype;

import model.StudentAccount;
import java.util.HashMap;
import java.util.Map;

public class AccountTemplateRegistry {
    private final Map<String, StudentAccount> templates = new HashMap<>();

    public void registerTemplate(String key, StudentAccount template) {
        templates.put(key, template);
    }

    public StudentAccount createFromTemplate(String key, String newId, String newName, String newEmail) {
        StudentAccount template = templates.get(key);
        if (template == null) {
            throw new IllegalArgumentException("القالب غير موجود: " + key);
        }
        return template.cloneWithNewId(newId, newName, newEmail);
    }
}