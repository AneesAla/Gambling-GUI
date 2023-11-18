import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GamblingSpinnerGame extends Application {

  private TextField tfBetAmount = new TextField();
  private Button btnSpin = new Button("Spin!");
  private double totalSpent = 0.0;
  private double totalWon = 0.0;
  private double wonThisRound = 0.0;
  private String gameMessage = "Insert an amount to play";
  private Label lblBetAmount = new Label("Wager Amount: $");
  private Label lblWonThisSpin = new Label("Amount won from this spin: $0.0");
  private Label lblTotalWon = new Label("Total amount won: $0.0");
  private Label lblTotalSpent = new Label("So far you spent: $0.0");
  private Label lblGameMessage = new Label(gameMessage);

  private ImageView[] cardViews = new ImageView[4];
  private final String basePath =
    "file:///C:/Users/Anees/OneDrive/Desktop/VSCode-For-Java/Ola6/card/";

  @Override
  public void start(Stage primaryStage) {
    for (int i = 0; i < cardViews.length; i++) {
      cardViews[i] = new ImageView(new Image(basePath + "b2fv.png"));
    }

    HBox cardBox = new HBox(10, cardViews);
    cardBox.setAlignment(Pos.CENTER);

    HBox textBox = new HBox(10, lblBetAmount, tfBetAmount);
    textBox.setAlignment(Pos.CENTER);

    VBox root = new VBox(
      10,
      cardBox,
      textBox,
      lblWonThisSpin,
      lblTotalWon,
      lblTotalSpent,
      btnSpin,
      lblGameMessage
    );
    root.setAlignment(Pos.CENTER);

    btnSpin.setOnAction(event -> gamblingProcess());

    primaryStage.setTitle("Gambling Spinner");
    primaryStage.setScene(new Scene(root, 400, 400));
    primaryStage.show();
  }

  private void gamblingProcess() {
    try {
      double bet = Double.parseDouble(tfBetAmount.getText());
      if (bet > 0) {
        Random random = new Random();
        int matches = 0;
        int[] cards = new int[4];
        for (int i = 0; i < cards.length; i++) {
          cards[i] = random.nextInt(13) + 1;
          for (int j = 0; j < i; j++) {
            if (cards[i] == cards[j]) {
              matches++;
              break;
            }
          }
          cardViews[i].setImage(new Image(filePathFinder(cards[i])));
        }

        totalSpent += bet;
        wonThisRound = bet * (matches + 1);
        totalWon += wonThisRound;
        gameMessage =
          matches > 0
            ? "Sweet! " + (matches + 1) + "x WIN!!"
            : "No luck. Play Again!";

        lblBetAmount.setText("Wager Amount: $" + bet);
        lblWonThisSpin.setText("Amount won from this spin: $" + wonThisRound);
        lblTotalWon.setText("Total amount won: $" + totalWon);
        lblTotalSpent.setText("So far you spent: $" + totalSpent);
        lblGameMessage.setText(gameMessage);
      } else {
        lblGameMessage.setText("Invalid input. Please enter a positive value.");
      }
    } catch (NumberFormatException e) {
      lblGameMessage.setText("Invalid input. Please enter a numeric value.");
    }
  }

  private String filePathFinder(int cardNumber) {
    return basePath + cardNumber + ".png";
  }

  public static void main(String[] args) {
    launch(args);
  }
}
