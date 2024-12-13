package Main;

import Controller.*;

public class Main {
    public static void main(String[] args) {
        DatabaseHandler dbHandMain = new DatabaseHandler();
        dbHandMain.connect();
        new ControllerAll(dbHandMain);
    }
}
