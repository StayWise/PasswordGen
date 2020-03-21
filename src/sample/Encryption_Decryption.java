package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Optional;

public class Encryption_Decryption {


    private static String hash;

    //Prompts at start of program
    public static void prompt(Stage stage){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setResizable(false);
        dialog.setTitle("SECURITY CHECK");
        dialog.setHeaderText("Security Check, 16 Characters or less!");
        dialog.setContentText("Security Code:");
        try {
            Optional<String> result = dialog.showAndWait();
            String toEncrypt = "Security Check";
            hash = fillRemaining(result.get());
            byte[] raw = hash.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(raw, "AES");
            byte[] encryptedText = encryptText(toEncrypt, secretKey);
            try {
                FileOutputStream stream = new FileOutputStream("encryption.text");
                stream.write(encryptedText);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FileInputStream fis = new FileInputStream("encryption.text");
            byte[] bytes = fis.readAllBytes();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please make sure you've entered a security code!");
            alert.setHeight(100);
            alert.show();
            stage.close();
        }
    }

    //Simply returns the hash list
    public static String getHash() {
        return hash;
    }

    //If user's security code is to short, this will fill in the rest
    private static String fillRemaining(String pass) {
        String fill = "=_^WPqfwY$!&2gV5";
        int toFill = 16 - pass.length();
        StringBuilder sb = new StringBuilder();
        sb.append(pass);
        for (int i = 0; i < toFill; i++) {
            sb.append(fill.toCharArray()[i]);
        }
        return sb.toString();
    }

    //Encrypts text
    public static byte[] encryptText(String plainText, SecretKey secKey) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return byteCipherText;
    }

    //Decrypts text
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }

}
