package es.ulpgc.eite.clean.mvp.dummy.bye;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class ByeModel extends GenericModel<Bye.ModelToPresenter>
    implements Bye.PresenterToModel {

  private String byeText;
  private String sayByeLabel;
  private String backToHelloLabel;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Bye.ModelToPresenter presenter) {
    super.onCreate(presenter);

    sayByeLabel = "Say Bye";
    backToHelloLabel = "Back to Hello";
    byeText = "Bye World!";
  }

  /**
   * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
   * Usefull for kill/stop activities that could be running on the background Threads
   *
   * @param isChangingConfiguration Informs that a change is occurring on the configuration
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////


  @Override
  public String getText() {
    return byeText;
  }

  @Override
  public String getSayByeLabel() {
    return sayByeLabel;
  }

  @Override
  public String getBackToHelloLabel() {
    return backToHelloLabel;
  }
}
