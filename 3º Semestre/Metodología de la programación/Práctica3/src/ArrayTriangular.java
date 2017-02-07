/**
 * Imprime un array triangular utilizando length
 * 
 * @author José Luis Garrido Labrador <JoseluCross>
 * @version 1.0
 */

public class ArrayTriangular {
	public static void main(String[] args) {
		int[][] triangular = { { 1, 2, 3, 4 }, { 5, 6, 7 }, { 8, 9 }, { 0 } };
		for (int i = 0; i < triangular.length; ++i) {
			for (int j = 0; j < triangular[i].length; ++j) {
				System.out.print(triangular[i][j] + " ");
			}
			System.out.println();
		}
	}
}
