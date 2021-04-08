import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.*;
import java.util.*;
import java.sql.*;

public class Controller {
    private DBmanager db = new DBmanager();


    public ArrayList<String> getQueryResult(String query){
        ArrayList<String> result = new ArrayList<String>();
        try{
            ResultSet rs = db.executeSQL(query);
            String line = "";
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()){
                if (cols > 1){
                    for (int i = 1; i <= cols; i++) {
                        line += rs.getString(i);
                        line += " ";
                        System.out.println("line " + i + ": " + rs.getString(i));
                    }
                    result.add(line);
                    line = "";
                }
                else{
                    result.add(rs.getString(1));
                    line = "";
                }
            }
            //printResult(result);
            return result;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }       
    }

    public void printList(ArrayList<String> res){
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    public ArrayList<ImageView> displayImages(String[] imageURLList){
        System.out.println("entering displayImages " + imageURLList[0].toString());
        ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();

        for (int i = 0; i < imageURLList.length; i++) {
            Image image = new Image(imageURLList[i], 200, 250, true, false);
            ImageView iv = new ImageView();
            iv.setImage(image);
            imageViewList.add(iv);
        }

        for (ImageView imageView : imageViewList) {
            //imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } 
        System.out.println(imageViewList.toString());
        return imageViewList;
    }


    public ArrayList<ImageView> displayImagesLarge(String[] imageURLList){
        System.out.println("entering displayImages " + imageURLList[0].toString());
        ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();

        for (int i = 0; i < imageURLList.length; i++) {
            Image image = new Image(imageURLList[i], 650, 325, true, false);
            ImageView iv = new ImageView();
            iv.setImage(image);
            imageViewList.add(iv);
        }

        for (ImageView imageView : imageViewList) {
            //imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } 
        System.out.println(imageViewList.toString());
        return imageViewList;
    }

    public ArrayList<ImageView> displayImagesPortrait(String[] imageURLList){
        System.out.println("entering displayImages " + imageURLList[0].toString());
        ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();

        for (int i = 0; i < imageURLList.length; i++) {
            Image image = new Image(imageURLList[i], 400, 500, true, false);
            ImageView iv = new ImageView();
            iv.setImage(image);
            imageViewList.add(iv);
        }

        for (ImageView imageView : imageViewList) {
            //imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } 
        System.out.println(imageViewList.toString());
        return imageViewList;
    }
    
}
