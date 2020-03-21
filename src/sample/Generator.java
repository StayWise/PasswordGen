package sample;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

import java.util.Random;

public class Generator {

    //Password Generation
    public static String generation(Slider slider, CheckBox checkOne, CheckBox checkTwo) {

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


        for (int i = 0; i < slider.getValue(); i++) {
            if (!checkOne.isSelected() && !checkTwo.isSelected()) {
                SB.append(none[rand.nextInt(none.length)]);
                if (SB.length() == slider.getValue()) {
                    break;
                }
            } else if (checkOne.isSelected() && !checkTwo.isSelected()) {
                SB.append(optionOne[rand.nextInt(optionOne.length)]);
                if (SB.length() == slider.getValue()) {
                    break;
                }
            } else if (!checkOne.isSelected() && checkTwo.isSelected()) {
                SB.append(optionTwo[rand.nextInt(optionTwo.length)]);
                if (SB.length() == slider.getValue()) {
                    break;
                }
            } else {
                SB.append(all[rand.nextInt(all.length)]);
                if (SB.length() == slider.getValue()) {
                    break;
                }
            }
        }

        return SB.toString();
    }
}
