package es.ulpgc.eite.clean.mvp.dummy.hello;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.dummy.hello.Hello;


public class HelloModel extends GenericModel<Hello.ModelToPresenter>
    implements Hello.PresenterToModel {

  private String helloText;
  private String sayHelloLabel;
  private String goToByeLabel;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Hello.ModelToPresenter presenter) {
    super.onCreate(presenter);

    sayHelloLabel = "Say Hello";
    goToByeLabel = "Go to Bye";
    helloText = "Hello World!";
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
    return helloText;
  }

  @Override
  public String getSayHelloLabel() {
    return sayHelloLabel;
  }

  @Override
  public String getGoToByeLabel() {
    return goToByeLabel;
  }
}
