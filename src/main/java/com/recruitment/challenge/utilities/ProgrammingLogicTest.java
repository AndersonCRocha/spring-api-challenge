package com.recruitment.challenge.utilities;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammingLogicTest {
	
	public static int ALLOWED_LIMIT = 100000000;
	public static int DEFAULT_RETURN = -1;
	
	public static void main(String[] args) {

		final String BASE_TEXT = "The bigger number in the %d family is %d. Expected: %d\n";
		
		int numberOne = 213;
		int numberTwo = 553;
		int numberThree = 100000001;
		
		int expectedBiggerOne = 321;
		int expectedBiggerTwo = 553;
		int expectedBiggerThree = -1;
		
		int biggerOne = getBiggerNumberOfTheFamily(numberOne);
		int biggerTwo = getBiggerNumberOfTheFamily(numberTwo);
		int biggerThree = getBiggerNumberOfTheFamily(numberThree);
		
		StringBuilder stringBuilder = new StringBuilder("---------Result--------\n")
				.append(String.format(BASE_TEXT, numberOne, biggerOne, expectedBiggerOne))
				.append(String.format(BASE_TEXT, numberTwo, biggerTwo, expectedBiggerTwo))
				.append(String.format(BASE_TEXT, numberThree, biggerThree, expectedBiggerThree));

		if (biggerOne == expectedBiggerOne && biggerTwo == expectedBiggerTwo && biggerThree == expectedBiggerThree) {
			stringBuilder.append("All tests passed!");
			System.out.println(stringBuilder.toString());
		} else {
			stringBuilder.append("Testing error!");
			System.err.println(stringBuilder.toString());
		}
	}
	
	private static int getBiggerNumberOfTheFamily(int number) {
		
		if (number > ALLOWED_LIMIT || number < 0) {
			return DEFAULT_RETURN;
		}
		
		String[] numbersAsString = String.valueOf(number).split("");
		
		List<String> sortedNumbers = Arrays.asList(numbersAsString);
		sortedNumbers.sort(Comparator.reverseOrder());
		
		String biggerAsString = sortedNumbers.stream().collect(Collectors.joining());
		Integer bigger = Integer.valueOf(biggerAsString); 
		
		return bigger > ALLOWED_LIMIT ? DEFAULT_RETURN : bigger.intValue();
	}
	
}