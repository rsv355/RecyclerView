package demopayment.krishna.com.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class MotorActivity extends AppCompatActivity implements onSingleClickListener,onLongTouchClickListener,onOption1ClickListener,onOption2ClickListener,onOption3ClickListener,onOption4ClickListener {
    private RecyclerView mRecyclerView;
    private MotorAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset = new ArrayList<>();
    int VIEW = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);

        for (int i = 0; i < 30; i++)
            myDataset.add("Motor " + i);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setItemViewCacheSize(0);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)
        mAdapter = new MotorAdapter(MotorActivity.this, myDataset);
        //mAdapter.setType(0);



        mAdapter.setOption1ClickListener(new onOption1ClickListener() {
            @Override
            public void onOption1Click(int pos) {
                Toast.makeText(MotorActivity.this, "Option 1 is Clicked of Item " + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOption2ClickListener(new onOption2ClickListener() {
            @Override
            public void onOption2Click(int pos) {
                Toast.makeText(MotorActivity.this, "Option 2 is Clicked of Item " + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOption3ClickListener(new onOption3ClickListener() {
            @Override
            public void onOption3Click(int pos) {
                Toast.makeText(MotorActivity.this, "Option 3 is Clicked of Item " + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOption4ClickListener(new onOption4ClickListener() {
            @Override
            public void onOption4Click(int pos) {
                Toast.makeText(MotorActivity.this, "Option 4 is Clicked of Item " + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setSingleClickListener(new onSingleClickListener() {
            @Override
            public void onSingleClick(int pos) {
                Toast.makeText(MotorActivity.this, "Single Click Item Pos: " + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setLongClickListener(new onLongTouchClickListener() {

            @Override
            public void onLongClick(int pos) {
                Toast.makeText(MotorActivity.this, "Long Click Item Pos: " + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setHasStableIds(true);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new LandingAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);
        mRecyclerView.getItemAnimator().setMoveDuration(500);
        mRecyclerView.getItemAnimator().setChangeDuration(500);


        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //0 -List view
                // 1 - for grid view

                if (VIEW == 1) {

                    mAdapter.setType(1);
                    mRecyclerView.setItemViewCacheSize(0);

                    mLayoutManager = new GridLayoutManager(MotorActivity.this, 2);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter.notifyDataSetChanged();
                    VIEW = 0;
                } else {


                    mAdapter.setType(0);
                    mLayoutManager = new LinearLayoutManager(MotorActivity.this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter.notifyDataSetChanged();
                    VIEW = 1;
                }
            }
        });


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

    @Override
    public void onSingleClick(int pos) {
    }

    @Override
    public void onLongClick(int pos) {
    }

    @Override
    public void onOption1Click(int pos) {

    }

    @Override
    public void onOption2Click(int pos) {

    }

    @Override
    public void onOption3Click(int pos) {

    }

    @Override
    public void onOption4Click(int pos) {

    }
}