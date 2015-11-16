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
public class User {
    private int userId;
    private String loginName;
    private String password;
    private LocalTime joinDate;
    
    public User(int userId, String loginName, String password) {
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
        this.joinDate = LocalTime.now();
    }
    
    public int getUserId() {
        return this.userId;
    }
    
    public String getLoginName() {
        return this.loginName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public LocalTime getJoinDate() {
        return this.joinDate;
    }
}
