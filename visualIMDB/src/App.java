import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.*;
import java.util.*;

import javax.swing.Box;

import javafx.scene.*;
import java.sql.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;


/*Kleurcodes
#ffffff wit
#c3f2f5 lichtblauw
#4fcad2 blauw
#454e9e donkerblauw
#000000 zwart
*/

public class App extends Application{

    private Controller ctrl = new Controller();

    // private ArrayList<String> imageURLList = new ArrayList<String>();
    private ArrayList<ImageView> imageList = new ArrayList<ImageView>();
    private ArrayList<ImageView> imageBlank = new ArrayList<ImageView>();
    private ArrayList<String> descriptions = new ArrayList<String>();
    private ArrayList<Button> buttonList = new ArrayList<Button>();

    private String[] btn1Images = new String[]{
        "images/frog1.jpg"
    };

    private String[] btn2Images = new String[]{
        "images/mf1.jpg",
        "images/mf2.jpg",
        "images/frog.jpg"
        //"images/frog.jpg"
    };

    private String[] btn3Images = new String[]{
        "images/aroundtheworld.jpg",
        "images/7boxes.jpg",
        "images/AngryVideoGame.jpg",
        "images/HelloAuRevoir.jpg",
        "images/ABrokenCode.jpg"
    };

    private String[] btn4Images = new String[]{
        "images/newyork.jpg"
    };

    private String[] btn5Images = new String[]{
        "images/mp,550x550,matte,ffffff,t.jpg"
    };

    private String[] btn6Images = new String[]{
        "images/Tom_Byron.jpg"
    };
   
    private String[] btn7Images = new String[]{
        "images/nina_hartley.jpg"
    };

    private String[] btn8Images = new String[]{
        "images/GoVnoEZ.jpg",
        "images/frog1.jpg",
        "images/frog.jpg",
        "images/frog1.jpg",
        "images/frog.jpg",
        "images/frog1.jpg",
        "images/frog.jpg",
        "images/frog1.jpg",
        "images/frog.jpg",
        "images/frog1.jpg"
    };

    private String[] btn9Images = new String[]{
        "images/bobby.jpg",
        "images/Peter.jpg",
        "images/michel.jpg",
        "images/Tom_Shadyac.jpg"
    };

    private String[] btn10Images = new String[]{
        "images/jinglebells.jpg"
    };

    private String[] blankImage = new String[]{
        "images/blankimg.png"
    };

    private String[] vragen = new String[]{
        "Hoeveel acteurs zijn er met de naam Wouter?",
        "In hoeveel films heeft Morgan Freeman gespeeld?",
        "Welke 5 films hebben de meeste acteurs?",
        "Hoeveel films zijn (deels) opgenomen in New York",
        "Hoeveel films zijn er per jaar uitgekomen sinds 2015?",
        "Welke acteur heeft in de meeste films gespeeld?",
        "Welke actrice heeft in de meeste films gespeeld?",
        "Welke acteurs hebben de rol van James Bond gespeeld?",
        "Welke regisseur heeft de meeste films met Jim Carrey geregisseerd?",
        "Welk nummer is het vaakst gebruikt in de soundtrack van films?"
    };

    private String query;

    VBox box = new VBox();
    VBox buttons = new VBox();
    Group root = new Group();
    TableView<CustomImage> table = new TableView<CustomImage>();
    GridPane grid = new GridPane();
    Scene scene = new Scene(root, 1080, 720, Color.web("#c3f2f5")); 
    Button btn = new Button("0");
    Button btn2 = new Button("1");
    Button btn3 = new Button("2");
    Button btn4 = new Button("3");
    Button btn5 = new Button("4");
    Button btn6 = new Button("5");
    Button btn7 = new Button("6");
    Button btn8 = new Button("7");
    Button btn9 = new Button("8");
    Button btn10 = new Button("9");
    VBox buttonsBG = new VBox();
    VBox border = new VBox();

    final Label header = new Label("IMDB Queries (duurt pittig lang soms, sorry lol)");

    @Override
    public void start(Stage primaryStage) throws Exception{
        scene.getStylesheets().add("stylesheet.css");

        buttons.setSpacing(5);
        box.setSpacing(5);
        box.setMinSize(700, 680);
        box.setMaxSize(700, 680);
        border.setMinSize(700, 680);
        border.setMaxSize(700, 680);
        buttonsBG.setMinSize(350, 1080);
        buttonsBG.setMaxSize(350, 1080);
        buttonsBG.setBackground(new Background(new BackgroundFill(Color.web("#4fcad2"), null, null)));
    
        grid.getStyleClass().add("grid");

        box.setPadding(new Insets(10,0,0,10));
        box.getChildren().addAll(header, table);

        buttonList.add(btn);
        buttonList.add(btn2);
        buttonList.add(btn3);
        buttonList.add(btn4);
        buttonList.add(btn5);
        buttonList.add(btn6);
        buttonList.add(btn7);
        buttonList.add(btn8);
        buttonList.add(btn9);
        buttonList.add(btn10);

        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setText(vragen[i]);
        }

        buttons.setLayoutX(20);
        buttons.setLayoutY(20);
        box.setLayoutX(350);
        box.setLayoutY(20);
        border.setLayoutX(350);
        border.setLayoutY(20);
        grid.setLayoutX(370);
        grid.setLayoutY(40);

        border.setStyle("-fx-border-width: 3; -fx-border-color: #454e9e; -fx-background: darkblue;");

