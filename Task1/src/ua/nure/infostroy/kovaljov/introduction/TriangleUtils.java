package ua.nure.infostroy.kovaljov.introduction;

public class TriangleUtils {

	/**
	 * Задача о треугольнике
	 * 
	 * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
	 * могут быть сторонами треугольника и false, если не могут.
	 *
	 */

	public boolean isTriangle(int a, int b, int c) throws IllegalArgumentException {
		if (a <= 0 || b <= 0 || c <= 0) {
			throw new IllegalArgumentException();
		}
		double p = (double) (a + b + c) / 2;
		return p * (p - a) * (p - b) * (p - c) > 0;
	}

	/**
	 * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
	 * площадь треугольника.
	 */

	public double getTriangleArea(int a, int b, int c) throws IllegalArgumentException {
		if (a <= 0 || b <= 0 || c <= 0 || !isTriangle(a, b, c)) {
			throw new IllegalArgumentException();
		}
		double p = (double) (a + b + c) / 2;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}
	
	

}
