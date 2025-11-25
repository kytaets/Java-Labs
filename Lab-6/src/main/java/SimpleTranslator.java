import java.util.HashMap;
import java.util.Map;

class SimpleTranslator {
    private Map<String, String> dictionary;

    public SimpleTranslator() {
        this.dictionary = new HashMap<>();
    }

    public void addWord(String englishWord, String ukrainianWord) {
        dictionary.put(englishWord.toLowerCase(), ukrainianWord.toLowerCase());
    }

    public String translatePhrase(String phrase) {
        String[] words = phrase.split(" ");
        StringBuilder translatedPhrase = new StringBuilder();

        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            String lowerCaseWord = cleanWord.toLowerCase();

            String translation = dictionary.get(lowerCaseWord);

            if (translation != null) {
                translatedPhrase.append(translation).append(" ");
            } else {
                translatedPhrase.append("[не знайдено: " + word + "] ");
            }
        }
        return translatedPhrase.toString().trim();
    }
}