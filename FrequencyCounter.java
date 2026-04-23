import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyCounter {
	private Map<String, Integer> wordFrequencies;

    public FrequencyCounter() {
        this.wordFrequencies = new HashMap<>();
    }

    public void addWord(String word) {
        wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
    }

    public int getUniqueWordCount() {
        return wordFrequencies.size();
    }

    // Sorts the map by values and returns the top N entries
    public List<Map.Entry<String, Integer>> getTopNWords(int n) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordFrequencies.entrySet());
        
        // Sort in descending order based on the frequency
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        return list.subList(0, Math.min(n, list.size()));
    }
}
