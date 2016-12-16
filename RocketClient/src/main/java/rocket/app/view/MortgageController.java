package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable {

	private MainApp mainApp;

	// TODO - RocketClient.RocketMainController

	// Create private instance variables for:
	// TextBox - txtIncome
	// TextBox - txtExpenses
	// TextBox - txtCreditScore
	// TextBox - txtHouseCost
	// ComboBox - loan term... 15 year or 30 year
	// Labels - various labels for the controls
	// Button - button to calculate the loan payment
	// Label - to show error messages (exception throw, payment exception)

	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpense;
	@FXML
	private TextField txtCredit;
	@FXML
	private TextField txtCost;
	@FXML
	private TextField txtDPay;
	@FXML
	private Label incomeLabel;
	@FXML
	private Label expenseLabel;
	@FXML
	private Label creditLabel;
	@FXML
	private Label costLabel;
	@FXML
	private Label termLabel;
	@FXML
	private Label DPayLabel;
	@FXML
	public Label MPayLabel;
	@FXML
	public Label errorLabel;
	@FXML
	private ComboBox<String> cboxTerm;
	@FXML
	private Button btnCalc;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	// TODO - RocketClient.RocketMainController
	// Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		Object message = null;
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		// TODO - RocketClient.RocketMainController
		// set the loan request details... rate, term, amount, credit score,
		// downpayment
		// I've created you an instance of lq... execute the setters in lq
		try {
			lq.setIncome(Integer.parseInt(txtIncome.getText()));
			lq.setExpenses(Integer.parseInt(txtExpense.getText()));
			lq.setiCreditScore(Integer.parseInt(txtCredit.getText()));
			lq.setdAmount(Double.parseDouble(txtCost.getText()));
		} catch (Exception e) {
			errorLabel.setText("Exception: Please input numbers in the textfields!");
		}
		try {
			if (cboxTerm.getValue() == "15 Year")
				lq.setiTerm(15*12);
			else
				lq.setiTerm(30*12);
		} catch (Exception e) {
			errorLabel.setText("Exception: Please select a term in the list!");
		}
		
		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		// TODO - RocketClient.HandleLoanRequestDetails
		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment)
		// should be calculated.
		// Display dPayment on the form, rounded to two decimal places
		
		// I tried... I don't think the calculation is correct.

		double dPay = lRequest.getdPayment();
		double monthIncome = lRequest.getIncome() / 12.00;
		double mPay = ((int) (dPay * 100.00)) / 100.00;
		double resid = dPay - mPay;
		double income1 = monthIncome * 0.28;
		double income2 = monthIncome * 0.36 - lRequest.getExpenses();
		double finalIncome = income1 <= income2 ? income1 : income2;

		if (resid > 0.005)
			mPay = mPay + 0.01;
		if (dPay <= finalIncome)
			DPayLabel.setText("\t\t The Mortgage Payment is: $" + mPay);
		else
			MPayLabel.setText("\t\t House Cost too high");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cboxTerm.setPromptText("Select a Term");
		cboxTerm.getItems().addAll("15 Year", "30 Year");
		DPayLabel.setText("");
		errorLabel.setText("Running...");
	}
}
