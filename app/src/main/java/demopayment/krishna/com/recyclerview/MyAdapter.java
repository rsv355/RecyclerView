package demopayment.krishna.com.recyclerview;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    static int VIEW_TYPE;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }


    public class ListViewHolder extends ViewHolder{
        public TextView txtHeader;
        public TextView txtFooter;

        public ListViewHolder ( View itemView ) {
            super ( itemView );
            txtHeader = (TextView) itemView.findViewById(R.id.firstLine);
            txtFooter = (TextView) itemView.findViewById(R.id.secondLine);
        }
    }

    public class GridViewHolder extends ViewHolder{
        public TextView txtHeader1;
        public TextView txtFooter1;

        public GridViewHolder ( View itemView ) {
            super ( itemView );
            txtHeader1 = (TextView) itemView.findViewById(R.id.firstLine);
            txtFooter1 = (TextView) itemView.findViewById(R.id.secondLine);
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



    public void setType(int type){
            VIEW_TYPE = type;
    }

    public MyAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v;
        ViewHolder vh;

        switch (viewType){
            case 0:
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
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final String name = mDataset.get(position);
       // holder.txtHeader.setText(mDataset.get(position));


        switch (viewHolder.getItemViewType () ) {
            case 0:
                ListViewHolder listHolder = ( ListViewHolder ) viewHolder;
                listHolder.txtHeader.setText(mDataset.get(position));
                break;
            case 1:
                GridViewHolder groupViewHolder = ( GridViewHolder ) viewHolder;
                groupViewHolder.txtHeader1.setText(mDataset.get(position));

                break;

        }



      /*  holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(name);
            }
        });

        holder.txtFooter.setText("Footer: " + mDataset.get(position));*/

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}