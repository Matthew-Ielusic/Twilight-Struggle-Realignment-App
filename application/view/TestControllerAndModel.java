package application.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import application.logic.Possibility;
import application.logic.PossibilityManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import utils.DoubleMap;
import utils.TupleInt;

public class TestControllerAndModel {

	
	@FXML
	private TextField usInfInput;
	
	private int usInfluence;
	
	@FXML
	private TextField ussrInfInput;
	
	private int ussrInfluence;
	
	@FXML
	private TextField usAdjInput;
	
	private int usAdjacent;
	
	@FXML
	private TextField ussrAdjInput;
	
	private int ussrAdjacent;
	
	@FXML
	private TextField stabInput;
	
	private int stability;

	
	@FXML
	private GridPane output;
	
	@FXML
	private Label usInf_First;
	
	@FXML
	private Label ussrInf_First;
	
	@FXML
	private Label us_Elm_First;
	
	@FXML
	private Label ussr_Elm_First;
	
	@FXML
	private Label usCtl_First;
	
	@FXML
	private Label ussrCtl_First;
	
	@FXML
	private Label usInf_Second;
	
	@FXML
	private Label ussrInf_Second;
	
	@FXML
	private Label us_Elm_Second;
	
	@FXML
	private Label ussr_Elm_Second;
	
	@FXML
	private Label usCtl_Second;
	
	@FXML
	private Label ussrCtl_Second;
	
	@FXML
	private Label usInf_Third;
	
	@FXML
	private Label ussrInf_Third;
	
	@FXML
	private Label us_Elm_Third;
	
	@FXML
	private Label ussr_Elm_Third;
	
	@FXML
	private Label usCtl_Third;
	
	@FXML
	private Label ussrCtl_Third;
	
	@FXML
	private Label usInf_Fourth;
	
	@FXML
	private Label ussrInf_Fourth;
	
	@FXML
	private Label us_Elm_Fourth;
	
	@FXML
	private Label ussr_Elm_Fourth;
	
	@FXML
	private Label usCtl_Fourth;
	
	@FXML
	private Label ussrCtl_Fourth;
	
	@FXML
	private GridPane dataGrid;
	
	@FXML
	private void updateData() {

		int maxValueForTextField = 99;
		String s = usInfInput.getText();
		if (isInt(s)) {
			int i = new Integer(s);
			if (i <= maxValueForTextField) 
				usInfluence = new Integer(s);
			else
				usInfInput.deletePreviousChar();
		} else {
			usInfInput.deletePreviousChar();
			if (usInfInput.getText().isEmpty()) {
				usInfluence = 0;
			}
		}
		
		s = ussrInfInput.getText();
		if (isInt(s)) {
			int i = new Integer(s);
			if (i <= maxValueForTextField) 
				ussrInfluence = new Integer(s);
			else
				ussrInfInput.deletePreviousChar();
		} else {
			ussrInfInput.deletePreviousChar();
			if (ussrInfInput.getText().isEmpty()) {
				ussrInfluence = 0;
			}
		}
		
		s = usAdjInput.getText();
		if (isInt(s)) {
			int i = new Integer(s);
			if (i <= maxValueForTextField) 
				usAdjacent = new Integer(s);
			else
				usAdjInput.deletePreviousChar();
		} else {
			usAdjInput.deletePreviousChar();
			if (usAdjInput.getText().isEmpty()) {
				usAdjacent = 0;
			}
		}
		
		s = ussrAdjInput.getText();
		if (isInt(s)) {
			int i = new Integer(s);
			if (i <= maxValueForTextField) 
				ussrAdjacent = new Integer(s);
			else
				ussrAdjInput.deletePreviousChar();
		} else {
			ussrAdjInput.deletePreviousChar();
			if (ussrAdjInput.getText().isEmpty()) {
				ussrAdjacent = 0;
			}
		}
		
		s = stabInput.getText();
		if (isInt(s)) {
			int i = new Integer(s);
			if (i <= maxValueForTextField) 
				stability = new Integer(s);
			else
				stabInput.deletePreviousChar();
		} else {
			stabInput.deletePreviousChar();
			if (stabInput.getText().isEmpty()) {
				stability = -1;
			}
		} 
		
		Possibility.setUSControlledAdjacent(usAdjacent);
		Possibility.setUSSRControlledAdjacent(ussrAdjacent);
		
		logic.update(usInfluence, ussrInfluence);

		double[] afterOne = this.getDataFromOddsMap(logic.oddsMapFirst);
		double[] afterTwo = this.getDataFromOddsMap(logic.oddsMapSecond);
		double[] afterThree = this.getDataFromOddsMap(logic.oddsMapThird);
		double[] afterFourth = this.getDataFromOddsMap(logic.oddsMapFourth);

		//For the meaning of these arrays, and the following string of magic numbers, see the documentation
		//for getDataFromOddsMap.
		
		this.formatAverage(this.usInf_First, afterOne[0]);
		this.formatAverage(this.ussrInf_First, afterOne[1]);
		
		this.formatProbability(this.us_Elm_First,afterOne[2]);
		this.formatProbability(this.ussr_Elm_First,afterOne[3]);
		
		this.formatProbability(this.usCtl_First, afterOne[4]);
		this.formatProbability(this.ussrCtl_First, afterOne[5]);
		
		this.formatAverage(this.usInf_Second, afterTwo[0]);
		this.formatAverage(this.ussrInf_Second, afterTwo[1]);
		
		this.formatProbability(this.us_Elm_Second,afterTwo[2]);
		this.formatProbability(this.ussr_Elm_Second,afterTwo[3]);
		
		this.formatProbability(this.usCtl_Second, afterTwo[4]);
		this.formatProbability(this.ussrCtl_Second, afterTwo[5]);
		
		this.formatAverage(this.usInf_Third, afterThree[0]);
		this.formatAverage(this.ussrInf_Third, afterThree[1]);
		
		this.formatProbability(this.us_Elm_Third,afterThree[2]);
		this.formatProbability(this.ussr_Elm_Third,afterThree[3]);
		
		this.formatProbability(this.usCtl_Third, afterThree[4]);
		this.formatProbability(this.ussrCtl_Third, afterThree[5]);
		
		
		this.formatAverage(this.usInf_Fourth, afterFourth[0]);
		this.formatAverage(this.ussrInf_Fourth, afterFourth[1]);
		
		this.formatProbability(this.us_Elm_Fourth,afterFourth[2]);
		this.formatProbability(this.ussr_Elm_Fourth,afterFourth[3]);
		
		this.formatProbability(this.usCtl_Fourth, afterFourth[4]);
		this.formatProbability(this.ussrCtl_Fourth, afterFourth[5]);
		
		

	}
	
