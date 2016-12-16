package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;

public class RocketHub extends Hub {
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");

		if (message instanceof LoanRequest) {
			resetOutput();

			LoanRequest lq = (LoanRequest) message;

			// TODO - RocketHub.messageReceived

			// You will have to:
			// Determine the rate with the given credit score (call
			// RateBLL.getRate)
			// If exception, show error message, stop processing
			// If no exception, continue
			// Determine if payment, call RateBLL.getPayment
			//
			// you should update lq, and then send lq back to the caller(s)

			try {
				double loadRate = RateBLL.getRate(lq.getiCreditScore());
				double newPayment = RateBLL.getPayment(loadRate/1200, lq.getiTerm(), lq.getdAmount(), 0, false);
				lq.setdRate(loadRate);
				lq.setdPayment(newPayment);
			} catch (RateException e) {
				e.printStackTrace();
				System.out.println("Can NOT get the rate with the given credit: " + lq.getiCreditScore());
			}

			sendToAll(lq);
		}

	}
}
