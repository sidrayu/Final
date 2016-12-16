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
	private ComboBox termBox;
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
		lq.setIncome(Integer.valueOf(txtIncome.getText()));
		lq.setExpenses(Integer.valueOf(txtExpense.getText()));
		lq.setdAmount(Double.valueOf(txtCost.getText()));
		lq.setiCreditScore(Integer.valueOf(txtCredit.getText()));
		if (termBox.getValue() == "15 Year")
			lq.setiTerm(12*15);
		else
			lq.setiTerm(12*30);
		
		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		// TODO - RocketClient.HandleLoanRequestDetails
		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment)
		// should be calculated.
		// Display dPayment on the form, rounded to two decimal places

	}

	ObservableList<String> list = FXCollections.observableArrayList("15 Year", "30 Year");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//cboxTerm.setItems(list);
		
	}
}
