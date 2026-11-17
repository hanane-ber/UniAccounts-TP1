package audit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditLog {
    private static AuditLog instance;
    private final List<String> logs;

    private AuditLog() {
        logs = new ArrayList<>();
    }

    public static synchronized AuditLog getInstance() {
        if (instance == null) {
            instance = new AuditLog();
        }
        return instance;
    }

    public void log(String action) {
        String logEntry = "[" + LocalDateTime.now() + "] " + action;
        logs.add(logEntry);
        System.out.println("AUDIT: " + logEntry);
    }

    public List<String> getLogs() {
        return logs;
    }
}