	/**
	 * 
	 * @return Returns an array of doubles with the following values:
	 *  0: Average US influence
	 *  1: Average USSR influence
	 *  2: Probability (less than 1) that US is eliminated
	 *  3: Probability (less than 1) that USSR is eliminated
	 *  4: Probability that US controls target country
	 *  5: Probability that USSR controls target country.
	 */
	private double[] getDataFromOddsMap(DoubleMap<TupleInt> oddsMap) {
		double[] returnValue = new double[6];
		for (TupleInt key : oddsMap.keySet()) {
			double probability = oddsMap.get(key);
			returnValue[0] += key.x * probability;
			returnValue[1] += key.y * probability;
			
			if (key.x == 0) {
				returnValue[2] += probability;
			}
			if (key.y == 0) {
				returnValue[3] += probability;
			}
			
			if (key.x - key.y >= stability) {
				returnValue[4] += probability;
			} else if (key.y - key.x >= stability) {
				returnValue[5] += probability;
			}
		}
		return returnValue;
	}
	
	private NumberFormat formatter = new DecimalFormat("#0.00");     

	private void formatAverage(Label label, double d) {
		label.textProperty().set(formatter.format(d));
	}
	
	private void formatProbability(Label label, double d) {
		label.textProperty().set(formatter.format(d * 100) + "%");
	}
	
	
	public static boolean isInt(String str)
	{
	 return str.matches("([0-9])([0-9]*)");  //match a number
	
	}
	
	private PossibilityManager logic = new PossibilityManager();
	
	@FXML
	private void initialize() {
		ChangeListener<String> def = new UpdateDataListener<>(this);
	/*	this.stabInput.textProperty().addListener(def);
		this.usInfInput.textProperty().addListener(def);
		this.ussrInfInput.textProperty().addListener(def);
		this.usAdjInput.textProperty().addListener(def);
		this.ussrAdjInput.textProperty().addListener(def);*/
		
		
		Integer defaultStability = 2;
		this.stabInput.textProperty().set(defaultStability.toString());
		this.updateData();
	}
	
	class UpdateDataListener<T> implements ChangeListener<T> {
		private TestControllerAndModel mdl;
		
		UpdateDataListener(TestControllerAndModel in) {
			this.mdl = in;
		}
		
		@Override
		public void changed(ObservableValue<? extends T> obs, T newv, T oldv) {
			mdl.updateData();
		}
	}

}



