package eg.edu.alexu.csd.datastructure.linkedList.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.PolynomialSolver;

class PolynomialTest {

	@Test
	void printTest() {
		PolynomialSolver test = new PolynomialSolver();
		
		int[][] terms = {{-4, 0},{-3, -1}, {5, -2}};
		test.setPolynomial('A', terms);
		String p = test.print('A');
		String r1 = "-4-3X^-1+5X^-2";
		assertEquals(r1, p);
		
		int[][] terms2 = {{0, 300},{0, 5}, {0, -1}};
		test.setPolynomial('A', terms2);
		p = test.print('A');
		assertEquals("0", p);
	}
	@Test
	void evaluateTests() {
		PolynomialSolver test = new PolynomialSolver();
		
		int[][]terms1 = {{-4, 0},{-3, -1}, {5, -2}};
		test.setPolynomial('A', terms1);
		assertEquals(-2, test.evaluatePolynomial('A', 1));
		
		int[][] terms2 = {{0, 300},{0, 5}, {0, -1}};
		test.setPolynomial('A', terms2);
		assertEquals(0, test.evaluatePolynomial('A', 3000));
	}
	@Test
	void addTest(){
		PolynomialSolver test = new PolynomialSolver();
		
		int[][] terms0 = {{0,0}};
		int[][]terms1 = {{-4, 0},{-3, -1}, {5, -2}};
		test.setPolynomial('A', terms1);
		int[][] terms2 = {{0, 300},{0, 5}, {0, -1}};
		test.setPolynomial('B', terms2);
		assertArrayEquals(terms1, test.add('A','B'));
		int[][] terms3 = {{4, 0},{3, -1}, {-5, -2}};
		test.setPolynomial('C', terms3);
		assertArrayEquals(terms0, test.add('A','C'));
	}
	@Test
	void subtractTest() {
		PolynomialSolver test = new PolynomialSolver();
		
		int[][] terms0 = {{0,0}};
		int[][]terms1 = {{-4, 0},{-3, -1}, {5, -2}};
		test.setPolynomial('A', terms1);
		int[][] terms2 = {{0, 300},{0, 5}, {0, -1}};
		test.setPolynomial('B', terms2);
		int[][] terms3 = {{4, 0},{3, -1}, {-5, -2}};
		assertArrayEquals(terms3, test.subtract('B','A'));
		assertArrayEquals(terms1, test.subtract('A','B'));
		assertArrayEquals(terms0, test.subtract('A','A'));
	}
	@Test
	void multiplyTest() {
		PolynomialSolver test = new PolynomialSolver();
		
		int[][] terms0 = {{0,0}};
		int[][]terms1 = {{-4, 0},{-3, -1}, {5, -2}};
		test.setPolynomial('A', terms1);
		int[][] terms2 = {{0, 300},{0, 5}, {0, -1}};
		int[][] terms3 = {{4, 0},{3, -1}, {-5, -2}};
		int[][] terms4 = {{1,0}};
		int[][] terms5 = {{-1,0}};
		int[][] terms6 = {{1,1}};
		int[][] terms7 = {{-4, 1},{-3, 0}, {5, -1}};
		int[][] terms8 = {{1,1},{1,0}};
		int[][] terms9 = {{-4, 1},{-7, 0},{2, -1}, {5, -2}};
		test.setPolynomial('B', terms2);
		assertArrayEquals(terms0, test.multiply('A','B'));
		test.setPolynomial('B', terms4);
		assertArrayEquals(terms1, test.multiply('A','B'));
		test.setPolynomial('B', terms5);
		assertArrayEquals(terms3, test.multiply('A','B'));
		test.setPolynomial('B', terms6);
		assertArrayEquals(terms7, test.multiply('A','B'));
		test.setPolynomial('B', terms8);
		assertArrayEquals(terms9, test.multiply('A','B'));
	}
}
