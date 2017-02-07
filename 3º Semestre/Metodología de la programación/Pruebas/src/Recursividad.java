public class Recursividad{
	private static void funcionRecursiva(int value){
		System.out.println(value);
		value++;
		funcionRecursiva(value);
	}
	public static void main(String[] args){
		funcionRecursiva(0);
	}
}