/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewebservice;

import javax.xml.ws.Endpoint;
import webService.AuctionWebService;

/**
 *
 * @author Jur
 */
public class SEWebservice {
    private static final String url = "http://localhost:8080/Auction";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Endpoint point = Endpoint.publish(url, new AuctionWebService());
        System.out.println("JEEJ");
    }
    
}
