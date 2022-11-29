package wholeworthsgrocery;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class GroceryController implements Initializable {

    @FXML
    private TextField balanceTotal;

    @FXML
    private TextField messageBox;

    @FXML
    private TextField breadQty;

    @FXML
    private TextField breadTotal;

    @FXML
    private TextField eggQty;

    @FXML
    private TextField eggsTotal;

    @FXML
    private TextField milkQty;

    @FXML
    private TextField milkTotal;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (validate(breadQty.getText()) && validate(milkQty.getText()) && validate(eggQty.getText())) {
            try {
                int eggsQty = Integer.valueOf(eggQty.getText());// Getting the integer value of Qty
                int breadsQty = Integer.valueOf(breadQty.getText());
                int milkQtyI = Integer.valueOf(milkQty.getText());
                int disEggs = eggsQty / 12;// Getting Discounted Items
                int disBreads = breadsQty / 2;
                int disMilk = milkQtyI / 3;
                int remEggs = eggsQty % 12;// Getting Remaining Actual price or non discounted item 
                int remBread = breadsQty % 2;
                int remMilk = milkQtyI % 3;
                double totalDue;
                if (eggsQty <= 11) {
                    eggsTotal.setText("$" + Double.toString(eggsQty * 0.50));
                }
                if (eggsQty > 11) {
                    eggsTotal.setText("$" + Double.toString((disEggs * 5.00) + (remEggs * 0.50)));
                }
                if (breadsQty < 2) {
                    breadTotal.setText("$" + Double.toString(breadsQty * 3.50));
                }
                if (breadsQty >= 2) {
                    breadTotal.setText("$" + Double.toString((disBreads * 6.00) + (remBread * 3.50)));
                }
                if (milkQtyI < 3) {
                    milkTotal.setText("$" + Double.toString(milkQtyI * 2.50));
                }
                if (milkQtyI >= 3) {
                    milkTotal.setText("$" + Double.toString((disMilk * 7.00) + (remMilk * 2.50)));
                }
                totalDue = Double.valueOf(eggsTotal.getText().replace("$", "0")) + Double.valueOf(breadTotal.getText().replace("$", "0")) + Double.valueOf(milkTotal.getText().replace("$", "0"));
                balanceTotal.setText("$" + totalDue);
            } catch (NumberFormatException e) {
                breadQty.setText("0");
                milkQty.setText("0");
                eggQty.setText("0");
                breadTotal.setText("0");
                eggsTotal.setText("0");
                milkTotal.setText("0");
                balanceTotal.setText("0");
                messageBox.setText("Not A Valid Qty ");
            }

        } else// replace invalid input to zero
        {
            breadQty.setText("0");
            milkQty.setText("0");
            eggQty.setText("0");
            breadTotal.setText("0");
            eggsTotal.setText("0");
            milkTotal.setText("0");
            balanceTotal.setText("0");
            messageBox.setText("Not A Valid Qty ");
        }

    }

    @FXML
    void handlePayAction(ActionEvent event) {
        TextInputDialog td = new TextInputDialog();
        if ((balanceTotal.getText()).equals("") || balanceTotal.getText().equals("0") || balanceTotal.getText().equals("$0.0")) {
            messageBox.setText("Nothing to Pay");
        } else {
            try {

                int randomSave = +randomPin();
                td.setTitle("Customer Account: ");
                td.setHeaderText("Your Customer Pin is : " + randomSave);
                td.setContentText("Enter Customer Pin Here: ");
                td.showAndWait();
                if (Integer.valueOf(td.getEditor().getText()) == randomSave && !td.getResult().equals("")) {

                    messageBox.setText("Total Amount Paid : " + balanceTotal.getText() + " Thanks For Shopping!");
                    balanceTotal.setText("0");

                } else {

                    messageBox.setText("Payment is Denied Please Try Again: ");

                }

            } catch (NumberFormatException E) {
                messageBox.setText("Payment is Denied Please Try Again: ");
            }

        }
        td.getEditor().setText("");

    }

    public boolean validate(String text)// function to check valid input of quantity as integer
    {
        return text.matches("[0-9]*");
    }

    public int randomPin()// creating pin for customers to pay
    {
        Random rand = new Random();
        return rand.nextInt(1000, 9999);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
