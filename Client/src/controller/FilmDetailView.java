package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.BeanInvoker;

import java.util.logging.Level;

public class FilmDetailView {


    public FilmDetailView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.FILM_DETAIL));
       // ScreenManager.createScene("Film Detail",loader);
        try {
            System.out.println("Film Detail1");
            Parent root=loader.load();
            Stage stage = new Stage();
            stage.initOwner(ScreenManager.getStage());
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene =new Scene(root);
            stage.setTitle("Film Detail");
            stage.setScene(scene);
            stage.show();
            System.out.println("Film Detail2");
        }
        catch (javafx.fxml.LoadException e) {
            BeanInvoker.logger.log(Level.FINE,"Ocakavana vynimka pri vytvarani okna \n");
        }
        catch (Exception e) {
            BeanInvoker.logger.log(Level.SEVERE,"Chyba pri vytvoreni sceny "+"Film Detail"+" \n", e);
            System.out.println("A mozno ano");
            e.printStackTrace();
        }
    }
}
