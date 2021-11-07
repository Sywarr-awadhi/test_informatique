package tn.esprit.spring.exception;

public class ContratNotFoundException extends RuntimeException {

	public ContratNotFoundException(String errormsg) {
		super(errormsg);
	}

}
