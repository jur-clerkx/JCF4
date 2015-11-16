/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalTime;

/**
 *
 * @author Jur
 */
public class UserSession {
    private int userId;
    private int sessionId;
    private LocalTime valid;

    public UserSession(int userId, int sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
        valid = LocalTime.now().plusMinutes(15);
    }

    public int getUserId() {
        return userId;
    }

    public int getSessionId() {
        return sessionId;
    }
    
    public boolean isValid() {
        if(LocalTime.now().isBefore(valid)) {
            valid = LocalTime.now().plusMinutes(15);
            return true;
        }
        return false;
    }
}
