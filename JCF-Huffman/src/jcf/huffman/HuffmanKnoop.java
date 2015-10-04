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
public class HuffmanKnoop implements Comparable {
    public char letter;
    public int frequentie;
    public HuffmanKnoop leftChild;
    public HuffmanKnoop rightChild;
    
    public HuffmanKnoop()
    {
    
    }
    
    public HuffmanKnoop(char letter)
    {
        
        this.letter = letter;
        this.frequentie = 1;
    }
    
    public HuffmanKnoop(char letter, int frequentie)
    {
        this.letter = letter;
        this.frequentie = frequentie;
    }

    @Override
    public int compareTo(Object o) {
        if (((HuffmanKnoop)o).frequentie < this.frequentie) 
        {
            return 1;
        }
        else if (((HuffmanKnoop)o).frequentie > this.frequentie) 
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
