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
        WritterViewController vistaWritterController = new WritterViewController(this);
        mainController.view.changeView(vistaWritterController.view);
    }
    
    public void iniciarWritterUserData(){
        WriterUserDataView vistaUserData = new WriterUserDataView();
        WritterUserDataController UserDataController = new WritterUserDataController(this, vistaUserData);
        mainController.view.changeView(vistaUserData);
    }
}
