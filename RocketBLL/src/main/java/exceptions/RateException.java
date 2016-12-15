package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	int creditScore;
	
	public RateException(int credit) {
		this.creditScore = credit;
	}
	
	public RateException(int credit, String message) {
		super(message);
		this.creditScore = credit;
	}
	
	public int getcreditScore() {
		return creditScore;
	}
}
