package org.naturn.java8.lambda.bulkdataoperations;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 下午4:43:16
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class Grep {

	private static void printUsageAndExit(String... str) {
		System.out.println("Usage: " + Grep.class.getSimpleName() + " [OPTION]... PATTERN FILE...");
		System.out.println("Search for PATTERN in each FILE. "
				+ "If FILE is a directory then whole file tree of the directory" + " will be processed.");
		System.out.println("Example: grep -m 100 'hello world' menu.h main.c");
		System.out.println("Options:");
		System.out.println("    -m NUM: stop analysis after NUM matches");
		Arrays.asList(str).forEach(System.err::println);
		System.exit(1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long maxCount = Long.MAX_VALUE;
		if (args.length < 2) {
			printUsageAndExit();
		}

		int i = 0;
		while (args[i].startsWith("-")) {
			switch (args[i]) {

			case "-m":
				try {
					maxCount = Long.parseLong(args[++i]);
				} catch (NumberFormatException e) {
					printUsageAndExit(e.toString());
				}
				break;
			default:
				printUsageAndExit("Unexpected option " + args[i]);
			}
		}

		Pattern pattern = Pattern.compile(args[i++]);
		if (i == args.length) {
			printUsageAndExit("There are no files for input. ");
		}

		try {
			List<Path> files = Arrays.stream(args, i, args.length).map(Paths::get).flatMap(Grep::getPathStream)
					.filter(Files::isRegularFile).collect(toList());
			files.parallelStream().flatMap(Grep::path2Lines).filter(pattern.asPredicate()).limit(maxCount)
					.forEachOrdered(System.out::println);
			;
		} catch (UncheckedIOException e) {
			printUsageAndExit(e.toString());
		}
	}

	private static Stream<Path> getPathStream(Path path) {
		try {
			return Files.walk(path);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static Stream<String> path2Lines(Path path) {

		try {
			return Files.lines(path);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
