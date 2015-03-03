import java.util.*;

/**
 * Created by bryte on 3/3/15.
 */
public class NumberToWords {
    private static String words;
    private static int[] values = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70, 80, 90 };
    private static String[] labels = new String[] { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    private static String[] tensLabels = new String[]{" ", " ", "Thousand ", "Million ", "Billion ", "Trillion "};
    private static Map<Integer, String> valueLabelMap = new HashMap<>();

    private static NumberToWords numberToWords;
    public static NumberToWords instance(){
        if (numberToWords == null) {
            numberToWords = new NumberToWords();
            for (int i = 0; i < values.length; i++)
                valueLabelMap.put(values[i], labels[i]);
        }
        words = "";
        return numberToWords;
    }

    public String toWords(int value) {
        List<Integer> partitions = convertToList(String.valueOf(value));
        for (int i = 0; i < partitions.size(); i++) {
            if (partitions.get(i) == 0) continue;
            convertDigitsToWord(partitions.get(i).toString());
            words += tensLabels[partitions.size()-i];
        }
        return words;
    }

    private static List<Integer> convertToList(String sval){
        int remainder = sval.length()%3;
        List<Integer> list = new LinkedList<>();
        if (remainder > 0) list.add(Integer.parseInt(sval.substring(0, remainder)));
        int count = remainder;
        for(int i=1; i<=sval.length()/3; i++) list.add(Integer.parseInt(sval.substring(count, count += 3)));
        return list;
    }

    private static void convertDigitsToWord(String sval){
        String toWords = null;
        if (sval.length() <= 2) toWords = valueLabelMap.get(Integer.parseInt(sval));
        if (toWords != null) {
            words += toWords + " ";
            return;
        }
        if(sval.length() == 2) words += valueLabelMap.get(Integer.parseInt(String.valueOf(sval.charAt(0))) * 10) + " ";
        else if(sval.length() == 3) words += valueLabelMap.get(Integer.parseInt(String.valueOf(sval.charAt(0)))) + " Hundred and ";

        convertDigitsToWord(sval.substring(1));
    }

    public static void main(String[] args) {
        System.out.println(NumberToWords.instance().toWords(2901));
    }
}