        for (int i = 0; i < buttonList.size(); i++) {
           
            buttonList.get(i).setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    imageList.clear();
                    Object node = event.getSource();
                    Button b = (Button)node;

                    //Testen voor leeg db
                    ArrayList<String> testList = new ArrayList();
                    imageBlank.clear();
                    imageBlank = ctrl.displayImages(blankImage);
                    switch(b.getText()){
                        case "Hoeveel acteurs zijn er met de naam Wouter?": imageList = ctrl.displayImagesLarge(btn1Images);
                            query = "SELECT COUNT(DISTINCT actorname) AS count FROM tempactors WHERE actorname LIKE '%, Wouter';";
                        break;
                        case "In hoeveel films heeft Morgan Freeman gespeeld?": imageList = ctrl.displayImages(btn2Images);
                            query = "SELECT actorname, COUNT(actorName) AS count FROM tempactors WHERE actorname LIKE '%Freeman, Morgan%' > 0 AND serieormovie = 'movie' GROUP BY actorName ORDER BY(count) DESC;";
                        break;
                        case "Welke 5 films hebben de meeste acteurs?": imageList = ctrl.displayImages(btn3Images);
                            query = "SELECT title, COUNT(actorName) as AmountofActors FROM tempactors WHERE serieormovie = 'movie' AND NOT platform = '(VG)' GROUP BY title, play_year ORDER BY AmountofActors DESC LIMIT 5;";
                        break;
                        case "Hoeveel films zijn (deels) opgenomen in New York": imageList = ctrl.displayImagesLarge(btn4Images);
                            query = "SELECT COUNT(title) as recorded_in_newyork FROM templocation WHERE filming_loc LIKE '%New York%';";
                        break;
                        case "Hoeveel films zijn er per jaar uitgekomen sinds 2015?": imageList = ctrl.displayImagesLarge(btn5Images);
                            query = "SELECT movie_year,COUNT(tempmovies.title) AS amountOfMovies FROM tempmovies WHERE movie_year > 2014 AND movie_year < 2022 GROUP BY movie_year ORDER BY movie_year DESC;";
                        break;
                        case "Welke acteur heeft in de meeste films gespeeld?": imageList = ctrl.displayImagesPortrait(btn6Images);
                            query = "SELECT actorName, COUNT(actorName) as NUMFILMS FROM tempactors WHERE serieormovie = 'movie' GROUP BY actorName ORDER BY NUMFILMS DESC LIMIT 1;";
                        break;
                        case "Welke actrice heeft in de meeste films gespeeld?": imageList = ctrl.displayImagesPortrait(btn7Images);
                            query = "SELECT actressName, COUNT(actressName) as NUMFILMS FROM tempactresses WHERE serieormovie = 'movie' GROUP BY actressName ORDER BY NUMFILMS DESC LIMIT 1;";
                        break;
                        case "Welke acteurs hebben de rol van James Bond gespeeld?": imageList = ctrl.displayImages(btn8Images);
                            query = "SELECT DISTINCT actorName as JamesBond FROM tempactors WHERE played LIKE '%james bond%' LIMIT 10;";
                        break;
                        case "Welke regisseur heeft de meeste films met Jim Carrey geregisseerd?": imageList = ctrl.displayImages(btn9Images);
                            query = "SELECT directorname, COUNT(directorname) as num FROM tempdirectors RIGHT JOIN tempactors ON play_title = tempactors.title WHERE tempactors.serieormovie = 'movie' AND actorname LIKE '%Carrey, Jim%' AND NOT tempdirectors.platform = '(TV)' GROUP BY directorname ORDER BY num DESC LIMIT 4;";
                        break;
                        case "Welk nummer is het vaakst gebruikt in de soundtrack van films?": imageList = ctrl.displayImagesLarge(btn10Images);
                            query = "SELECT songtitle, COUNT(songtitle) AS count FROM tempsoundtrackmovies GROUP BY songtitle ORDER BY count DESC LIMIT 1;";
                        break;
                        default: imageList = ctrl.displayImages(btn1Images);
                        break;
                    } 
                    
                    testList.add("tekst1");
                    testList.add("tekst2");

                    root.getChildren().remove(grid);
                    grid.getChildren().clear();

                    try{
                        //descriptions = testList;
                        descriptions = ctrl.getQueryResult(query);
                        query = "";
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                    int col = 0;
                    int row = 0;

                    if(descriptions.size() > 0){
                        for (int j = 0; j < descriptions.size(); j++) {
                            System.out.println("entering image+desc loop");
                            Text text = new Text(100, 250, descriptions.get(j));

                            if(col == 3){
                                col = 0;
                                row+=2;
                            }
                            if(imageList.size() > j)
                                grid.add(imageList.get(j), col, row);
                            else
                                grid.add(imageBlank.get(0), col, row);
                            grid.add(text, col, row+1);
                            col++;
                        }
                    }
                    box.getStyleClass().add("box");
                    root.getChildren().add(grid);
                    descriptions.clear();
                    event.consume();
                }
            }); 

            buttonList.get(i).setMinHeight(50);
            buttonList.get(i).setMaxWidth(300);
            buttonList.get(i).setMinWidth(300);
            buttonList.get(i).wrapTextProperty().setValue(true);
            buttons.getChildren().add(buttonList.get(i));
        }; 

        buttons.setSpacing(20);
        root.getChildren().add(buttonsBG);
        root.getChildren().add(border);
        root.getChildren().add(buttons);
        primaryStage.setTitle("VisualIMDB");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
