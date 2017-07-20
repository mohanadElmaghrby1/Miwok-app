package apps.mohanad.com.miwok1;
import android.view.View;
import android.widget.Toast;

/**
 * Created by mohanad on 19/04/17.
 */

public class NumbersClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),"numbers clicked" , Toast.LENGTH_SHORT).show();
    }
}
