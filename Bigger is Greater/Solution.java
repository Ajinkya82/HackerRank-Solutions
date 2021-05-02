//Problem: https://www.hackerrank.com/challenges/bigger-is-greater
//Java 8
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

	/*
	 * Complete the 'biggerIsGreater' function below.
	 *
	 * The function is expected to return a STRING.
	 * The function accepts STRING w as parameter.
	 */

	public static String biggerIsGreater(String w) {
		
		//convert input string to string array
		String ar[] = w.split("");
		List<String> s = new ArrayList<String>();
		String temp ="";
		int swapIndex = Integer.MIN_VALUE;
		
		//iterate in reverse order(since we need string lexographically greater than input which is smallest)
		for(int i=ar.length-1; i>0;i--){
			
			//maintain all char in list which will be needed to swap with the temp
			s.add(ar[i]);
			
			//if found a char which is greater than previous char
			//store the char(temp) and its index to be swapped
			if(ar[i].compareTo(ar[i-1])>0){
				swapIndex = i-1;
				temp = ar[i-1];
				break;
			}
		}
		
		//this means all characters are arranged in desc order.sol does not exists
		if(temp.isEmpty())
			return "no answer";
				
		for(String st: s) {
			//if found element greater than temp perform the required swap
			if(st.compareTo(temp) > 0) {
				ar[swapIndex] = st;
				s.remove(st);
				s.add(temp);
				break;
			}
		}
		//sorting subarray in ascending order(since we need string lexographically greater than input which is smallest)
		Collections.sort(s);
		
		return Arrays.toString(Arrays.copyOfRange(ar, 0, swapIndex+1)).replaceAll("[\\[|,|\\]|\\s]","").concat(s.toString().replaceAll("[\\[|,|\\]|\\s]",""));

	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int T = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, T).forEach(TItr -> {
			try {
				String w = bufferedReader.readLine();

				String result = Result.biggerIsGreater(w);

				bufferedWriter.write(result);
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
