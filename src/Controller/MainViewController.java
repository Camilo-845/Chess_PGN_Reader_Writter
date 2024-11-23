package Controller;

import View.IntroApp;
import View.MainMenuView;
import View.MainView;
import View.PantallaDeCarga;
import View.ReaderView;
import View.WritterView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainViewController {
    public MainView view;

    public MainViewController(MainView view) {
        this.view = view;

    }

    public void iniciarVistaMenu(){
        MainMenuView vistaMenuPrincipal = new MainMenuView();
        ControladorMenuPrincipal controladorMenuPrincipal = new ControladorMenuPrincipal(this,vistaMenuPrincipal);
        view.changeView(vistaMenuPrincipal);
    }
    public void iniciarVistaLector(){
        ReaderView vistaLector = new ReaderView();
        ReaderViewController vistaLectorController = new ReaderViewController(this,vistaLector);
        view.changeView(vistaLector);
    }
    public void iniciarVistaWritter(){
        new WritterController(this);
    }
    
    public void iniciarIntroApp(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        IntroApp vistaIntro = new IntroApp();
        IntroAppController vistaIntroController = new IntroAppController(this, vistaIntro);
        view.changeView(vistaIntro);
        
         Runnable task = () -> {
            iniciarVistaMenu();
        };
        scheduler.schedule(task, 2, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
    
    public void iniciarPantallaCarga(){
        PantallaDeCarga vistaCarga = new PantallaDeCarga();
        view.changeView(vistaCarga);
    }

    public void iniciar() {
        iniciarIntroApp();
        this.view.iniciar();
    }
}
