import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encryption {
    private ArrayList<Character> sourceTextArray = new ArrayList<>();
    private ArrayList<Character> alphabet = new ArrayList<>();
    private ArrayList<Character> destTextArray = new ArrayList<>();
    private ArrayList<Character> decryptedTextArray = new ArrayList<>();
    private ArrayList<Character> decryptedTempTextArray = new ArrayList<>();




    public Encryption() {

        alphabetFiller();

    }

    public void encrypt(String sourceFile, String destinationFile, int key) {
        readFromFile(sourceFile, sourceTextArray);
        Character currentChar;
        int indexOfCurrentCharInAlphabet;
        int indexOfEncryptedChar;
        Character encryptedChar;
        for (Character c : sourceTextArray) {

            indexOfCurrentCharInAlphabet = alphabet.indexOf(c);

            if (indexOfCurrentCharInAlphabet != -1) {
                indexOfEncryptedChar = (indexOfCurrentCharInAlphabet + key) % (alphabet.size()-1);

                encryptedChar = alphabet.get(indexOfEncryptedChar);
            } else {
                encryptedChar = c;
            }
            destTextArray.add(encryptedChar);

        }
        writeToFile(destinationFile, destTextArray);
    }

    private void readFromFile(String file, List<Character> list) {
        try (BufferedReader inputStream = Files.newBufferedReader(Path.of(file))) {
            String line = "";
            while ((line = inputStream.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    list.add(c);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(String file, List<Character> list) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(file))) {
            for (Character c : list) {
                writer.append(c);
            }
        } catch (IOException e) {
        }

    }

    public void decryption(String sourceFile, String destinationFile, int key) {
        readFromFile(sourceFile, destTextArray);

        int indexOfCurrentCharInAlphabet;
        int indexOfEncryptedChar;
        Character encryptedChar;
        for (Character c : destTextArray) {

            indexOfCurrentCharInAlphabet = alphabet.indexOf(c);

            if (indexOfCurrentCharInAlphabet != -1) {

                indexOfEncryptedChar = (indexOfCurrentCharInAlphabet - key) > 0 ? (indexOfCurrentCharInAlphabet - key) % (alphabet.size()-1) : (alphabet.size()-1) + (indexOfCurrentCharInAlphabet - key);
//                if(indexOfEncryptedChar < alphabet.size()) {
//
//                    encryptedChar = alphabet.get(indexOfEncryptedChar);
//                }
//                else {
//
//                }
                encryptedChar = alphabet.get(indexOfEncryptedChar);
            } else {
                encryptedChar = c;
            }
            decryptedTextArray.add(encryptedChar);

        }
        writeToFile(destinationFile, decryptedTextArray);
    }
    private void alphabetFiller() {
        alphabet.add(' ');
        alphabet.add('a');
        alphabet.add('b');
        alphabet.add('c');
        alphabet.add('d');
        alphabet.add('e');
        alphabet.add('f');
        alphabet.add('g');
        alphabet.add('h');
        alphabet.add('i');
        alphabet.add('j');
        alphabet.add('k');
        alphabet.add('l');
        alphabet.add('m');
        alphabet.add('n');
        alphabet.add('o');
        alphabet.add('p');
        alphabet.add('q');
        alphabet.add('r');
        alphabet.add('s');
        alphabet.add('t');
        alphabet.add('u');
        alphabet.add('v');
        alphabet.add('w');
        alphabet.add('x');
        alphabet.add('y');
        alphabet.add('z');

        alphabet.add('A');
        alphabet.add('B');
        alphabet.add('C');
        alphabet.add('D');
        alphabet.add('E');
        alphabet.add('F');
        alphabet.add('G');
        alphabet.add('H');
        alphabet.add('I');
        alphabet.add('J');
        alphabet.add('K');
        alphabet.add('L');
        alphabet.add('M');
        alphabet.add('N');
        alphabet.add('O');
        alphabet.add('P');
        alphabet.add('Q');
        alphabet.add('R');
        alphabet.add('S');
        alphabet.add('T');
        alphabet.add('U');
        alphabet.add('V');
        alphabet.add('W');
        alphabet.add('X');
        alphabet.add('Y');
        alphabet.add('Z');
        alphabet.add(' ');


    }

    public void cryptAnalyzer(String encryptedFile, String cryptAnalyserDestinationFile){

        List<String> temp = Arrays.asList("the",  "and", "THE","The", "AND");
        int key = 1;
        int counter = 0;
        for (int i = 1; i < 64; i++) {

            decryption(encryptedFile, cryptAnalyserDestinationFile, key);
            try {
                String text = Files.readString(Path.of(encryptedFile));
                String[] array = text.split(" ");
                for (String word: array) {
                    if(temp.contains(word)){
                        counter++;
                    }
                }
                if(counter > 10){
                    key ++;
                    break;
                }
            } catch (IOException e) {

            }
        }

        System.out.println("key = "+ key);

    }





}
