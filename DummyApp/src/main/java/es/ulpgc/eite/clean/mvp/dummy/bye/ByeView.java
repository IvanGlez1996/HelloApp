package es.ulpgc.eite.clean.mvp.dummy.bye;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.dummy.R;

public class ByeView
    extends GenericActivity<Bye.PresenterToView, Bye.ViewToPresenter, ByePresenter>
    implements Bye.PresenterToView {

  private Toolbar toolbar;
  private Button sayByeButton;
  private Button backToHelloButton;
  private ProgressBar progressBar;
  private TextView text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bye);

    text = (TextView) findViewById(R.id.byeMsg);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    progressBar = (ProgressBar) findViewById(R.id.progressBye);

    sayByeButton = (Button) findViewById(R.id.sayByeBtn);
    sayByeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
              getPresenter().onSayByeBtnClicked();
      }
    });

    backToHelloButton = (Button) findViewById(R.id.backToHelloBtn);
    backToHelloButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onBackToHelloBtnClicked();
      }
    });
  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @Override
  protected void onResume() {
    super.onResume(ByePresenter.class, this);
  }

  /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_hello, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
  */


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  @Override
  public void finishScreen() {
    finish();
  }

  @Override
  public void hideToolbar() {
    toolbar.setVisibility(View.GONE);
  }

  @Override
  public void hideProgressBar() {
    progressBar.setVisibility(View.GONE);
    }

  @Override
  public void showProgressBar() {
     progressBar.setVisibility(View.VISIBLE);
    }

  @Override
  public void hideText() {
    text.setVisibility(View.GONE);
  }

  @Override
  public void showText() {
    text.setVisibility(View.VISIBLE);
  }

  @Override
  public void setText(String txt) {
    text.setText(txt);
  }

  @Override
  public void setSayByeLabel(String txt) {
    sayByeButton.setText(txt);
  }

  @Override
  public void setBackToHelloLabel(String txt) {
    backToHelloButton.setText(txt);
  }
}
