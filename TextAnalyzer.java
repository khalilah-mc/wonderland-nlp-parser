import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TextAnalyzer {

	public static void main(String[] args) {
		String textFilePath = "alice29.txt";
        String stopwordsFilePath = "stopwords.txt";

        
        Preprocessor preprocessor = new Preprocessor(stopwordsFilePath);
        FrequencyCounter counter = new FrequencyCounter();

        // Trackers for Task 4
        int rawWordCount = 0;
        int validWordCount = 0;
        int punctuationCount = 0;
        String longestWord = "";

        // Process the Alice file
        try (BufferedReader br = new BufferedReader(new FileReader(textFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                punctuationCount += preprocessor.countPunctuation(line);

                // Tokenize by splitting at non-alphabetical characters
                String[] tokens = line.toLowerCase().split("[^a-z]+");

                for (String token : tokens) {
                    if (token.isEmpty()) continue; 

                    rawWordCount++; 

                    // Check with our Preprocessor to see if we should keep it
                    if (!preprocessor.isStopWord(token)) {
                        validWordCount++; 
                        
                        // Pass the valid word to our FrequencyCounter
                        counter.addWord(token);

                        if (token.length() > longestWord.length()) {
                            longestWord = token;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the text file: " + e.getMessage());
            return;
        }

        
        System.out.println("=== TEXT ANALYSIS RESULTS ===");
        
        System.out.println("\n--- Dataset Statistics (Task 4) ---");
        System.out.println("Total Punctuation Marks Found: " + punctuationCount);
        System.out.println("Raw Word Count (Before Preprocessing): " + rawWordCount);
        System.out.println("Valid Word Count (After Stopwords): " + validWordCount);
        System.out.println("Total Unique Valid Words: " + counter.getUniqueWordCount());
        System.out.println("Longest Valid Word: '" + longestWord + "' (" + longestWord.length() + " chars)");
        System.out.println("\n--- Top 10 Most Frequent Words & Ratios (Task 3) ---");
        List<Map.Entry<String, Integer>> topWords = counter.getTopNWords(10);
        
        for (int i = 0; i < topWords.size(); i++) {
            Map.Entry<String, Integer> entry = topWords.get(i);
            String word = entry.getKey();
            int frequency = entry.getValue();
            
            
            double ratio = (double) frequency / validWordCount;
            
            System.out.printf("%d. %-10s | Count: %-5d | Ratio: %.4f%%\n", 
                    (i + 1), word, frequency, (ratio * 100));
        }
    }

	}


