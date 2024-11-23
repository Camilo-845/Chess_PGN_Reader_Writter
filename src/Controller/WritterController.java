package Controller;

import View.WriterUserDataView;
import View.WritterView;

public class WritterController {
    public MainViewController mainController;
    
    public WritterController (MainViewController mainController){
        this.mainController = mainController;
        iniciarWritterUserData();
    }
    
    public void iniciarVistaWritter(){
        WritterView vistaWritter = new WritterView();
        WritterViewController vistaWritterController = new WritterViewController(this,vistaWritter);
        mainController.view.changeView(vistaWritter);
    }
    
    public void iniciarWritterUserData(){
        WriterUserDataView vistaUserData = new WriterUserDataView();
        WritterUserDataController UserDataController = new WritterUserDataController(this, vistaUserData);
        mainController.view.changeView(vistaUserData);
    }
}
