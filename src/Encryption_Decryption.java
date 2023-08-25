import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Encryption_Decryption {
    static int shift = 4;
    static String text;
    static String encrytedString;
    static String encoded;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\t\t\t   Menu");
            System.out.println("\t----------------------------------------");
            System.out.print("\t1-Encrypt_String\t 2-Decrypt_String\n\t3-Encrypt_Img    \t 4-Decrypt_Imp\n\t5-Exit\n");
            System.out.println("\t----------------------------------------");
            System.out.print("\tEnter Choice : ");
            choice = in.nextInt();
            System.out.println();
        
            switch(choice) {
                case 1: System.out.print("\tEnter a string to encrypt : ");
                        text = in.next();
                        Encrypt_String(text);
                        break;
                case 2: Decrypt_String();
                        break;
                        case 3: System.out.print("\tEnter the path of the image: ");
                                String path = reader.readLine();
                                BufferedImage image = ImageIO.read(new File(path));
                                Encrypt_Img(image);
                                break;                    
                case 4: Decrypt_Imp();
                        break;
                case 5: System.out.println("\nExiting the program...!!!");
                        break;
            }
        }while(choice != 5);
        in.close();
    }

    static void Encrypt_String(String text) throws InterruptedException {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch) && Character.isUpperCase(ch)) {
                char encryptedChar = (char) (((ch - 'A' + shift) % 26) + 'A');
                result.append(encryptedChar);
            } else if (Character.isLetter(ch) && Character.isLowerCase(ch)) {
                char encryptedChar = (char) (((ch - 'a' + shift) % 26) + 'a');
                result.append(encryptedChar);
            } else {
                result.append(ch);
            }
        }
        encrytedString = result.toString();
        System.out.println("\tEncrypted string : " + result.toString());
        System.out.println();

        Thread.sleep(5000);
    }

    static void Decrypt_String() throws InterruptedException {
        StringBuilder result = new StringBuilder();


        for (char ch : encrytedString.toCharArray()) {
            if (Character.isLetter(ch) && Character.isUpperCase(ch)) {
                char decryptedChar = (char) (((ch - 'A' - shift + 26) % 26) + 'A');
                result.append(decryptedChar);
            } else if (Character.isLetter(ch) && Character.isLowerCase(ch)) {
                char encryptedChar = (char) (((ch - 'a' - shift) % 26) + 'a');
                result.append(encryptedChar);
            } else {
                result.append(ch);
            }
        }
        System.out.println("\tEncrypted string : " + result.toString());
        System.out.println();

        Thread.sleep(5000);
    }

    static void Encrypt_Img(BufferedImage image) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();
        encoded = Base64.getEncoder().encodeToString(imageBytes);
        System.out.println("\tEncoded Image:");
        System.out.println(encoded);

        Thread.sleep(5000);
    }

    static void Decrypt_Imp() throws Exception{
        byte[] imageBytes = Base64.getDecoder().decode(encoded);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(bais);
        System.out.println("Decoded Image:");
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Decoded Image");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(new JLabel(new ImageIcon(image)));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
///Passionate software developer dedicated to continuous learning. Skilled in C/C++, Java, Python, HTML, CSS, SQL. Effective team player.