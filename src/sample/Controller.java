package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.Random;

public class Controller {



    @FXML
    private Slider slider1;

    @FXML
    private TextField lengthbox1;

    @FXML
    private CheckBox checkbox1;

    @FXML
    private CheckBox checkbox2;

    @FXML
    private Button generatebox;

    public void hovered(){
        generatebox.setScaleY(1.5);
        generatebox.setScaleX(1.5);
    }
    public void out(){
        generatebox.setScaleY(1);
        generatebox.setScaleX(1);
    }

    public void dragged(){
        double i = slider1.getValue();
        int length = (int) i;
        System.out.println(length);
        lengthbox1.setText(String.valueOf((int)slider1.getValue()));
    }

    public void typed(){
        try {
            if (Double.parseDouble(lengthbox1.getText()) >= 0 && Double.parseDouble(lengthbox1.getText()) <= 300) {
                slider1.setValue(Double.parseDouble(lengthbox1.getText()));
            }
        }catch (Exception e){
            System.out.println("Invalid");
        }
    }

    public void clicked(){
        if(slider1.getValue() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Your Password");
            alert.setHeaderText("Automatically Copied to your clipboard!");
            alert.setHeight(300);
            String pass = generation();
            alert.setContentText(pass);
            alert.show();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(pass);
            clipboard.setContent(content);
        }
    }

    public String generation() {
        Random rand = new Random();
        StringBuilder SB = new StringBuilder();

        String[] none = {"a", "b", "c", "d",
                "e", "f", "g", "h",
                "i", "j", "k", "l",
                "m", "n", "o", "p",
                "q", "r", "s", "t",
                "u", "v", "w", "x",
                "y", "z", "A", "B",
                "C", "D", "E", "F",
                "G", "H", "I", "J",
                "K", "L", "M", "N",
                "O", "P", "Q", "R",
                "S", "T", "U", "V",
                "W", "X", "Y", "Z"};


        String[] optionOne = {"a", "b", "c", "d",
                "e", "f", "g", "h",
                "i", "j", "k", "l",
                "m", "n", "o", "p",
                "q", "r", "s", "t",
                "u", "v", "w", "x",
                "y", "z", "A", "B",
                "C", "D", "E", "F",
                "G", "H", "I", "J",
                "K", "L", "M", "N",
                "O", "P", "Q", "R",
                "S", "T", "U", "V",
                "W", "X", "Y", "Z",
                "1", "2", "3", "4",
                "5", "6", "7", "8",
                "9", "0"};

        String[] optionTwo = {"a", "b", "c", "d",
                "e", "f", "g", "h",
                "i", "j", "k", "l",
                "m", "n", "o", "p",
                "q", "r", "s", "t",
                "u", "v", "w", "x",
                "y", "z", "A", "B",
                "C", "D", "E", "F",
                "G", "H", "I", "J",
                "K", "L", "M", "N",
                "O", "P", "Q", "R",
                "S", "T", "U", "V",
                "W", "X", "Y", "Z",
                "`", "~", "!", "@",
                "#", "$", ">", "/",
                "%", "^", "&", "*",
                "(", ")", "-", "_",
                "=", "+",
                 "|", ";",
                ">", "<",};

        String[] all = {"a", "b", "c", "d",
                "e", "f", "g", "h",
                "i", "j", "k", "l",
                "m", "n", "o", "p",
                "q", "r", "s", "t",
                "u", "v", "w", "x",
                "y", "z", "A", "B",
                "C", "D", "E", "F",
                "G", "H", "I", "J",
                "K", "L", "M", "N",
                "O", "P", "Q", "R",
                "S", "T", "U", "V",
                "W", "X", "Y", "Z",
                "1", "2", "3", "4",
                "5", "6", "7", "8",
                "9", "0", "`", "~",
                "!", "@", "#", "$",
                "%", "^", "&", "*",
                "(", ")", "-", "_",
                "=", "+",
                 "|", ";",
                "<", ">", "/", ">"};

        for(int i = 0; i < slider1.getValue(); i++) {
            if (!checkbox1.isSelected() && !checkbox2.isSelected()) {
                SB.append(none[rand.nextInt(none.length)]);
                if (SB.length() == slider1.getValue()) {
                    break;
                }
            } else if (checkbox1.isSelected() && !checkbox2.isSelected()) {
                SB.append(optionOne[rand.nextInt(optionOne.length)]);
                if (SB.length() == slider1.getValue()) {
                    break;
                }
            } else if (!checkbox1.isSelected() && checkbox2.isSelected()) {
                SB.append(optionTwo[rand.nextInt(optionTwo.length)]);
                if(SB.length() == slider1.getValue()){
                    break;
                }
            }else {
                SB.append(all[rand.nextInt(all.length)]);
                if(SB.length() == slider1.getValue()){
                    break;
                }
            }
        }
        return SB.toString();
    }
}
