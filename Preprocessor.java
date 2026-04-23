import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Preprocessor {
	private Set<String> stopWords;

   
    public Preprocessor(String stopwordsFilePath) {
        this.stopWords = new HashSet<>();
        loadStopWords(stopwordsFilePath);
    }

    private void loadStopWords(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    stopWords.add(line.trim().toLowerCase());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading stopwords file: " + e.getMessage());
        }
    }

    // O(1) lookup to check if a word should be ignored
    public boolean isStopWord(String word) {
        return stopWords.contains(word);
    }

   
    public int countPunctuation(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                count++;
            }
        }
        return count;
    }
}
