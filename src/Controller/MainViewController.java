package Controller;

import View.MainMenuView;
import View.MainView;
import View.ReaderView;
import View.WritterView;

public class MainViewController {
    public MainView view;

    public MainViewController(MainView view) {
        this.view = view;

    }

    public void iniciarVistaMenu(){
        MainMenuView vistaMenuPrincipal = new MainMenuView();
        ControladorMenuPrincipal controladorMenuPrincipal = new ControladorMenuPrincipal(this,vistaMenuPrincipal);
        view.changeView(vistaMenuPrincipal.panel1);
    }
    public void iniciarVistaLector(){
        ReaderView vistaLector = new ReaderView();
        ReaderViewController vistaLectorController = new ReaderViewController(this,vistaLector);
        view.changeView(vistaLector.panel1);
    }
    public void iniciarVistaWritter(){
        WritterView vistaWritter = new WritterView();
        WritterViewController vistaWritterController = new WritterViewController(this,vistaWritter);
        view.changeView(vistaWritter.Panel1);
    }

    public void iniciar() {
        iniciarVistaMenu();
        this.view.iniciar();
    }
}
