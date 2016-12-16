package rocketBase;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	@Test
	public void test() {

		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println("Rates size: " + rates.size());
		assert (rates.size() > 0);

	}

	@Test
	public void rate_test() {
		// TODO - RocketDAL rate_test
		// Check to see if a known credit score returns a known interest rate
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		for (RateDomainModel r : rates) {
			assertTrue(r.getiMinCreditScore() != 600 || r.getdInterestRate() == 5.0);
			assertTrue(r.getiMinCreditScore() != 650 || r.getdInterestRate() == 4.5);
			assertTrue(r.getiMinCreditScore() != 700 || r.getdInterestRate() == 4.0);
			assertTrue(r.getiMinCreditScore() != 750 || r.getdInterestRate() == 3.75);
			assertTrue(r.getiMinCreditScore() != 800 || r.getdInterestRate() == 3.5);
		}
	}

	@Test(expected=RateException.class)
	public void rateException_test() throws RateException{
		// TODO - RocketDAL rate_test
		// Check to see if a RateException is thrown if there are no rates for a
		// given
		// credit score

		int nonExistCredit = -1;
		boolean isValidCredit = false;
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();

		for (RateDomainModel r : rates) {
			if (r.getdInterestRate() == nonExistCredit) {
				isValidCredit = true;
				break;
			}
		}
		if (!isValidCredit)
			throw new RateException(nonExistCredit);
	}

	protected class RateException extends Exception {
		private static final long serialVersionUID = 1L;

		RateException(int credit) {
			super("Can NOT get the interest rate for the given credit: " + credit);
		}
	}
}
