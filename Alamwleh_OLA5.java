import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Alamwleh_OLA5 extends Application {
    private TextField tfAmountToGamble = new TextField();
    private Button btSpin = new Button("Spin!");
    private double totalMoneySpent = 0.0;
    private double totalWon = 0.0;
    private double amountWonRound = 0.0;
    private String interActiveText = "Insert an amount to play";
    private Label lblAmountInterested = new Label("Wager Amount: $");
    private Label lblAmountWonThisSpin = new Label("Amount won from this spin: $" + amountWonRound);
    private Label lblTotalAmountWon = new Label("Total amount won: $" + totalWon);
    private Label lblTotalMoneySpent = new Label("So far you spent: $" + totalMoneySpent);
    private Label lblInterActiveText = new Label(interActiveText);
    private String cardPath = "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/b2fv.png";
    private Image card1  = new Image(cardPath);
    private ImageView card1View  = new ImageView(card1);
    private Image card2  = new Image(cardPath);
    private ImageView card2View = new ImageView(card2);
    private Image card3  = new Image(cardPath);
    private ImageView card3View = new ImageView(card3);
    private Image card4  = new Image(cardPath) ;
    private ImageView card4View = new ImageView(card4);

    @Override
    public void start(Stage primaryStage) {

        // create a box to hold the cards
        HBox cardBox = new HBox(10);
        cardBox.getChildren().addAll(card1View,card2View,card3View,card4View);
        cardBox.setAlignment(Pos.CENTER); // center them

        // text box for "amount interested$" and the enterable amount
        HBox textBox = new HBox(10);
        textBox.getChildren().addAll(lblAmountInterested,tfAmountToGamble);
        textBox.setAlignment(Pos.CENTER);// center them

        VBox rootVBox = new VBox(10); // 10 pixels spacing between children
        rootVBox.setAlignment(Pos.CENTER);
        // Process events
        btSpin.setOnAction(event -> gamblingProcess());
        // add childeren to box
        rootVBox.getChildren().addAll(cardBox,textBox, lblAmountWonThisSpin,lblTotalAmountWon,lblTotalMoneySpent,btSpin,lblInterActiveText);

        // set the scene
        Scene scene = new Scene(rootVBox, 400, 400); // create a box
        primaryStage.setTitle("Gambling Spinner");
        primaryStage.setScene(scene); // add the scene to the stage
        primaryStage.show(); // show the stage
    }

    // this method does the whole gambling process. It creates 4 random numbers between 1 and 13 (ace through king) and 
    // creates a tally for the number of matches. it updates the text boxes in the GUI. It calls on the method "imageFinder" to
    // find the image corresponding with the random number then updates the image.
    private void gamblingProcess() {
        try {
            double wager = Double.parseDouble(tfAmountToGamble.getText());
            if (wager > 0) {
                // Create a Random object
                Random random = new Random();
                // Generate a random number between 1 and 13
                int ranNum1 = random.nextInt(13) + 1;
                int ranNum2 = random.nextInt(13) + 1;
                int ranNum3 = random.nextInt(13) + 1;
                int ranNum4 = random.nextInt(13) + 1;
                // Compare the random numbers and keep a tally
                int matchTally = 0;
                if (ranNum1 == ranNum2 || ranNum1 == ranNum3 || ranNum1 == ranNum4) {
                    matchTally++;
                }
                if (ranNum2 == ranNum3 || ranNum2 == ranNum4) {
                    matchTally++;
                }
                if (ranNum3 == ranNum4) {
                    matchTally++;
                }

                totalMoneySpent += wager;  // update the total spent

                if(matchTally == 0){ // no matches
                    amountWonRound = 0; // no money won :(
                    interActiveText = "No luck. Play Again!";
                }
                else if(matchTally == 1){ // 2 of a kind;
                    amountWonRound = wager*2; // double it for money won
                    totalWon += amountWonRound; // add the amount won from the round to the totalWon
                    interActiveText = "Sweet! DOUBLE WIN x2!!";
                } 
                else if(matchTally == 2) {// 3 of a king
                    amountWonRound = wager*3; //triple the money won
                    totalWon += amountWonRound; // add the amount won from the round to the totalWon
                    interActiveText = "Sweet! Triple WIN x3!!";
                }
                else { // 4 of a kind!!!!
                    amountWonRound = wager*4; // 4 times the amount of money put in
                    totalWon += amountWonRound; // add the amount won from the round to the totalWon
                    interActiveText = "Jackpot! Quadruple WIN x4!!";
                }

                // resend all the info back to the GUI
                lblAmountInterested.setText("Wager Amount: $" + wager);
                lblAmountWonThisSpin.setText("Amount won from this spin: $" + amountWonRound);
                lblTotalAmountWon.setText("Total amount won: $" + totalWon);
                lblTotalMoneySpent.setText("So far you spent: $" + totalMoneySpent);
                lblInterActiveText.setText(interActiveText);
                // Update the card images
                card1 = new Image(filePathFinder(ranNum1));
                card2 = new Image(filePathFinder(ranNum2));
                card3 = new Image(filePathFinder(ranNum3));
                card4 = new Image(filePathFinder(ranNum4));
                // Update the ImageView objects with the new images
                card1View.setImage(card1);
                card2View.setImage(card2);
                card3View.setImage(card3);
                card4View.setImage(card4);
            }
            else
                lblInterActiveText.setText("Invalid input. Please enter a postive value."); // NO NEGATIVE VALUES!!
        
        } catch (NumberFormatException e) {
            lblInterActiveText.setText("Invalid input. Please enter a numeric value."); // NO NON NUMBER VALUES!!
        }
    }

    // this method takes the random number from the gamblingProcess as a parameter
    // and finds the correct image. Once the correct image is found the path to the image 
    // is sent back to the gamlingProcess method to update the GUI.
    private String filePathFinder(int num){
        if (num == 1) 
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/1.png";
        else if(num == 2)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/2.png";
        else if(num == 3)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/3.png";
        else if(num == 4)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/4.png";
        else if(num == 5)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/5.png";
        else if(num == 6)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/6.png";
        else if(num == 7)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/7.png";
        else if(num == 8)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/8.png";
        else if(num == 9)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/9.png";
        else if(num == 10)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/10.png";
        else if(num == 11)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/11.png";
        else if(num == 12)
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/12.png";
        else 
            return "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/13.png";
    }
    public static void main(String[] args) {
        launch(args); // launch the GUI
    }
}