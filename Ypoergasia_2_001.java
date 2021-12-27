package ypoergasia_2_001;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Ypoergasia_2_001 {

    private static final int THREAD_COUNT = 8;
    private static final String FILE_NAME = "C:\\Users\\ggyfa\\Documents/simpsons_script_lines.csv";

    // Μέθοδος εισαγωγής του αρχείου
    static List<String> loadDataFromFile() {
        System.out.println("Loading " + FILE_NAME);
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(FILE_NAME));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                lines.add(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // To thread εισάγει μια λίστα από String και εξάγει τα αποτελέσματα που ζητάμε
    private static class ProcessThread extends Thread {

        private final List<String> lines;
        private final HashMap<String, Integer> episodeWords = new HashMap<>();
        private final HashMap<String, Integer> locationText = new HashMap<>();
        private final HashMap<String, String> characterWordsSplit = new HashMap<>();
        private final HashMap<String, Integer> bartWords = new HashMap<>();
        private final HashMap<String, Integer> homerWords = new HashMap<>();
        private final HashMap<String, Integer> margeWords = new HashMap<>();
        private final HashMap<String, Integer> lisaWords = new HashMap<>();

        public ProcessThread(List<String> lines) {
            this.lines = lines;
            System.out.println(this.getName() + " processing " + lines.size() + " lines");
        }

        @Override
        public void run() {
            for (String line : lines) {
                String[] columns = line.split(",");

                // skip lines with errors
                if (columns.length != 9) {
                    continue;
                }
                String episode_id = columns[1];
                String location_id = columns[4];
                String text = columns[7];
                int word_count = 0;
                int character_id2 = 0;

                // μετατροπή των δεδομένων της στήλης σε αριθμούς
                try {

                    word_count = Integer.parseInt(columns[8]);

                } catch (NumberFormatException e) {
                    continue;
                }
                // μετατροπή των δεδομένων της στήλης σε αριθμούς
                try {

                    character_id2 = Integer.parseInt(columns[3]);

                } catch (NumberFormatException e) {
                    continue;
                }

                //κάλεσμα των μεθώδων 
                proccesEpisodeWords(episode_id, word_count);
                proccesLocationText(location_id, text);
                processCharacterWordsSplit(character_id2, text);

            }
        }

        //μέθοδος εντοπισμού λέξεων του κάθε επεισοδίου
        private void proccesEpisodeWords(String episode_id, int word_count) {
            int wordsSum = word_count;
            if (episodeWords.containsKey(episode_id)) {
                wordsSum += episodeWords.get(episode_id);
            }
            episodeWords.put(episode_id, wordsSum);
        }

        //μέθοδος εντοπισμού στιχομυθιών της κάθε τοποθεσίας
        private void proccesLocationText(String location_id, String text) {
            int textSum = 1;
            if (locationText.containsKey(location_id)) {
                textSum += locationText.get(location_id);
            }
            locationText.put(location_id, textSum);
        }

        //μέθοδος εντοπισμού των λέξεων του κάθε χαρακτήρα
        private void processCharacterWordsSplit(int character_id, String text) {

            String[] splited = text.split(" ");
            for (int i = 0; i < splited.length; i++) {
                if (splited[i].length() > 4) {

                    if (character_id == 8) { //εάν ο χαρακτήρας είναι ο Bart
                        int bartSum = 1;
                        if (bartWords.containsKey(splited[i])) {
                            bartSum += bartWords.get(splited[i]);
                        }
                        bartWords.put(splited[i], bartSum);

                    } else if (character_id == 2) {//εάν ο χαρακτήρας είναι ο Homer
                        int homerSum = 1;
                        if (homerWords.containsKey(splited[i])) {
                            homerSum += homerWords.get(splited[i]);
                        }
                        homerWords.put(splited[i], homerSum);
                    } else if (character_id == 1) {//εάν ο χαρακτήρας είναι η Marge
                        int margeSum = 1;
                        if (margeWords.containsKey(splited[i])) {
                            margeSum += margeWords.get(splited[i]);
                        }
                        margeWords.put(splited[i], margeSum);
                    } else if (character_id == 9) {//εάν ο χαρακτήρας είναι η Lisa
                        int lisaSum = 1;
                        if (lisaWords.containsKey(splited[i])) {
                            lisaSum += lisaWords.get(splited[i]);
                        }
                        lisaWords.put(splited[i], lisaSum);
                    }

                }
            }

        }

        //getters 
        public HashMap<String, Integer> getEpisodeWords() {
            return episodeWords;
        }

        public HashMap<String, Integer> getLocationText() {
            return locationText;
        }

        public HashMap<String, String> getCharacterWordsSplit() {
            return characterWordsSplit;
        }

        public HashMap<String, Integer> getBartWords() {
            return bartWords;
        }

        public HashMap<String, Integer> getHomerWords() {
            return homerWords;
        }

        public HashMap<String, Integer> getMargeWords() {
            return margeWords;
        }

        public HashMap<String, Integer> getLisaWords() {
            return lisaWords;
        }

    }

    //Μέθοδος εκτύπωσης αγαπημένης λέξης του κάθε χαρακτήρα
    static void printCharacter(String characterName, HashMap<String, Integer> characterWords) {

        int maxValueInMap;
        maxValueInMap = (Collections.max(characterWords.values()));

        for (Entry<String, Integer> entry : characterWords.entrySet()) {
            if (entry.getValue() == maxValueInMap) {

                System.out.println(characterName + "'s favorite word, is \"" + entry.getKey() + "\", which said " + maxValueInMap + " times.");

            }

        }

    }

    //main
    public static void main(String[] args) {
        
        long startTime = System.nanoTime(); 

    

   
        List<String> lines = loadDataFromFile();

        System.out.println("Loaded " + lines.size() + " lines");

        String headers = lines.remove(0); //Διαγραφή επικεφαλίδας 
        String[] columns = headers.split(","); //Χωρισμός στηλών 
        System.out.println("\nThe data have the following columns:");
        //Οι στήλες του πίνακα είναι οι εξής 
        for (int i = 0; i < columns.length; i++) {
            System.out.println(i + ") " + columns[i]);
        }

        ProcessThread[] threads = new ProcessThread[THREAD_COUNT];
        int batchSize = lines.size() / THREAD_COUNT;
        System.out.println("\nProcess lines in batches of " + batchSize + " lines");

        //Μοιρασιά των Lines στα νήματα
        for (int i = 0; i < threads.length; i++) {
            int start = i * batchSize;
            int end = (i + 1) * batchSize;

            // Εάν περισεύουν lines βάλτες στο τελευταίο νήμα
            if (i == threads.length - 1 && end < lines.size()) {
                System.out.println("end is extended from " + end + " to " + lines.size());
                end = lines.size();
            }
            System.out.println("Batch [start, end): [" + start + ", " + end + ")");

            threads[i] = new ProcessThread(lines.subList(start, end));
            threads[i].start();
        }

        for (ProcessThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Δημιουργία των HasMaps
        HashMap<String, Integer> episodeWords = new HashMap<>();
        HashMap<String, Integer> locationText = new HashMap<>();
        HashMap<String, Integer> bartWords = new HashMap<>();
        HashMap<String, Integer> homerWords = new HashMap<>();
        HashMap<String, Integer> margeWords = new HashMap<>();
        HashMap<String, Integer> lisaWords = new HashMap<>();

        // Ένωση των νημάτων των λέξεων ανά επεισόδιο 
        for (ProcessThread thread : threads) {
            // Merge EpisodeWords
            thread.getEpisodeWords().forEach((episode_id, word_count) -> {
                int wordSum = word_count;
                if (episodeWords.containsKey(episode_id)) {
                    wordSum += episodeWords.get(episode_id);
                }
                episodeWords.put(episode_id, wordSum);
            });

        }

        // Ένωση των νημάτων των στιχωμυθιών ανά τοποθεσία 
        for (ProcessThread thread : threads) {
            // Merge LocationText
            thread.getLocationText().forEach((location_id, text) -> {
                int textSum = text;
                if (locationText.containsKey(location_id)) {
                    textSum += locationText.get(location_id);
                }
                locationText.put(location_id, textSum);
            });

        }

        // Ένωση των νημάτων των λέξεων του Bart 
        for (ProcessThread thread : threads) {
            // Merge BartWords
            thread.getBartWords().forEach((word, text) -> {
                int textSum = text;
                if (bartWords.containsKey(word)) {
                    textSum += bartWords.get(word);
                }
                bartWords.put(word, textSum);
            });

        }

        // Ένωση των νημάτων των λέξεων του Homer 
        for (ProcessThread thread : threads) {
            // Merge HomerWords
            thread.getHomerWords().forEach((word, text) -> {
                int textSum = text;
                if (homerWords.containsKey(word)) {
                    textSum += homerWords.get(word);
                }
                homerWords.put(word, textSum);
            });

        }

        // Ένωση των νημάτων των λέξεων της Marge 
        for (ProcessThread thread : threads) {
            // Merge MargeWords
            thread.getMargeWords().forEach((word, text) -> {
                int textSum = text;
                if (margeWords.containsKey(word)) {
                    textSum += margeWords.get(word);
                }
                margeWords.put(word, textSum);
            });

        }

        // Ένωση των νημάτων των λέξεων της Lisa
        for (ProcessThread thread : threads) {
            // Merge LisaWords
            thread.getLisaWords().forEach((word, text) -> {
                int textSum = text;
                if (lisaWords.containsKey(word)) {
                    textSum += lisaWords.get(word);
                }
                lisaWords.put(word, textSum);
            });

        }

        // Εντωπισμός και εκτύπωση του επεισοδίου με τις περισσότερες λέξεις
        int maxValueInMap;
        maxValueInMap = (Collections.max(episodeWords.values()));
        for (Entry<String, Integer> entry : episodeWords.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                System.out.println("\nThe episode with the maximum number of words, is the " + entry.getKey() + "th episode, which has " + maxValueInMap + " words.");

            }
        }

        // Εντωπισμός και εκτύπωση της τοποθεσίας με τις περισσότερες στιχομυθίες
        int maxValueInMap2;
        maxValueInMap2 = (Collections.max(locationText.values()));
        for (Entry<String, Integer> entry2 : locationText.entrySet()) {
            if (entry2.getValue() == maxValueInMap2) {

                System.out.print("The location with the maximum number of dialogues, is the " + entry2.getKey() + "th location, which has " + maxValueInMap2 + " dialogues.");
            }

        }

        //Εκτύπωση χαρακτήρων με τις αγαπημένες τους λέξεις
        System.out.println("");
        printCharacter("Bart", bartWords);
        printCharacter("Homer", homerWords);
        printCharacter("Marge", margeWords);
        printCharacter("Lisa", lisaWords);
        
         double totalTime = System.nanoTime() - startTime;
        System.out.println("\nFor " + threads.length + " threads :");
        System.out.println("Total time: " + totalTime / 1_000_000_000 + " seconds");  


    }

}
