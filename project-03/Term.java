//@Author Sathvik Prahadeeswaran srp0061@auburn.edu

import java.util.Arrays;
import java.util.Comparator;

public final class Term implements Comparable<Term> {
   private final String query;
   private final long weight;

	/**
    * Initialize a term with the given query and weight.
    * This method throws a NullPointerException if query is null,
    * and an IllegalArgumentException if weight is negative.
    */
   public Term(String query, long weight) {
      if (query == null) throw new NullPointerException("Query is null");
      if (weight < 0) throw new IllegalArgumentException("Weight <= 0");
      this.query = query;
      this.weight = weight;
   }

	/**
    * Compares the two terms in descending order of weight.
    */
   public static Comparator<Term> byDescendingWeightOrder() {
      return new ComparatorByReverseOrderWeight();
   }

   private static class ComparatorByReverseOrderWeight implements Comparator<Term> {
      @Override
      public int compare(Term a, Term b) {
         if (a.weight == b.weight)
            return 0;
         if (a.weight > b.weight)
            return -1;
         return 1;
      }
   }

	/**
    * Compares the two terms in ascending lexicographic order of query,
    * but using only the first length characters of query. This method
    * throws an IllegalArgumentException if length is less than or equal
    * to zero.
    */
   public static Comparator<Term> byPrefixOrder(int length) {
      if (length < 0) throw new IllegalArgumentException("length <= 0");
      return new ComparatorByPrefixOrderQuery(length);
   }

   private static class ComparatorByPrefixOrderQuery implements Comparator<Term> {
      private int length;
   
      private ComparatorByPrefixOrderQuery(int length) {
         this.length = length;
      }
   
      @Override
      public int compare(Term a, Term b) {
         String aPre, bPre;
      
      
         if (a.query.length() < length) aPre = a.query;
         else aPre = a.query.substring(0, length);
      
         if (b.query.length() < length) bPre = b.query;
         else bPre = b.query.substring(0, length);
      
         return aPre.compareTo(bPre);
      }
   }

	/**
    * Compares this term with the other term in ascending lexicographic order
    * of query.
    */
   @Override
   public int compareTo(Term other) {
      return this.query.compareTo(other.query);
   }

	/**
    * Returns a string representation of this term in the following format:
    * query followed by a tab followed by weight
    */
   @Override
   public String toString(){
      return query + "\t" + weight;
   }
}