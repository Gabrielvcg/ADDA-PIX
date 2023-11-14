package util;

public record EnteroCadena(Integer a, String s) {
	
	public static EnteroCadena of(Integer a, String s) {
		return new EnteroCadena(a,s);
		
	}

	public Integer a() {
		return this.a;
	}

	public String s() {
		// TODO Auto-generated method stub
		return this.s;
	}

	



	
	}
	

