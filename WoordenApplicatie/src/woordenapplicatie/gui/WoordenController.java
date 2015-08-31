package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {
    
   private static final String DEFAULT_TEXT =   "Een, twee, drie, vier\n" +
                                                "Hoedje van, hoedje van\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "Heb je dan geen hoedje meer\n" +
                                                "Maak er één van bordpapier\n" +
                                                "Eén, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van, hoedje van\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier\n" +
                                                "\n" +
                                                "En als het hoedje dan niet past\n" +
                                                "Zetten we 't in de glazenkas\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier";
    
    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }
    
    @FXML
    private void aantalAction(ActionEvent event) {
         /**Split all items into array, request array size because its allready there
          * Then add all items into hashset, as it is fast and removes all the double words, then request size of that
          */
        String input = taInput.getText().toLowerCase();
        String[] words = input.trim().split(",\\s|\\s|,|\\.|\n");
        Set set = new HashSet(Arrays.asList(words));
        
        //Display results
        taOutput.setText("Aantal woorden: " + words.length + "\n" +
                "Aantal verschillende: " + set.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        /**Split all items into array, request array size because its allready there
          * Then add all items into hashset, as it is fast and removes all the double words
          * Add collection to a list, sort and print output
          */
        String input = taInput.getText().toLowerCase();
        String[] words = input.trim().split(",\\s|\\s|,|\\.|\n");
        Set set = new HashSet(Arrays.asList(words));
        
        List<String> list = new ArrayList(set);
        Collections.sort(list);
        Collections.reverse(list);
        
        String result = "";
        for (String s : list) {
            result += s + "\n";
        }
        
        taOutput.setText(result);
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
         throw new UnsupportedOperationException("Not supported yet."); 
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
         throw new UnsupportedOperationException("Not supported yet."); 
    }
   
}
