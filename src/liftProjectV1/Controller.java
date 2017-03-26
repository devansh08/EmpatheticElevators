/*
Purpose  	: To control the onClick events of each button and to simulate the movement of the elevator car based on the logic of LiftMovement class
@Author	   	: SpecificallyRandom Team
@Created On	: 18-Feb-17
*/
package liftProjectV1;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.*;

public class Controller implements Initializable {

    private static final int OPEN_DR = 0;
    private static final int MOVE_UP = 1;
    private static final int MOVE_DN = 2;
    private static final int IDLE_ST = 3;
    private static final int SPCL_CS = 4;
    private static int SPCL_FLAG = 0;
    private static int resultSpl;

    private static final String liftOpenPath = "file:/home/devansh/Downloads/LiftProject_1-master/src/liftProjectV1/lift_open.png";
    private static final String liftClosePath = "file:/home/devansh/Downloads/LiftProject_1-master/src/liftProjectV1/lift_close.png";
    private static int floor;

    @FXML
    Button btn_1;
    @FXML
    Button btn_2;
    @FXML
    Button btn_3;
    @FXML
    Button btn_4;
    @FXML
    Button btn_5;
    @FXML
    Button btn_6;
    @FXML
    Button btn_7;
    @FXML
    Button btn_8;
    @FXML
    Button btn_9;

    @FXML
    Button btn_1u;
    @FXML
    Button btn_2u;
    @FXML
    Button btn_3u;
    @FXML
    Button btn_4u;
    @FXML
    Button btn_5u;
    @FXML
    Button btn_6u;
    @FXML
    Button btn_7u;
    @FXML
    Button btn_8u;

    @FXML
    Button btn_2d;
    @FXML
    Button btn_3d;
    @FXML
    Button btn_4d;
    @FXML
    Button btn_5d;
    @FXML
    Button btn_6d;
    @FXML
    Button btn_7d;
    @FXML
    Button btn_8d;
    @FXML
    Button btn_9d;

    @FXML
    ImageView lift_image_view;
    @FXML
    Label floor_number;
    @FXML
    Label direction;

    @FXML protected void handleBtn1Action(ActionEvent event) {
        LiftMovement.setRequestFloor(1);
    }

    @FXML protected void handleBtn2Action(ActionEvent event) {
        LiftMovement.setRequestFloor(2);
    }

    @FXML protected void handleBtn3Action(ActionEvent event) {
        LiftMovement.setRequestFloor(3);
    }

    @FXML protected void handleBtn4Action(ActionEvent event) {
        LiftMovement.setRequestFloor(4);
    }

    @FXML protected void handleBtn5Action(ActionEvent event) {
        LiftMovement.setRequestFloor(5);
    }

    @FXML protected void handleBtn6Action(ActionEvent event) {
        LiftMovement.setRequestFloor(6);
    }

    @FXML protected void handleBtn7Action(ActionEvent event) {
        LiftMovement.setRequestFloor(7);
    }

    @FXML protected void handleBtn8Action(ActionEvent event) {
        LiftMovement.setRequestFloor(8);
    }

    @FXML protected void handleBtn9Action(ActionEvent event) {
        LiftMovement.setRequestFloor(9);
    }

    //For Up buttons

    @FXML protected void handleBtn1uAction(ActionEvent event) {
        LiftMovement.setRequestFloor(1);
    }

    @FXML protected void handleBtn2uAction(ActionEvent event){
        LiftMovement.setRequestFloor(2);
    }

    @FXML protected void handleBtn3uAction(ActionEvent event){
        LiftMovement.setRequestFloor(3);
    }

    @FXML protected void handleBtn4uAction(ActionEvent event){
        LiftMovement.setRequestFloor(4);
    }

    @FXML protected void handleBtn5uAction(ActionEvent event){
        LiftMovement.setRequestFloor(5);
    }

    @FXML protected void handleBtn6uAction(ActionEvent event){
        LiftMovement.setRequestFloor(6);
    }

