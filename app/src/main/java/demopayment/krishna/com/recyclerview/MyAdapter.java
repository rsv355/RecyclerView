package demopayment.krishna.com.recyclerview;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    static int VIEW_TYPE;
    public Context _ctx;
    public onLongClickListener _longClick;
    public onSingleClickListener _singleClick;


    // 0 - for List Type
    // 1 - for Gri Type

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }


    public class ListViewHolder extends ViewHolder{
        public TextView txtHeader;
        public TextView txtFooter;
        public RelativeLayout listView;
        public ListViewHolder ( View itemView ) {
            super ( itemView );
            txtHeader = (TextView) itemView.findViewById(R.id.firstLine);
            txtFooter = (TextView) itemView.findViewById(R.id.secondLine);
            listView = (RelativeLayout) itemView.findViewById(R.id.listView);
        }
    }

    public class GridViewHolder extends ViewHolder{
        public TextView txtHeader1;
        public TextView txtFooter1;
        public LinearLayout gridView;

        public GridViewHolder ( View itemView ) {
            super ( itemView );
            txtHeader1 = (TextView) itemView.findViewById(R.id.firstLine);
            txtFooter1 = (TextView) itemView.findViewById(R.id.secondLine);
            gridView = (LinearLayout) itemView.findViewById(R.id.gridView);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(VIEW_TYPE==0)
              return 0;
        else
            return 1;
    }

    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public void setSingleClickListener(onSingleClickListener obj){
            this._singleClick = obj;
    }

    public void setLongClickListener(onLongClickListener obj){
            this._longClick = obj;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void setType(int type){
            VIEW_TYPE = type;
    }

    public MyAdapter(Context ctx,ArrayList<String> myDataset) {
        this._ctx = ctx;
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        ViewHolder vh;

        LayoutInflater mInflater = LayoutInflater.from (parent.getContext());
        switch (viewType) {

            case 0:
                ViewGroup vImage = ( ViewGroup ) mInflater.inflate ( R.layout.item_view, parent, false );
                ListViewHolder vhImage = new ListViewHolder (vImage);
                return vhImage;
            case 1:
                ViewGroup vGroup = ( ViewGroup ) mInflater.inflate ( R.layout.item_grid, parent, false );
                GridViewHolder vhGroup = new GridViewHolder (vGroup);
                return vhGroup;
            default:
                ViewGroup vGroup0 = ( ViewGroup ) mInflater.inflate ( R.layout.item_grid, parent, false );
                GridViewHolder vhGroup0 = new GridViewHolder (vGroup0);
                return vhGroup0;
        }







      /*  switch (viewType){
            case 0 :
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
                ListViewHolder lv = new ListViewHolder(v);
                return lv;
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
                GridViewHolder gv = new GridViewHolder(v);
                return gv;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
                vh = new ViewHolder ( v );
                return vh;
        }*/
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final String name = mDataset.get(position);
        Log.e("##### NAME",name);

        switch (viewHolder.getItemViewType () ) {
            case 0:
                ListViewHolder listHolder = ( ListViewHolder ) viewHolder;
                listHolder.txtHeader.setText(mDataset.get(position));

                listHolder.listView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        _singleClick.onSingleClick(position);
                    }
                });

                break;
            case 1:



              GridViewHolder groupViewHolder = ( GridViewHolder ) viewHolder;


                    groupViewHolder.txtHeader1.setText(mDataset.get(position));

                    groupViewHolder.gridView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            _singleClick.onSingleClick(position);
                        }
                    });

                    groupViewHolder.gridView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            _longClick.onLongClick(position);
                            return false;
                        }
                    });


                break;

        }





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}