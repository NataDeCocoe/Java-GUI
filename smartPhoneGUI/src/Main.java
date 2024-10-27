import MainFrame.LoginFrame;
import MainFrame.config;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
            FlatLightLaf.setup();
            new config();
            new LoginFrame();
    }
}
