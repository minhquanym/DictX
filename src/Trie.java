import java.util.*;

class TrieNode {
    static final int ALPHABET_SIZE = 26;
    private TrieNode[] child;
    private int id;

    public TrieNode() {
        child = new TrieNode[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            child[i] = null;
        }
        id = -1;
    }
    public TrieNode[] getChild() {
        return this.child;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return this.root;
    }

    /**
     * Add word w to Trie with position in Array is id
     * @param target english word add
     * @param id pos in array
     */
    public void add(String target, int id) {
        String key = target.toLowerCase();
        TrieNode p = root;
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i) - 'a';
            if (p.getChild()[c] == null) {
                p.getChild()[c] = new TrieNode();
            }
            p = p.getChild()[c];
        }
        p.setId(id);
    }

    /**
     * Support function for suggest
     * @param p current TrieNode
     * @param results array store node found
     * @param num limit result array
     */
    private void DFS(TrieNode p, ArrayList<Integer> results, int num) {
        if (results.size() == num || p == null) {
            return;
        } else if (p.getId() != -1) {
            results.add(p.getId());
        }
        for (int c = 0; c < p.ALPHABET_SIZE; c++) {
            if (p.getChild()[c] != null) {
                DFS(p.getChild()[c], results, num);
            } else {
                // do nothing
            }
        }
    }
    /**
     * Suggest a list of word begin with target
     * @param target English word
     * @return list of first num English word begin with target
     */
    public ArrayList<Integer> suggest(String target, int num) {
        ArrayList<Integer> results = new ArrayList<Integer>();
        String key = target.toLowerCase();
        TrieNode p = root;

        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i) - 'a';
            if (p.getChild()[c] == null) {
                return results;
            } else {
                p = p.getChild()[c];
            }
        }
        if (p == null) {
            return results;
        } else {
            DFS(p, results, num);
            return results;
        }
    }
    /**
     * Search if target word existed in dictionary
     * @param target English word
     * @return pos in the Array
     */
    public int search(String target) {
        String key = target.toLowerCase();
        TrieNode p = root;
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i) - 'a';
            if (p.getChild()[c] == null) {
                return -1;
            } else {
                p = p.getChild()[c];
            }
        }
        if (p != null && p.getId() != -1) {
            return p.getId();
        } else {
            return -1;
        }
    }
    public void delete(String target) {
        this.add(target, -1);
    }
}
