import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import screens.DashboardScreen;
import screens.LoginScreen;

public class Tests {

    private static final String USERNAME_AND_PASSWORD = "admin1";

    public static void testUploadingFile(WebDriver webDriver,String filename) {
        String chessDbName = RandomString.make(8);
        LoginScreen loginScreen = new LoginScreen(webDriver);
        DashboardScreen dashboardScreen = loginScreen.register(USERNAME_AND_PASSWORD, USERNAME_AND_PASSWORD);
        dashboardScreen
                .createDb(chessDbName)
                .goToDatabaseSettings(chessDbName)
                .uploadFile(filename);

        dashboardScreen.goToListOfGames(chessDbName);
        dashboardScreen.goToGameScreen();

        dashboardScreen
                .goToDatabaseSettings(chessDbName)
                .deleteChessDb();
        dashboardScreen.logOut();
    }
}
