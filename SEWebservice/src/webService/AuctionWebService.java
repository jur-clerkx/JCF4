/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webService;

import domain.AuctionObject;
import domain.Bid;
import domain.User;
import domain.UserSession;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Jur
 */
@WebService
public class AuctionWebService {
    private ArrayList<User> users = new ArrayList();
    private ArrayList<AuctionObject> auctionObjects = new ArrayList();
    private ArrayList<UserSession> sessions = new ArrayList();
    private int nextSessionId = 10000;
    private int nextUserId = 500;
    private int nextActionObjectId = 1;
    
    @WebMethod
    public boolean createUser(String userName, String password) {
        for(User u : users) {
            if (u.getLoginName().equals(userName)) {
                return false;
            }
        }
        if (password.length() > 5) {
            users.add(new User(nextUserId, userName, password));
            nextUserId++;
            return true;
        }
        return false;
    }
    
    @WebMethod
    public int loginUser(String username, String password) {
        User u = getUser(username);
        if (u == null) {
            return -1;
        } else {
            if (u.getPassword().equals(password)) {
                sessions.add(new UserSession(u.getUserId(), nextSessionId));
                nextSessionId++;
                return nextSessionId - 1;
            }
        }
        return -1;
    }
    
    @WebMethod
    public ArrayList<AuctionObject> getAuctions (int sessionId) {
        UserSession us = getSession(sessionId);
        if (us.isValid()) {
            return this.auctionObjects;
        } else {
            return new ArrayList();
        }
    }
    
    @WebMethod
    public boolean bid(int sessionId, int objectid, double amount) {
        UserSession us = getSession(sessionId);
        AuctionObject o = getAuction(objectid);
        if (us != null && o != null && us.isValid()) {
            Bid bid = new Bid(us.getUserId(), amount);
            return o.addBid(bid);
        }
        return false;
    }
    
    private User getUser (int userId) {
        for(User u : users) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }
    
    private User getUser (String username) {
        for(User u : users) {
            if (u.getLoginName().equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    private UserSession getSession (int sessionId) {
        for (UserSession us : sessions ) {
            if (us.getSessionId() == sessionId) {
                return us;
            }
        }
        return null;
    }
    
    private AuctionObject getAuction (int auctionid) {
        for (AuctionObject o : auctionObjects) {
            if (o.getAuctionId() == auctionid) {
                return o;
            }
        }
        return null;
    }
}
