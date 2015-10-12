/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf.huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 *
 * @author NickvdMullen
 */
public class Huffman {

    private final Map<Character, Integer> frequentieMap = new TreeMap();
    private final List<Letter> letters = new ArrayList<>();

    private static final List<HuffmanKnoop> nodes = new ArrayList<>();

    static HuffmanKnoop root;
    static Map<Character, String> codes = new HashMap<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = "Bananen";

        //Telt hoe vaak elke letter voor komt. En maakt er een boom van.
        getLetterFrequencies(input);
        System.out.println("\n");
        //Maakt voor elke letter een unieke code.
        createCodes(root, "");
        System.out.println("\n");

        //Veranderd Text naar code.
        String codedMessage = codeMessage(input);
        System.out.println("\n");

        //Print decoded text
        System.out.println("Van boom naar text: \t" + decodeMessage(codedMessage) + "\n");
        System.out.println("Van binair naar text , met map: \t" + decodeMessageWithCodeList(codedMessage, codes)+ "\n");
    }

    private static String decodeMessageWithCodeList(String codedmessage, Map<Character, String> codes) {
        String result = "";

        //Vergelijkt substring met mapvalues, en zet deze in een string
        int nextCutOff = 1;
        while (codedmessage.length() > 0) {
            String codePart = codedmessage.substring(0, nextCutOff);
            Character foundLetter = '-';
            for (Map.Entry<Character, String> entry : codes.entrySet()) {
                if (entry.getValue().equals(codePart)) {
                    foundLetter = entry.getKey();
                    codedmessage = codedmessage.substring(nextCutOff);
                }
            }

            if (foundLetter == '-') {
                nextCutOff += 1;
            } else {
                result = result + String.valueOf(foundLetter);
                nextCutOff = 1;
            }
        }

        return result;
    }

    private static String decodeMessage(String codedMessage) {
        String decodedMessage = "";
        HuffmanKnoop nextKnoop = root;

        //Haalt de message door de boom stap voor stap tot dat de letter gevonden is. En zet dit in een string
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '0') {
                if (nextKnoop.leftChild.letter == 0) {
                    nextKnoop = nextKnoop.leftChild;
                } else {
                    decodedMessage = decodedMessage + nextKnoop.leftChild.letter;
                    nextKnoop = root;
                }
            } else if (codedMessage.charAt(i) == '1') {
                if (nextKnoop.rightChild.letter == 0) {
                    nextKnoop = nextKnoop.rightChild;
                } else {
                    decodedMessage = decodedMessage + nextKnoop.rightChild.letter;
                    nextKnoop = root;
                }
            }
        }

        return decodedMessage;
    }

    private static String codeMessage(String input) {
        char[] textAsChararray;
        textAsChararray = input.toCharArray();

        //Haalt de code van het character uit een map, zet deze in een string
        String result = "";
        for (char c : textAsChararray) {
            result = result + codes.get(c);
        }

        System.out.println("Gecodeerd bericht: " + result);
        return result;
    }

    //Geeft elke letter in de text een unieke code
    private static void createCodes(HuffmanKnoop root, String vorigeCode) {
        char start = '\0';
        if (root.letter == start) {
            createCodes(root.rightChild, vorigeCode.concat("1"));
            createCodes(root.leftChild, vorigeCode.concat("0"));
        } else {
            codes.put(root.letter, vorigeCode);
            System.out.println("letter: " + root.letter + "\t" + vorigeCode);
        }
    }

    //Telt hoe vaak iedere letter voor komt. Een maakt boom
    private static void getLetterFrequencies(String input) {
        char[] textAsChararray;
        textAsChararray = input.toCharArray();

        for (char c : textAsChararray) {
            boolean letterBestaatAlInLijst = false;

            for (HuffmanKnoop knoop : nodes) {
                if (knoop.letter == c) {
                    knoop.frequentie += 1;
                    letterBestaatAlInLijst = true;
                }
            }

            if (!letterBestaatAlInLijst) {
                nodes.add(new HuffmanKnoop(c));
            }
        }

        //Sorteert op frequentie
        PriorityQueue queue = new PriorityQueue<>();
        queue.addAll(nodes);
        createHuffmanTree(queue);
    }

    //Maakt HuffmanTree
    public static void createHuffmanTree(PriorityQueue<HuffmanKnoop> huffQ) {
        System.out.println("Generated huffman tree:");
        HuffmanKnoop huffknoop = new HuffmanKnoop();
        HuffmanKnoop knoop1;
        HuffmanKnoop knoop2;
        while ((knoop1 = huffQ.poll()) != null) {
            if ((knoop2 = huffQ.poll()) != null) {
                huffknoop = new HuffmanKnoop();
                huffknoop.frequentie = (knoop1.frequentie + knoop2.frequentie);
                huffknoop.leftChild = knoop1;
                huffknoop.rightChild = knoop2;
                huffQ.add(huffknoop);
                System.out.println("Left child : " + (huffknoop.leftChild.letter) + " " + "Frequency : " + (huffknoop.leftChild.frequentie)
                        + " " + "Right child : " + (huffknoop.rightChild.letter) + " " + "Frequency : " + (huffknoop.rightChild.frequentie)
                        + " -->  Value :" + (String.valueOf(huffknoop.frequentie)));
            } else {
                System.out.println("Root Value  : " + knoop1.frequentie);
                root = knoop1;
            }
        }
    }
}