    @FXML protected void handleBtn7uAction(ActionEvent event){
        LiftMovement.setRequestFloor(7);
    }

    @FXML protected void handleBtn8uAction(ActionEvent event){
        LiftMovement.setRequestFloor(8);
    }

    //For Down Buttons

    @FXML protected void handleBtn2dAction(ActionEvent event){
        LiftMovement.setRequestFloor(2);
    }

    @FXML protected void handleBtn3dAction(ActionEvent event){
        LiftMovement.setRequestFloor(3);
    }

    @FXML protected void handleBtn4dAction(ActionEvent event){
        LiftMovement.setRequestFloor(4);
    }

    @FXML protected void handleBtn5dAction(ActionEvent event){
        LiftMovement.setRequestFloor(5);
    }

    @FXML protected void handleBtn6dAction(ActionEvent event){
        LiftMovement.setRequestFloor(6);
    }

    @FXML protected void handleBtn7dAction(ActionEvent event){
        LiftMovement.setRequestFloor(7);
    }

    @FXML protected void handleBtn8dAction(ActionEvent event) {
        LiftMovement.setRequestFloor(8);
    }

    @FXML protected void handleBtn9dAction(ActionEvent event){
        LiftMovement.setRequestFloor(9);
    }

    //For Panic Outside the lift on each floor using buttons

    @FXML protected void handleBtn2PanicAction(ActionEvent event) {
        LiftMovement.setRequestFloor(-2);
    }

    @FXML protected void handleBtn3PanicAction(ActionEvent event) {
        LiftMovement.setRequestFloor(-3);
    }

    @FXML protected void handleBtn4PanicAction(ActionEvent event) {
        LiftMovement.setRequestFloor(-4);
    }

    @FXML protected void handleBtn5PanicAction(ActionEvent event) {
        LiftMovement.setRequestFloor(-5);
    }

    @FXML protected void handleBtn6PanicAction(ActionEvent event) {
        LiftMovement.setRequestFloor(-6);
    }

    @FXML protected void handleBtn7PanicAction(ActionEvent event) {
        LiftMovement.setRequestFloor(-7);
    }

    @FXML protected void handleBtn8PanicAction(ActionEvent event) {
        LiftMovement.setRequestFloor(-8);
    }

    @FXML protected void handleBtn9PanicAction(ActionEvent event) {
        LiftMovement.setRequestFloor(-9);
    }

    @FXML protected void handleInsidePanic(ActionEvent event) {
        LiftMovement.setRequestFloor(-1);
    }

    protected void updateView() {
        int result = LiftMovement.setCurrentFloor(floor);
        System.out.println("Result: " + result);
        if(SPCL_FLAG == 1) {
            result = resultSpl;
        }
        switch (result) {
            case MOVE_UP:
                floor++;
                updateFloorNumber(floor);
                updateDirection("UP");
                closeDoors();
                System.out.println(floor + " : Moving up...");
                break;
            case MOVE_DN:
                floor--;
                updateFloorNumber(floor);
                updateDirection("DOWN");
                closeDoors();
                break;
            case OPEN_DR:
                openDoors();
                break;
            case IDLE_ST:
                closeDoors();
                updateDirection("None");
                break;
            case SPCL_CS:
                floor--;
                SPCL_FLAG = 1;
                openDoors();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                closeDoors();
                resultSpl = LiftMovement.setRequestFloor(-1);
        }
    }

    public void updateFloorNumber (int floor) {
        floor_number.setText(String.valueOf(floor));
        System.out.println("Updated Floor number...");
    }

    public void updateDirection (String dir) {
        direction.setText(dir);
    }

    public void openDoors() {
        lift_image_view.setImage(new Image(liftOpenPath));
    }

    public void closeDoors() {
        lift_image_view.setImage(new Image(liftClosePath));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("initialize()...");

        floor_number.setText("1");
        direction.setText("UP");

        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while(true) {
                    if (isCancelled()) {
                        break;
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            updateView();
                        }
                    });

                    Thread.sleep(1000);
                }

                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
