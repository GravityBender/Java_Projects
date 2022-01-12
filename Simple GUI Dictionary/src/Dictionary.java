import java.util.HashMap;

public class Dictionary {
    private HashMap<String, String> wm;

    public Dictionary() {
        wm = new HashMap<>();
    }

    public String get(String word) {
        return this.wm.get(word);
    }

    public void add(String word, String definition) {
        // if (!this.wm.containsKey(word)) {
        // this.wm.put(word, definition);
        // }
        this.wm.put(word, definition);
    }

    public boolean hasWord(String word) {
        if (wm.containsKey(word)) {
            return true;
        }

        return false;
    }
}
