import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Sathvik Prahadeeswaran (srp0061@auburn.edu)
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////
   TreeSet<String> lexicon;
   
   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         lexicon = new TreeSet<>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }
   
   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////

    /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
   }

   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str1) {
      return lexicon.contains(str1.toLowerCase());
   }
   
   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: <a href="https://en.wikipedia.org/wiki/Hamming_distance">...</a>
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str2, String str3) {
      if (str2.length() != str3.length()) 
         return -1;
      int count = 0;
      for (int i = 0; i < str2.length(); i++) {
         if (str2.charAt(i) != str3.charAt(i)) count++;
      }
      return count;
   }
   
   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      ArrayList<String> result = new ArrayList<>();
      for (String item : lexicon) {
         int i = getHammingDistance(word, item);
         if (i == 1) result.add(item);
      }
      return result;
   }
   
   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.size() == 0)
         return false;
      if (sequence.size() == 1) 
         return true;
      int count = 0;
      for (int i = 0; i < sequence.size() - 1; i++) {
         if (!isWord(sequence.get(i))) 
            return false;
         if (!isWord(sequence.get(i+1))) 
            return false;
         if (getHammingDistance(sequence.get(i), sequence.get(i+1)) == 1) count++;
      }
      count++;
      return count == sequence.size();
   }
   
   /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      ArrayList<String> result = new ArrayList<>();
      ArrayList<String> parents = new ArrayList<>();
      ArrayList<String> isVisited = new ArrayList<>();
      result.add(start);
      Deque<String> deque = new ArrayDeque<>();
      deque.addLast(start);
      isVisited.add(start);
      parents.add("NoOne");
      boolean canContinue = false;
      while (!deque.isEmpty() && !canContinue) {
         String item = deque.removeFirst();
         List<String> neighbors = getNeighbors(item);
         for (String token : neighbors) {
            if (!isVisited.contains(token)) {
               deque.addLast(token);
               isVisited.add(token);
               result.add(token);
               parents.add(item);
               if (token.equals(end)) canContinue = true;
            }
         }
      }
      ArrayList<String> emptyList = new ArrayList<>();
      if (!result.contains(end)) 
         return emptyList;
      ArrayList<String> values = new ArrayList<>();
      values.add(end); 
      String word = end;
      while (!Objects.equals(word, start)) {
         word = parents.get(result.indexOf(word));
         values.add(word);
      }
      ArrayList<String> valuesReal = new ArrayList<>();
      for (int i = values.size() - 1; i >= 0; i--) {
         valuesReal.add(values.get(i));
      }
      return valuesReal;
   }
}
