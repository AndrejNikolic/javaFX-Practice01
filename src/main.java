/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Andrej
 */

public class main extends Application {
      
    Stage window;
    Text response;
    double radius;
    Scene scene1, scene2;
    Circle circle;
    int newY, newX = 0;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        //LAYOUT 1 - Choosing radius size
        
        //Fonts
        Font labelFont = Font.font("Ubuntu", FontWeight.NORMAL, 14);
        Font buttonFont = Font.font("Ubuntu", FontWeight.THIN, 16);
        
        //Nodes
        Label chooseR = new Label("Choose radius size");
        chooseR.setFont(labelFont);
        TextField radiusInput = new TextField();
        radiusInput.setMaxWidth(100);
        radiusInput.setPromptText("radius here");
        response = new Text();
        response.setFill(Color.RED);
        Button setRadiusBtn = new Button("Set Radius");
        setRadiusBtn.setFont(buttonFont);
        setRadiusBtn.setStyle("-fx-color:#a70532;-fx-text-fill:#fff");
        
        setRadiusBtn.setOnAction(e -> isInt(radiusInput, radiusInput.getText()));
        
        //Pane
        VBox layoutSet = new VBox();
        layoutSet.getChildren().addAll(chooseR, radiusInput, response, setRadiusBtn);
        layoutSet.setPadding(new Insets(20));
        layoutSet.setAlignment(Pos.CENTER);
        layoutSet.setSpacing(10);
        
        //Scene1
        scene1 = new Scene(layoutSet, 300, 400);
        window.setScene(scene1);
        window.setTitle("Choose Your Radius");
        window.show();
        
        //LAYOUT 2 - Circle with buttons
        
        //Nodes
        circle = new Circle();
        circle.setFill(Color.RED);
        Polygon btnUp = new Polygon();
        btnUp.getPoints().addAll(10.0,0.0 ,0.0,20.0, 20.0,20.0);
        Polygon btnRight = new Polygon();
        btnRight.getPoints().addAll(0.0,0.0 ,20.0,10.0, 0.0,20.0);
        Polygon btnDown = new Polygon();
        btnDown.getPoints().addAll(0.0,0.0 ,20.0,0.0, 10.0,20.0);
        Polygon btnLeft = new Polygon();
        btnLeft.getPoints().addAll(0.0,10.0 ,20.0,0.0, 20.0,20.0);
        Label labelC = new Label("Click on arrows to move circle");
        labelC.setFont(labelFont);
        Button buttonReset = new Button("Reset");
        resetClass reset = new resetClass();
        buttonReset.setOnAction(reset);
        Button buttonBack = new Button("Back");
        buttonBack.setOnAction(e -> {
            window.setScene(scene1);
            circle.setTranslateX(0);
            circle.setTranslateY(0);
            newY = 0;
            newX = 0;
        });
        
        //Pane
        BorderPane layoutGet = new BorderPane();
        VBox layoutUp = new VBox();
        layoutUp.getChildren().addAll(labelC, btnUp);
        layoutUp.setAlignment(Pos.CENTER);
        layoutUp.setSpacing(10);
        HBox layoutRight = new HBox();
        layoutRight.getChildren().add(btnRight);
        layoutRight.setAlignment(Pos.CENTER);
        FlowPane buttons = new FlowPane();
        buttons.getChildren().addAll(buttonReset, buttonBack);
        buttons.setAlignment(Pos.CENTER);
        buttons.setHgap(10);
        buttons.setStyle("-fx-color:#a70532;-fx-text-fill:#fff");
        VBox layoutDown = new VBox();
        layoutDown.getChildren().addAll(btnDown, buttons);
        layoutDown.setSpacing(10);
        layoutDown.setAlignment(Pos.CENTER);
        HBox layoutLeft = new HBox();
        layoutLeft.getChildren().add(btnLeft);
        layoutLeft.setAlignment(Pos.CENTER);
        layoutGet.setCenter(circle);
        layoutGet.setTop(layoutUp);
        layoutGet.setRight(layoutRight);
        layoutGet.setBottom(layoutDown);
        layoutGet.setLeft(layoutLeft);
        layoutGet.setPadding(new Insets(10));
        
        //Event Handlers
//        btnRight.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//           //System.out.println("Pressed Right");
//           newX = newX + 10;
//           circle.setTranslateX(newX);
//           event.consume();
//        });
        btnRight.setOnMouseClicked(e -> {
           newX = newX + 10;
           circle.setTranslateX(newX);
        });
        btnLeft.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
           newX = newX - 10;
           circle.setTranslateX(newX);
           event.consume();
        });
        btnUp.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
           newY = newY - 10;
           circle.setTranslateY(newY);
           event.consume();
        });
        btnDown.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
           newY = newY + 10;
           circle.setTranslateY(newY);
           event.consume();
        });
        
        //Scene2
        scene2 = new Scene(layoutGet, 600, 700);
    }
    
    private boolean isInt(TextField radiusInput, String message){
        try {
            radius = Double.parseDouble(radiusInput.getText());
            //System.out.println(radius);
            response.setText("");
            circle.setRadius(radius);
            window.setScene(scene2);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("nije");
            response.setText("*" + message + " is not a number");
            return false;
        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    class resetClass implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {
            circle.setTranslateX(0);
            circle.setTranslateY(0);
            newY = 0;
            newX = 0;
    }

    }
    
}


