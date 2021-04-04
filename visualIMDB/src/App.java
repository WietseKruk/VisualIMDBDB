import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.*;
import java.util.*;
import javafx.scene.*;
import java.sql.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;

public class App extends Application{

    private Controller ctrl = new Controller();

    // private ArrayList<String> imageURLList = new ArrayList<String>();
    private ArrayList<ImageView> imageList = new ArrayList<ImageView>();
    private ArrayList<String> descriptions = new ArrayList<String>();
    private ArrayList<Button> buttonList = new ArrayList<Button>();

    private String[] btn1Images = new String[]{
        "images/frog1.jpg"
    };

    private String[] btn2Images = new String[]{
        "images/mf1.jpg",
        "images/mf2.jpg",
        "images/frog.jpg",
        "images/frog.jpg"
    };

    private String[] btn3Images = new String[]{
        "images/aroundtheworld.jpg",
        "images/7boxes.jpg",
        "images/AngryVideoGame.jpg",
        "images/HelloAuRevoir.jpg",
        "images/ABrokenCode.jpg"
    };

    private String[] btn4Images = new String[]{
        "images/maxresdefault.jpg"
    };

    private String[] btn5Images = new String[]{
        "images/mp,550x550,matte,ffffff,t.jpg"
    };

    private String[] btn6Images = new String[]{
        "images/p7mi3mo31x2z.jpg"
    };

    private String[] btn7Images = new String[]{
        "images/GoVnoEZ.jpg",
        "images/frog1.jpg"
    };

    private String[] btn8Images = new String[]{
        "images/GoVnoEZ.jpg",
        "images/frog1.jpg"
    };

    private String[] btn9Images = new String[]{
        "images/GoVnoEZ.jpg",
        "images/frog1.jpg"
    };

    private String[] btn10Images = new String[]{
        "images/GoVnoEZ.jpg",
        "images/frog1.jpg"
    };

    private String[] vragen = new String[]{
        "Hoeveel acteurs zijn er met de naam Wouter?",
        "In hoeveel films heeft Morgan Freeman gespeeld?",
        "Welke 5 films hebben de meeste acteurs?",
        "Hoe lang duurt een film gemiddeld per genre (gesorteerd op jaar)",
        "In welk jaar zijn de meeste films uitgekomen (per genre)?",
        "Welke acteur/actrice heeft in de meeste films/series gespeeld?",
        "Hoeveel acteurs zijn er met de voornaam Max?",
        "Welke acteurs hebben de rol van James Bond gespeeld?",
        "Welke regisseur heeft de meeste films met Jim Carrey geregisseerd?",
        "Welk nummer is het vaakst gebruikt in de soundtrack van films?"
    };

    private String query;

    VBox box = new VBox();
    VBox buttons = new VBox();
    Group root = new Group();
    TableView<CustomImage> table = new TableView<CustomImage>();
    Scene scene = new Scene(root, 800, 1000); 
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

    final Label header = new Label("IMDB Queries (duurt pittig lang soms, sorry lol)");
    final int fontSize = 30;

