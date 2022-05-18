package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler extends ViewCreator
{

  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory=viewModelFactory;
  }

  @Override protected void initViewController(ViewController controller,
      Region root)
  {
    controller.init(this,viewModelFactory, root);
  }

  @Override public void start(Stage primaryStage)
  {
    this.primaryStage=primaryStage;
    this.currentScene= new Scene(new Region());
    openView("CashierView.fxml");
  }

  public void openView(String id)
  {
    Region root = getViewController(id).getRoot();
    //Magic happens

    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }


}
