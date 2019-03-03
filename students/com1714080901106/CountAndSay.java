import java.util.Scanner;

/*
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。
 * 前六项：
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * 312211
 */

public class CountAndSay {
	static String countAndSay(int n) {
		String s = "1";
		for (int i = 1; i < n; i++) {
			int index = 0;
			String str = "";
			for (int j = 0; j < s.length(); j++) {
				int count = 0;
				while (s.charAt(index) == s.charAt(j)) {
					count++;
					j++;
					if (j == s.length())
						break;
				}
				str = str + Integer.toString(count) + s.charAt(index);
				index = j;
				j--;
			}
			s = str;
		}
		return s;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println(countAndSay(n));
		in.close();
	}
}
