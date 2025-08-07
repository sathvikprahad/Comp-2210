
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DJ { 
   public static void main(String[] args) {
      LinkedSet<Integer> tester = new LinkedSet<Integer>();
      tester.add(1);
      tester.add(2);
      tester.add(3);
      Iterator<Set<Integer>> testOne = tester.powerSetIterator();
      while(testOne.hasNext()) {
         System.out.println(testOne.next());
      }
      
   }
}