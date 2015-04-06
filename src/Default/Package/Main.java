/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Default.Package;

import javax.swing.JFrame;

/**
 *
 * @author Sourav
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static JFrame frame;
    public static int SizeOfBoard, difficulty=1;

    public static void main(String[] args) {

        SizeOfBoard = 7;
        difficulty = 1;

        frame = new Menu();
        frame.setVisible(true);
    }
}
