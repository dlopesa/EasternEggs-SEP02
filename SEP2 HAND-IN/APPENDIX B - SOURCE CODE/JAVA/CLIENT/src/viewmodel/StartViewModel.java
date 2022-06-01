package viewmodel;

import model.Model;

public class StartViewModel
{
  private Model model;
  public StartViewModel(Model model){
    this.model=model;
  }

  public void setUserType(String userType) {
    model.setUserType(userType);
  }
}
