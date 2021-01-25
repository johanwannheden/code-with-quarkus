package org.example.timelog;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CallContext {

    private static final ThreadLocal<String> STATE = new ThreadLocal<>();

    public void setCurrentUserId(String user) {
        STATE.set(user);
    }

    public String getCurrentUserId() {
        return STATE.get();
    }

    public void resetCurrentUserId() {
        STATE.remove();
    }
}
