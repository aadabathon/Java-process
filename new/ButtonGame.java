import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import java.util.Random;

public class ButtonGame extends Application {

        private int score = 0;
        private scoreLabel new Label("Score: 0");
        private Random random = new Random();

        private void scrambleButtons(Random rando, Buttons[] buttons) {
        for (Button button : buttons) {
                button.setLayoutX(rando.nextDouble() * 500);
                button.setLayoutY(rando.nextDouble() * 400);
                }
        }

    @Override
    public void start(final Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("ButtonGame");
        stage.setScene(scene);

        root.setTop(scoreLabel);


        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> Platform.exit());
        root.setBottom(exitButton);

        Pane center = new Pane();
        centerPane.setPrefSize(500, 400);
        root.setCenter(center);

        Button[] buttons = new Button[9];

        while (int i = 0 < 9){
                Button button;
                if (i == 7) {
                        button = new Button("Click me!");
                } else {
                        button = new Button("Click me?"):
                }
                buttons[i] = button;
                center.getChildren().add(button);
                i++
        }

        Button target = buttons[7];

        target.setOnAction(e -> {score++;
                scoreLabel.setText("Score: " + score);
                scrambleButtons(random, buttons);
                exitButton.requestFocus();}
        );

        for(int i = 0; i < 9; i++) {
                if(i == 7) continue;
                Button button = buttons[i]
                button.setOnAction(e -> {score--;
                        scoreLabel.setText("Score: " + score);
                        scrambleButtons(random, buttons);
                        exitButton.requestFocus();}
                );
        }

        scrambleButtons(random, buttons);
        stage.show();
        exitButton.requestFocus();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}