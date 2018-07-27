package test;

public class test2 {
	int nbreChiffre = 5;
	
	public test2() {
	char [] charac = new char[nbreChiffre];
	for (int i = 0; i<nbreChiffre; i++) {
		charac[i] = '#';
	}
	String str = new String(charac);
	System.out.println(str);
	}
	public static void main(String[] args) {
		test2 test = new test2();
	}
}
