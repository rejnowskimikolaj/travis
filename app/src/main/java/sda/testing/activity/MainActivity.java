package sda.testing.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import sda.testing.R;
import sda.testing.RecyclerViewAdapter;

public class MainActivity extends Activity implements View.OnClickListener, RecyclerViewAdapter.OnItemClickListener {

    TextView text;
    private RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        GridLayout layout = (GridLayout) findViewById(R.id.grid_layout);
        layout.requestLayout();
        text = (TextView) findViewById(R.id.text);
        (findViewById(R.id.button1)).setOnClickListener(this);
        (findViewById(R.id.button2)).setOnClickListener(this);
        (findViewById(R.id.button3)).setOnClickListener(this);
        (findViewById(R.id.button4)).setOnClickListener(this);
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        myRecyclerViewAdapter = new RecyclerViewAdapter(this);
        myRecyclerViewAdapter.setOnItemClickListener(this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManager);

        //insert dummy items
        myRecyclerViewAdapter.add("Android0");
        myRecyclerViewAdapter.add("Android1");
        myRecyclerViewAdapter.add("Android2");
        myRecyclerViewAdapter.add("Android3");
        myRecyclerViewAdapter.add("Android4");
        myRecyclerViewAdapter.add("Android5");
        myRecyclerViewAdapter.add("Android6");
        myRecyclerViewAdapter.add("Android7");
        myRecyclerViewAdapter.add("Android8");
        myRecyclerViewAdapter.add("Android9");
        myRecyclerViewAdapter.add("Android10");
    }

    @Override
    public void onItemClick(RecyclerViewAdapter.ItemHolder item, int position) {
        Toast.makeText(this, position + " : " + item.getItemName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Clicked Item 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
                text.setText(((Button) v).getText());
        }
    }
}
