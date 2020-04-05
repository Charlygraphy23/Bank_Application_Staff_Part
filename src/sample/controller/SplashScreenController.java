package sample.controller;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class SplashScreenController {

    @FXML
    private Circle logo;

    @FXML
    void initialize() {


        logo.setFill(new ImagePattern(new Image("/sample/assets/biz_slt_bankABC.max-752x423.jpg")));
        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(3));
        fadeTransition.setNode(logo);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        PauseTransition pauseTransition=new PauseTransition(Duration.seconds(6));
        pauseTransition.setOnFinished(e->{
            logo.getScene().getWindow().hide();
            try {
                Parent root= FXMLLoader.load(getClass().getResource("/sample/view/adminsignupview.fxml"));
                Stage stage=new Stage(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root));
                stage.getIcons().addAll(new Image("/sample/assets/biz_slt_bankABC.max-752x423.jpg"));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pauseTransition.play();



    }
}