    @Override
    public void start(Stage primaryStage) throws Exception{
        scene.getStylesheets().add("stylesheet.css");

        buttons.setMaxWidth(400);
        buttons.setSpacing(5);
        box.setSpacing(5);
        box.setMinSize(300, 1000);
        box.setMaxSize(300, 1000);
        
        table.setEditable(true);
        TableColumn<CustomImage, ImageView> imageCol = new TableColumn<CustomImage, ImageView>("Image");
        TableColumn<CustomImage, ImageView> descCol = new TableColumn<CustomImage, ImageView>("Description");
        table.getColumns().addAll(imageCol, descCol);

        box.setPadding(new Insets(10,0,0,10));
        box.getChildren().addAll(header, table);

        // btn.setText("Hoeveel acteurs zijn er met de naam Max?");
        // btn2.setText("In hoeveel films heeft Morgan Freeman gespeeld?");
        // btn3.setText("Hoeveel acteurs zitten er gemiddeld in een film?");
        // btn4.setText("Hoe lang duurt een film gemiddeld per genre (gesorteerd op jaar)");
        // btn5.setText("In welk jaar zijn de meeste films uitgekomen (per genre)?");
        // btn6.setText("Welke acteur/actrice heeft in de meeste films/series gespeeld?");
        // btn7.setText("Hoeveel acteurs zijn er met de voornaam Max?");
        // btn8.setText("Welke acteurs hebben de rol van James Bond gespeeld?");
        // btn9.setText("Welke regisseur heeft de meeste films met Jim Carrey geregisseerd?");
        // btn10.setText("Welk nummer is het vaakst gebruikt in de soundtrack van films?");

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

        box.setLayoutX(400);

        box.setStyle("-fx-border-width: 3; -fx-border-color: black; -fx-background: darkblue;");

        for (int i = 0; i < buttonList.size(); i++) {

        buttonList.get(i).setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                imageList.clear();
                Object node = event.getSource();
                Button b = (Button)node;
                //System.out.println("ButtonText: " + b.getText());
                //System.out.println(button1Images.size());

                switch(b.getText()){
                    case "Hoeveel acteurs zijn er met de naam Wouter?": imageList = ctrl.displayImages(btn1Images);
                        query = "SELECT COUNT(DISTINCT actorname) AS count FROM tempactors WHERE actorname LIKE '%, Wouter';";
                    break;
                    case "In hoeveel films heeft Morgan Freeman gespeeld?": imageList = ctrl.displayImages(btn2Images);
                        query = "SELECT actorname, COUNT(actorName) AS count FROM tempactors WHERE actorname LIKE '%Freeman, Morgan%' > 0 AND serieormovie = 'movie' GROUP BY actorName ORDER BY(count) DESC;";
                    break;
                    case "Welke 5 films hebben de meeste acteurs?": imageList = ctrl.displayImages(btn3Images);
                        query = "SELECT title, COUNT(actorName) as AmountofActors FROM tempactors WHERE serieormovie = 'movie' AND NOT platform = '(VG)' GROUP BY title, play_year ORDER BY AmountofActors DESC LIMIT 5;";
                    break;
                    case "Hoe lang duurt een film gemiddeld per genre (gesorteerd op jaar)": imageList = ctrl.displayImages(btn4Images);
                    break;
                    case "In welk jaar zijn de meeste films uitgekomen (per genre)?": imageList = ctrl.displayImages(btn5Images);
                    break;
                    case "Welke acteur/actrice heeft in de meeste films/series gespeeld?": imageList = ctrl.displayImages(btn6Images);
                    break;
                    case "Hoeveel acteurs zijn er met de voornaam Max?": imageList = ctrl.displayImages(btn7Images);;
                    break;
                    case "Welke acteurs hebben de rol van James Bond gespeeld?": imageList = ctrl.displayImages(btn8Images);
                    break;
                    case "Welke regisseur heeft de meeste films met Jim Carrey geregisseerd?": imageList = ctrl.displayImages(btn9Images);
                    break;
                    case "Welk nummer is het vaakst gebruikt in de soundtrack van films?": imageList = ctrl.displayImages(btn10Images);
                    break;
                    default: imageList = ctrl.displayImages(btn1Images);
                    break;
                }
                
                root.getChildren().remove(box);
                box.getChildren().clear();
                //imageList.clear();
                //System.out.println(imageURLList.size());
                //imageList = ctrl.displayImages(imageURLList);

                try{
                    descriptions = ctrl.getQueryResult(query);
                    query = "";
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                //System.out.println("desc size: " + descriptions.size());
                //System.out.println("IL size: " + imageList.size());
                if(descriptions.size() == imageList.size() && descriptions.size() > 0){
                    for (int j = 0; j < descriptions.size(); j++) {
                        System.out.println("entering image+desc loop");
                        //imageCol.setCellValueFactory(new CustomImage(imageList.get(j)));
                        box.getChildren().add(imageList.get(j));
                        Text text = new Text(100, 250, descriptions.get(j));
                        //Label text = new Label();
                        //text.setFont(new Font(fontSize));
                        //text.setText(descriptions.get(j));
                        box.getChildren().add(text);
                    }
                }

                root.getChildren().add(box);
                //imageURLList.clear();
                descriptions.clear();
                event.consume();
            }
        }); 

        buttonList.get(i).setMinHeight(80);
        buttonList.get(i).wrapTextProperty().setValue(true);
        buttons.getChildren().add(buttonList.get(i));
    }; 
        buttons.setSpacing(20);
        root.getChildren().add(buttons);
        primaryStage.setTitle("VisualIMDB");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
