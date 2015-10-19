/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf4opdracht3;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nick van der Mullen
 */
public class GreaterTypes {

    private String GTypename;
    private ArrayList<Subtype> subtypes;
    private ObservableList<Subtype> observableSubtypes;

    public GreaterTypes(String Name) {
        this.GTypename = Name;
        subtypes = new ArrayList<>();
        observableSubtypes = FXCollections.observableArrayList();
    }

    public ObservableList<Subtype> getSubtypes() {
        return observableSubtypes;
    }

    public void addSubtype(Subtype newSubtype) {
        subtypes.add(newSubtype);
        observableSubtypes.add(newSubtype);
    }
    public int getSize()
    {
        return subtypes.size();
    }
    
    @Override
    public String toString()
    {
        return GTypename;
    }
}
