package es.ulpgc.eite.clean.mvp.dummy.bye;


import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.dummy.app.Mediator;
import es.ulpgc.eite.clean.mvp.dummy.app.Navigator;

public class ByePresenter extends GenericPresenter
    <Bye.PresenterToView, Bye.PresenterToModel, Bye.ModelToPresenter, ByeModel>
    implements Bye.ViewToPresenter, Bye.ModelToPresenter, Bye.ByeToHello, Bye.HelloToBye {


  private boolean toolbarVisible;
  private boolean buttonClicked;
  private boolean textVisible;
  private boolean progressBarVisible;
  private String text;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Bye.PresenterToView view) {
    super.onCreate(ByeModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingByeScreen()");
    Mediator app = (Mediator) getView().getApplication();
    app.startingByeScreen(this);

  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Bye.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");

    if(configurationChangeOccurred()) {
      getView().setSayByeLabel(getModel().getSayByeLabel());
      getView().setBackToHelloLabel(getModel().getBackToHelloLabel());
      getView().setText(text);

      checkToolbarVisibility();
      checkTextVisibility();
      checkProgressBarVisibility();

      if (buttonClicked) {
        getView().setText(text);
      }
    }

  }

  /**
   * Helper method to inform Presenter that a onBackPressed event occurred
   * Called by {@link GenericActivity}
   */
  @Override
  public void onBackPressed() {
    Log.d(TAG, "calling onBackPressed()");
  }

  /**
   * Hook method called when the VIEW is being destroyed or
   * having its configuration changed.
   * Responsible to maintain MVP synchronized with Activity lifecycle.
   * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
   *
   * @param isChangingConfiguration true: configuration changing & false: being destroyed
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {
    super.onDestroy(isChangingConfiguration);
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////

  @Override
  public void onSayByeBtnClicked() {
    Log.d(TAG, "calling onSayHelloBtnClicked()");
    if(isViewRunning()) {
      buttonClicked = true;
      text = getModel().getText();
      new CountDownTimer(3000, 1000) {

        public void onTick(long millisUntilFinished) {
          progressBarVisible = true;
          textVisible = false;
          checkProgressBarVisibility();
          checkTextVisibility();
        }

        public void onFinish() {
          progressBarVisible = false;
          getView().setText(text);
          textVisible = true;
          checkProgressBarVisibility();
          checkTextVisibility();
          Log.d("Test", text);
        }
      }.start();
    }
  }

  @Override
  public void onBackToHelloBtnClicked() {
    Log.d(TAG, "calling onGoToByeBtnClicked()");
    Navigator app = (Navigator) getView().getApplication();
    app.goToHelloScreen(this);
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Bye //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
      getView().setSayByeLabel(getModel().getSayByeLabel());
      getView().setBackToHelloLabel(getModel().getBackToHelloLabel());
      getView().setText(text);
    if(text != null) {
      setTextVisibility(true);
    }
  }
    checkToolbarVisibility();
    checkTextVisibility();
    checkProgressBarVisibility();
  }

  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }

  @Override
  public void setProgressBarVisibility(boolean visible) {
    progressBarVisible = visible;
  }

  @Override
  public void setStateText(String text) {
    this.text = text;
  }

  @Override
  public void setTextVisibility(boolean visible) {
    textVisible = visible;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Bye To //////////////////////////////////////////////////////////////////////


  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }
  @Override
  public boolean isToolbarVisible() {
    return toolbarVisible;
  }

  @Override
  public boolean isProgressBarVisible() {
    return progressBarVisible;
  }

  @Override
  public boolean isTextVisible() {
    return textVisible;
  }

  public String getStateText() {
    return text;
  }

  ///////////////////////////////////////////////////////////////////////////////////

  private void checkToolbarVisibility(){
    Log.d(TAG, "calling checkToolbarVisibility()");
    if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }
  }

  private void checkProgressBarVisibility() {
    Log.d(TAG, "calling checkProgressBarVisibility()");
    if(isViewRunning()) {
      if (!progressBarVisible) {
        getView().hideProgressBar();
      }else{
        getView().showProgressBar();
      }
    }
  }

  private void checkTextVisibility(){
    Log.d(TAG, "calling checkTextVisibility()");
    if(isViewRunning()) {
      if(!textVisible) {
        getView().hideText();
      } else {
        getView().showText();
      }
    }
  }

}
