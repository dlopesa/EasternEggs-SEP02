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
    UserProxy proxy;
    ModelManager modelManager=new ModelManager();
    try {
      proxy = new UserProxy(modelManager);
      ViewModelFactory viewModelFactory=new ViewModelFactory(proxy);
      ViewCreator view=new ViewHandler(viewModelFactory);
      view.start(primaryStage);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}
