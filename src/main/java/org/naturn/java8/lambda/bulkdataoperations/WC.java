package org.naturn.java8.lambda.bulkdataoperations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 下午3:55:53
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class WC {

	private static final int READ_AHEAD_LIMIT = 100_000_000;
	private static final Pattern nonWordPattern = Pattern.compile("\\W");

	private static void usage() {

		System.out.println("Usage: " + WC.class.getSimpleName() + " FILE");
		System.out.println("Print newline, word," + " character counts and max line length for FILE. ");
	}

	public static void main(String[] args) throws IOException{
		
		if(args.length != 1){
			usage();
			System.exit(0);
		}
		
		try(BufferedReader reader = new BufferedReader(new FileReader(args[0]))){
			reader.mark(READ_AHEAD_LIMIT);
			
			collectInFourPasses(reader);
			
			reader.reset();
			
			collectInOnePass(reader);
		}catch(FileNotFoundException e){
			usage();
			System.err.println(e);
		}

	}
	
	private static void collectInFourPasses(BufferedReader reader) throws IOException{
		
		System.out.println("Character count = "+ reader.lines().flatMapToInt(String::chars).count());
		
		reader.reset();		
		System.out.println("Word count = " + reader.lines().flatMap(nonWordPattern::splitAsStream).filter(str -> !str.isEmpty()).count());
		
		reader.reset();
		System.out.println("NewLine count = " + reader.lines().count());
		
		reader.reset();
		System.out.println("Max line length = " + reader.lines().mapToInt(String::length).max().getAsInt());
	}
	
	private static void collectInOnePass(BufferedReader reader){
		
		WCStatistics wc = reader.lines().parallel().collect(WCStatistics::new,WCStatistics::accept,WCStatistics::combine);
		System.out.println(wc);
	}

	public static class WCStatistics implements Consumer<String> {

		private long characterCount;
		private long lineCount;
		private long wordCount;
		private long maxLineLength;

		@Override
		public void accept(String t) {
		
			characterCount += t.length();
			lineCount++;
			wordCount += nonWordPattern.splitAsStream(t).filter(str -> !str.isEmpty()).count();
			maxLineLength = Math.max(maxLineLength, t.length());
		}

		public void combine(WCStatistics stat) {
			wordCount += stat.wordCount;
			lineCount += stat.lineCount;
			characterCount += stat.characterCount;
			maxLineLength = Math.max(maxLineLength, stat.maxLineLength);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("#------WCStatistic------#\n");
			sb.append("Character count = ").append(characterCount).append('\n');
			sb.append("Word count = ").append(wordCount).append('\n');
			sb.append("Newline count = ").append(lineCount).append('\n');
			sb.append("Max line length = ").append(maxLineLength).append('\n');
			return sb.toString();
		}
	}
	
}
