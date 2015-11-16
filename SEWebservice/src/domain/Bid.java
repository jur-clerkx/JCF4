/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Jur
 */
public class Bid {
    private int bidderId;
    private double amount;
    
    public Bid(int bidderId, double amount) {
        this.bidderId = bidderId;
        this.amount = amount;
    }

    public int getBidderId() {
        return bidderId;
    }

    public double getAmount() {
        return amount;
    }
}
