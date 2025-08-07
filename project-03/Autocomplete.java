//@Author Sathvik Prahadeeswaran srp0061@auburn.edu

import java.util.Arrays;

public final class Autocomplete {
   private final Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
      if (terms == null) {
         throw new NullPointerException("null");
      }
      this.terms = new Term[terms.length];
   
      for (int i = 0; i < terms.length; i++) {
         this.terms[i] = terms[i];
      }
   
      Arrays.sort(this.terms);
   }

    /**
	 * Returns all terms that start with the given prefix, in descending order of weight.
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
      if (prefix == null) throw new NullPointerException("null");
   
      Term term = new Term(prefix, 0);
      int firstIndex = BinarySearch.firstIndexOf(terms, term, Term.byPrefixOrder(prefix.length()));
      if (firstIndex == -1) {
         return new Term[0];
      }
      int lastIndex  = BinarySearch.lastIndexOf (terms, term, Term.byPrefixOrder(prefix.length()));
   
      Term[] matches = new Term[1 + lastIndex - firstIndex];
   
      for (int i = 0; i < matches.length; i++) {
         matches[i] = terms[firstIndex++];
      }
   
      Arrays.sort(matches, Term.byDescendingWeightOrder());
      return matches;
   }
}