import java.util.Scanner;
import java.io.*;

public class SpellCheck {
    HashTable dictionary;
    RBTree textTree;

    public SpellCheck() {
        dictionary = new HashTable();
        textTree = new RBTree();
    }

    public static void main(String args[]) throws IOException {
        SpellCheck sc = new SpellCheck();
        sc.SpellCheckTest();
    }

    public void SpellCheckTest() throws IOException {
        CreateDictionary(dictionary);
        createRBTreeFromTextFile("I-O-Files/Text1.txt");
        deleteExistingWordsInDictionary(textTree.getRoot());
        if (textTree.getRoot().getKey()!= null) {
            System.out.println("Hello friend, I've found misspelled words. You might want to check the list below: ");
            textTree.printInOrder(textTree.getRoot());
        }
        else {
            System.out.println("You are good to go! No misspelled words were found in my Dictionary.");
        }
    }

    private static int CalculateKey(String word){
        long hash = 5381;
        int c;

        for(int i=0; i<word.length();i++) {
            c = Character.getNumericValue(word.charAt(i));
            hash = ((hash << 5) + hash) + c; /* hash * 33 + c */
        }
        long num = Integer.MAX_VALUE;
        int hashInt = (int)(hash%num);
        return Math.abs((hashInt));
    }

    public static void CreateDictionary(HashTable dictionary) throws IOException {
        File file = new File("I-O-Files/DictionaryWords.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.next();
            int key = CalculateKey(line);
            dictionary.put(key, line);
        }
    }

    private void createRBTreeFromTextFile (String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String word = scanner.next();
            RBNode wordNode = new RBNode(word.toLowerCase());
            textTree.RBInsert(wordNode);
        }
    }

    private void deleteExistingWordsInDictionary(RBNode root) {
        if (root.getKey() == null)
            return;

        deleteExistingWordsInDictionary(root.getLeftSon());
        deleteExistingWordsInDictionary(root.getRightSon());

        if ((dictionary.get(CalculateKey(root.getKey()), root.getKey())) != null) {
            textTree.RBDelete(root);
        }

    }
}
