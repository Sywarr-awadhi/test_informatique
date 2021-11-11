package tn.esprit.spring.exception;

public class EmployeNotFoundException extends RuntimeException {

	public EmployeNotFoundException(String errormsg) {
		super(errormsg);
	}

}
