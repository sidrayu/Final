package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	@Test
	public void test() {
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
		
	}
	
	@Test
	public void rate_test() {
		//TODO - RocketDAL rate_test
		//		Check to see if a known credit score returns a known interest rate
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		for(RateDomainModel r : rates){
            assertTrue(r.getiMinCreditScore() != 600 || r.getdInterestRate() == 5.0);
            assertTrue(r.getiMinCreditScore() != 650 || r.getdInterestRate() == 4.5);
            assertTrue(r.getiMinCreditScore() != 700 || r.getdInterestRate() == 4.0);
            assertTrue(r.getiMinCreditScore() != 750 || r.getdInterestRate() == 3.75);
            assertTrue(r.getiMinCreditScore() != 800 || r.getdInterestRate() == 3.5);
		}
	}
	
//	@Test
//	public void rateException_test() {
//		//TODO - RocketDAL rate_test
//		//		Check to see if a RateException is thrown if there are no rates for a given
//		//		credit score
//
//		/try {
//			ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
//			if(rates == null){
//				throw new RateException();
//			}
//			
//		} catch (RateException e) {
//			System.out.println("There is no credit score.");
//		}
//	}
}
