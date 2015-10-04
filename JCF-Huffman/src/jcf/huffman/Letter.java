/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf.huffman;

/**
 *
 * @author NickvdMullen
 */
public class Letter {
    
    private char letter;
    private int frequentie;
    
    public Letter(char letter, int freq)
    {
        this.letter = letter;
        this.frequentie = freq;
    }
    
    public char getLetter()
    {
        return this.letter;
    }
    
    public int getFrequentie()
    {
        return this.frequentie;
    }
    
    public void setFrequentie(int frequentie)
    {
        this.frequentie = frequentie;
    }
    
    public void UpFrequentie()
    {
        this.frequentie++;
    }
}
