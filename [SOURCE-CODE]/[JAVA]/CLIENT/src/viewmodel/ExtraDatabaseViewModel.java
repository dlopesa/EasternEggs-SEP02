package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import property.ExtraProperty;
import utility.Extra;

public class ExtraDatabaseViewModel
{
  private Model model;
  private ObservableList<ExtraProperty> allExtras;
  private ExtraProperty selectedExtra;

  public ExtraDatabaseViewModel(Model model)
  {
    this.model = model;
    allExtras = FXCollections.observableArrayList();
    this.selectedExtra = null;
  }

  public void setSelectedExtra(ExtraProperty extra) {
    this.selectedExtra = extra;
  }

  public void reset()
  {
    allExtras.clear();
    try
    {
      for (Extra extra : model.getAllExtras()) {
        allExtras.add(new ExtraProperty(extra));
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public ObservableList<ExtraProperty> getAllExtras() {
    return allExtras;
  }

  public void removeExtra() {
    try
    {
      model.removeExtraFromExtraList(selectedExtra.getExtra());
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }
}
