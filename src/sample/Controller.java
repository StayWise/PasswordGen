package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {

    @FXML
    private Slider lengthSlider;

    @FXML
    private TextField lengthText;

    @FXML
    private CheckBox numberCheck;

    @FXML
    private CheckBox characterCheck;

    @FXML
    private TextField keyText;

    @FXML
    private Button generateButton;

    @FXML
    private Button retrieveButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button viewAllButton;


    //Generate Button Effect
    public void generateButtonEnter(){
        this.generateButton.setScaleY(1.15);
        this.generateButton.setScaleX(1.15);
    }

    public void generateButtonExit(){
        this.generateButton.setScaleY(1);
        this.generateButton.setScaleX(1);
    }

    //Retrieve Button Effect
    public void retrieveButtonEnter(){
        this.retrieveButton.setScaleY(1.15);
        this.retrieveButton.setScaleX(1.15);
    }

    public void retrieveButtonExit(){
        this.retrieveButton.setScaleY(1);
        this.retrieveButton.setScaleX(1);
    }

    //Remove Button Effect
    public void removeButtonEnter(){
        this.removeButton.setScaleY(1.15);
        this.removeButton.setScaleX(1.15);
    }

    public void removeButtonExit(){
        this.removeButton.setScaleY(1);
        this.removeButton.setScaleX(1);
    }

    //ViewAll Button Effect
    public void viewAllButtonEnter(){
        this.viewAllButton.setScaleY(1.15);
        this.viewAllButton.setScaleX(1.15);
    }

    public void viewAllButtonExit(){
        this.viewAllButton.setScaleY(1);
        this.viewAllButton.setScaleX(1);
    }


    //Set length via slider drag
    public void sliderDragged(){
        this.lengthText.setText(String.valueOf((int) this.lengthSlider.getValue() + 1));
    }

    //Set length via text box
    public void lengthKeyPress(){
        try {
            if (Double.parseDouble(this.lengthText.getText()) > 0 && Double.parseDouble(this.lengthText.getText()) <= 100) {
                this.lengthSlider.setValue(Double.parseDouble(this.lengthText.getText()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Generate and automatically save into database
    public void generateButtonClicked(){
        if(this.lengthSlider.getValue() > 0) {
            JSONParser parser = new JSONParser();
            Path path = Paths.get("PasswordData.json");

            //Checks to see if you have a data file, if not creates one
            if(Files.exists(path)) {
                try (Reader reader = new FileReader("PasswordData.json")) {
                    JSONObject jsonObject = (JSONObject) parser.parse(reader);

                    if (this.keyText.getLength() < 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("You forgot a key!");
                        alert.setHeight(100);
                        alert.show();
                    } else {
                        String pass = Generator.generation(this.lengthSlider, this.numberCheck, this.characterCheck);
                        jsonObject.put(this.keyText.getText(), pass);

                        try (FileWriter file = new FileWriter("PasswordData.json")) {
                            file.write(jsonObject.toJSONString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Your Password");
                        alert.setHeaderText("Automatically Copied to your clipboard!");
                        alert.setHeight(100);
                        alert.setContentText(pass);
                        alert.show();
                        Clipboard clipboard = Clipboard.getSystemClipboard();
                        ClipboardContent content = new ClipboardContent();
                        content.putString(pass);
                        clipboard.setContent(content);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                JSONObject jsonObject = new JSONObject();
                if (this.keyText.getLength() < 1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("You forgot a key!");
                    alert.setHeight(100);
                    alert.show();
                } else {
                    String pass = Generator.generation(this.lengthSlider, this.numberCheck, this.characterCheck);
                    jsonObject.put(this.keyText.getText(), pass);
                    try (FileWriter file = new FileWriter("PasswordData.json")) {
                        file.write(jsonObject.toJSONString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Your Password");
                    alert.setHeaderText("Automatically Copied to your clipboard!");
                    alert.setHeight(100);
                    alert.setContentText(pass);
                    alert.show();
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(pass);
                    clipboard.setContent(content);

                }
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Do not have a length below 0!");
            alert.setHeight(100);
            alert.show();
        }
    }

    //Check all key's within the database
    public void viewAllButtonClicked(){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("PasswordData.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            StringBuilder temp = new StringBuilder();
            for(Object key : jsonObject.keySet()){
                temp.append(key + "\n");

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Keys");
            alert.setHeaderText("Your Keys:");
            alert.setHeight(100);
            alert.setContentText(temp.toString());
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Remove the specific key&value from the database
    public void removeButtonClicked(){
        JSONParser parser = new JSONParser();

        try(Reader reader = new FileReader("PasswordData.json")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Key Removal");
            alert.setHeight(100);

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            if(jsonObject.containsKey(this.keyText.getText())){
                jsonObject.remove(this.keyText.getText());

                try(FileWriter file = new FileWriter("PasswordData.json")){
                    file.write(jsonObject.toJSONString());
                }catch (Exception e){
                    e.printStackTrace();
                }
                alert.setHeaderText("Success!\nYour key has been removed!");

            }else{
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Uh oh...\nThere is no key with that value!");
            }

            alert.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Get the value of the key you enter
    public void retrieveButtonClicked(){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("PasswordData.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            if(jsonObject.containsKey(this.keyText.getText())){
                String pass = (String) jsonObject.get(this.keyText.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(this.keyText.getText());
                alert.setHeaderText("Automatically Copied to your clipboard!");
                alert.setHeight(100);
                alert.setContentText(pass);
                alert.show();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(pass);
                clipboard.setContent(content);

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(this.keyText.getText());
                alert.setHeaderText("Uh oh...\nThere is no key with that value!");
                alert.setHeight(100);
                alert.show();
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
