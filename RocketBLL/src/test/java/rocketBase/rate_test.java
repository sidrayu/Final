package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	// TODO - RocketBLL rate_test
	// Check to see if a known credit score returns a known interest rate

	// TODO - RocketBLL rate_test
	// Check to see if a RateException is thrown if there are no rates for a
	// given
	// credit score
	@Test
	public void test() {
		assert (1 == 1);
	}

	@Test
	public void rate_test() {
		try {
			double rate = RateBLL.getRate(600);
			assertTrue(rate == 5);
		} catch (RateException e) {
			e.printStackTrace();
		}

		try {
			double rate = RateBLL.getRate(650);
			assertTrue(rate == 4.5);
		} catch (RateException e) {
			e.printStackTrace();
		}

		try {
			double rate = RateBLL.getRate(700);
			assertTrue(rate == 4);
		} catch (RateException e) {
			e.printStackTrace();
		}

		try {
			double rate = RateBLL.getRate(750);
			assertTrue(rate == 3.75);
		} catch (RateException e) {
			e.printStackTrace();
		}

		try {
			double rate = RateBLL.getRate(800);
			assertTrue(rate == 3.5);
		} catch (RateException e) {
			e.printStackTrace();
		}

	}

	@Test(expected=RateException.class)
	public void rateException_test() throws RateException {
		double rate = RateBLL.getRate(500);
	}

}
