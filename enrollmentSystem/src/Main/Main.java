package Main;

import Components.LoginFrame;
import Database.config;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        new LoginFrame();
        new config();
    }
}
