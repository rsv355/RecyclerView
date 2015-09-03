package demopayment.krishna.com.recyclerview;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    static int VIEW_TYPE;
    public Context _ctx;
    public onLongTouchClickListener _longClick;
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
        public LinearLayout listView;
        public ListViewHolder ( View itemView ) {
            super ( itemView );
            txtHeader = (TextView) itemView.findViewById(R.id.firstLine);
            listView = (LinearLayout) itemView.findViewById(R.id.listView);
        }
    }

    public class GridViewHolder extends ViewHolder{
        public TextView txtHeader1;
        public TextView txtFooter1;
        public LinearLayout gridView;

        public GridViewHolder ( View itemView ) {
            super ( itemView );
            txtHeader1 = (TextView) itemView.findViewById(R.id.firstLine);
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

    public void setLongClickListener(onLongTouchClickListener obj){
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
                ViewGroup viewgroup1 = ( ViewGroup ) mInflater.inflate ( R.layout.item_view, parent, false );
                ListViewHolder listHolder = new ListViewHolder (viewgroup1);
                return listHolder;
            case 1:
                ViewGroup viewgroup2 = ( ViewGroup ) mInflater.inflate ( R.layout.item_grid, parent, false );
                GridViewHolder gridHolder = new GridViewHolder (viewgroup2);
                return gridHolder;
            default:
                ViewGroup viewgroup3 = ( ViewGroup ) mInflater.inflate ( R.layout.item_grid, parent, false );
                GridViewHolder gridHolder1 = new GridViewHolder (viewgroup3);
                return gridHolder1;
        }

    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final String name = mDataset.get(position);

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