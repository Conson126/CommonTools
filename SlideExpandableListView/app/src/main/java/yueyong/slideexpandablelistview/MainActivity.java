package yueyong.slideexpandablelistview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view for this activity, check the content view xml file
        // to see how it refers to the ActionSlideExpandableListView view.
        this.setContentView(R.layout.activity_main);
        // get a reference to the listview, needed in order
        // to call setItemActionListener on it
        ActionSlideExpandableListView list = (ActionSlideExpandableListView) this.findViewById(R.id.list);

        // fill the list with data
        list.setAdapter(buildDummyData());

        // listen for events in the two buttons for every list item.
        // the 'position' var will tell which list item is clicked
        list.setItemActionListener(new ActionSlideExpandableListView.OnActionClickListener() {

            @Override
            public void onClick(View listView, View buttonview, int position) {

                /**
                 * Normally you would put a switch
                 * statement here, and depending on
                 * view.getId() you would perform a
                 * different action.
                 */
                String actionName = "";
                if (buttonview.getId() == R.id.buttonA) {
                    actionName = "buttonA";
                } else {
                    actionName = "ButtonB";
                }
                /**
                 * For testing sake we just show a toast
                 */
                Toast.makeText(
                        MainActivity.this,
                        "Clicked Action: " + actionName + " in list item " + position,
                        Toast.LENGTH_SHORT
                ).show();
            }

            // note that we also add 1 or more ids to the setItemActionListener
            // this is needed in order for the listview to discover the buttons
        }, R.id.buttonA, R.id.buttonB);
    }

    public ListAdapter buildDummyData() {
        final int SIZE = 20;
        String[] values = new String[SIZE];
        for(int i=0;i<SIZE;i++) {
            values[i] = "Item "+i;
        }
        return new ArrayAdapter<String>(
                this,
                R.layout.expandable_list_item,
                R.id.text,
                values
        );
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
