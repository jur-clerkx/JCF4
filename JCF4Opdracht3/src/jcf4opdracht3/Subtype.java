/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf4opdracht3;

/**
 *
 * @author Nick van der Mullen
 */
public class Subtype {

    private String subTypeName;

    private String description;

    public Subtype(String TypeName, String Description) {
        this.subTypeName = TypeName;
        this.description = Description;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public String getDescription() {
        return description;
    }
}
