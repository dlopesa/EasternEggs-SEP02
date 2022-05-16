import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import view.ViewCreator;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    try {
      Model model= new ModelManager();
      ViewModelFactory viewModelFactory=new ViewModelFactory(model);
      ViewCreator view=new ViewHandler(viewModelFactory);
      view.start(primaryStage);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}
