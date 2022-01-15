package edu.iipw.pap;

import java.util.ArrayList;

public class CLI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static String ANSI_RGB(ArrayList<Integer> color, String text) {
        return "\u001B[38;2;" + color.get(0) + ";" + color.get(1) + ";" + color.get(2) + "m" + text + ANSI_RESET;
    }

    public static String ANSI_RGB_Gradient(int R1, int G1, int B1, int R2, int G2, int B2, String text) {
        String result = "";
        ArrayList<Integer> color = new ArrayList<Integer>();
        color.add(R1);
        color.add(G1);
        color.add(B1);
        double delta_R = (double) (R2 - R1) / (text.length() - 1);
        double delta_G = (double) (G2 - G1) / (text.length() - 1);
        double delta_B = (double) (B2 - B1) / (text.length() - 1);
        for (int i = 0; i < text.length(); i++) {
            color.set(0, (int) (R1 + delta_R * i));
            color.set(1, (int) (G1 + delta_G * i));
            color.set(2, (int) (B1 + delta_B * i));
            result += ANSI_RGB(color, text.substring(i, i + 1));
        }
        return result;
    }

    public static String interline(int length, int R, int G, int B) {
        String result = "";
        ArrayList<Integer> color = new ArrayList<Integer>();
        color.add(R);
        color.add(G);
        color.add(B);
        for (int i = 0; i < length; i++) {
            result += (ANSI_RGB(color, "═") + ANSI_RESET);
        }
        return result;
    }

    public static String interlineGradient(int length, int R1, int G1, int B1, int R2, int G2, int B2) {
        String result = "";
        ArrayList<Integer> color = new ArrayList<Integer>();
        color.add(R1);
        color.add(G1);
        color.add(B1);
        double delta_R = (double) (R2 - R1) / (length - 1);
        double delta_G = (double) (G2 - G1) / (length - 1);
        double delta_B = (double) (B2 - B1) / (length - 1);
        for (int i = 0; i < length; i++) {
            color.set(0, (int) (R1 + delta_R * i));
            color.set(1, (int) (G1 + delta_G * i));
            color.set(2, (int) (B1 + delta_B * i));
            result += (ANSI_RGB(color, "═") + ANSI_RESET);
        }
        return result;
    }

}
