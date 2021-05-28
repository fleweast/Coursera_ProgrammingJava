package spelling;

import java.util.*;

public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private final TrieNode root;
    private int size;

    public AutoCompleteDictionaryTrie() {
        root = new TrieNode();
    }

    public boolean addWord(String word) {
        word = word.toLowerCase();
        TrieNode currNode = root;
        for (Character c : word.toCharArray()) {
            TrieNode child = currNode.getChild(c);
            if (child == null) {
                currNode = currNode.insert(c);
            } else {
                currNode = child;
            }
        }
        if (currNode.endsWord()) return false;
        size++;
        currNode.setEndsWord(true);
        return true;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isWord(String s) {
        s = s.toLowerCase();
        TrieNode currNode = root;
        for (Character c : s.toCharArray()) {
            TrieNode child = currNode.getChild(c);
            if (child != null) {
                currNode = child;
            } else return false;
        }
        return currNode.endsWord();
    }

    public List<String> predictCompletions(String prefix, int numCompletions) {
        TrieNode node = root;

        for (Character c : prefix.toLowerCase().toCharArray()) {
            TrieNode child = node.getChild(c);
            if (child != null) {
                node = child;
            } else {
                return Collections.emptyList();
            }
        }

        List<String> completions = new LinkedList<>();
        Queue<TrieNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty() && numCompletions > 0) {
            TrieNode t = queue.poll();

            if (t.endsWord()) {
                completions.add(t.getText());
                --numCompletions;
            }

            for (Character c : t.getValidNextCharacters()) {
                queue.offer(t.getChild(c));
            }

        }

        return completions;
    }

    public void printTree() {
        printNode(root);
    }

    public void printNode(TrieNode curr) {
        if (curr == null) return;

        System.out.println(curr.getText());

        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }
}