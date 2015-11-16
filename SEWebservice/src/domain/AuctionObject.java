/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Jur
 */
public class AuctionObject {
    private int auctionId;

    public int getAuctionId() {
        return auctionId;
    }
    private String name;
    private String description;
    private ArrayList<Bid> offers;
    private LocalTime postDate;
    private LocalTime endTime;

    public AuctionObject(String name, String description, ArrayList<Bid> offers, LocalTime endTime) {
        this.name = name;
        this.description = description;
        this.offers = offers;
        this.endTime = endTime;
        this.postDate = LocalTime.now();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Bid> getOffers() {
        return offers;
    }

    public LocalTime getPostDate() {
        return postDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    
    public boolean addBid (Bid bid) {
        //Check if auction still running
        if (LocalTime.now().isBefore(endTime)) {
            if(offers.get(offers.size() - 1).getAmount() <= bid.getAmount()) {
                offers.add(bid);
                return true;
            }
        }
        return false;
    }
}
