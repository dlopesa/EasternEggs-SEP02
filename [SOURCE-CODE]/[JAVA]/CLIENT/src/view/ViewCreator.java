package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public abstract class ViewCreator
{

  private Map<String, ViewController> views = new HashMap<>();

  public ViewCreator()
  {

  }
  protected abstract void initViewController(ViewController controller,
      Region root);

  public abstract void start(Stage primaryStage);
  public ViewController getViewController(String id)
  {
    //The loadfromFXML method should be called right heeeeeeeeeeeeeeere
    ViewController item = views.get(id);
    if(item==null)
    {
      item = loadFromFXML(id);
      views.put(id, item);
    }
    item.reset();
    return item;
  }

  private ViewController loadFromFXML(String fxmlFile)
  {
    ViewController controller = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      Region root = loader.load();
      controller = loader.getController();
      initViewController(controller, root);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return controller;
  }

}
