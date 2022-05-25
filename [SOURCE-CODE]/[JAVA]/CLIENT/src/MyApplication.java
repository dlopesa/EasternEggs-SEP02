import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import model.UserProxy;
import view.ViewCreator;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    Model model=null;
    UserProxy proxy = null;
    try {
      model= new ModelManager();
      proxy = new UserProxy(model);
      ViewModelFactory viewModelFactory=new ViewModelFactory(proxy);
      ViewCreator view=new ViewHandler(viewModelFactory);
      view.start(primaryStage);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}
