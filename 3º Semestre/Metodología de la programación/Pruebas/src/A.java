abstract class A implements I{

	private int i;
	protected int j;
	public A(int i, int j){
		this.i = i;
		this.j = j;
	}
	public int consultarI(){
		return i;
	}
	@Override
	public void o(){
		i = i*j;
	}
	
	//Código 1
	public static void main(String[] args){
		A a1 = new B(2,3);
		a1.o();
		//a1.p();
		System.out.println(a1.consultarI());
		System.out.println(a1.j);
		I i1 = a1;
		//I i2 = new A(3,2);
		System.out.println(((A) i1).consultarI());
		J j1 = new C(3,4);
		//B b1 = new C(2,4);
		J j2 = new B(2,2);
		j1.o();
		j1.p();
		j2.p();
		//System.out.println(j2.consultarI());
	}

}

interface I {
	void o();
}

interface J {
	void p();
	default void o(){
		System.out.println("o en J");
	}
}

class B extends A implements J {
	public B(int i, int j){
		super(i*i,j*j);
	}
	@Override
	public void o(){
		j = j * consultarI();
	}
	@Override
	public void p(){
		j += consultarI();
	}
	//Código 2
	public static void main(String[] args){
		A a1 = new B(2,4);
		//A a2 = new C(2,3)
		C.consultarK();
		a1.o();
		System.out.println(a1.consultarI());
		//a1.p();
		System.out.println(a1.j);
		//J j2 = a1;
		a1.o();
		J j3 = new C(2,5);
		j3.o();
		C c1 = (C) j3;
		j3.p();
		//J j4 = new J();
	}
}

class C implements I, J{
	
	private static int k;
	
	public C(int i,int j){
		k = i * j;
	}
	@Override
	public void p(){
		System.out.println("p en C "+k);
	}
	@Override
	public void o(){
		System.out.println("o en C " +k);
	}
	public static void consultarK(){
		System.out.println(k);
	}
}