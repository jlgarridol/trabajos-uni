
public class ExceptionLauncher {
	public static void main(String[] args) {
		call();
	}
	public static void call(){
		callcall();
	}
	public static void callcall(){
		throw new RuntimeException("JEJE");
	}
}
