package demopayment.krishna.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    private ArrayList<String> mDataset;
    static int VIEW_TYPE;
    public Context _ctx;
    public onLongTouchClickListener _longClick;
    public onSingleClickListener _singleClick;

    public onOption1ClickListener _option1Click;
    public onOption2ClickListener _option2Click;
    public onOption3ClickListener _option3Click;
    public onOption4ClickListener _option4Click;
    public onOption5ClickListener _option5Click;


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
        public ImageView imgOption1,imgOption2,imgOption3,imgOption4;

        public ListViewHolder ( View itemView ) {
            super ( itemView );
            txtHeader = (TextView) itemView.findViewById(R.id.firstLine);
            listView = (LinearLayout) itemView.findViewById(R.id.listView);

            imgOption1 = (ImageView) itemView.findViewById(R.id.imgOption1);
            imgOption2 = (ImageView) itemView.findViewById(R.id.imgOption2);
            imgOption3 = (ImageView) itemView.findViewById(R.id.imgOption3);
            imgOption4 = (ImageView) itemView.findViewById(R.id.imgOption4);
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
        if(position%2==0)
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

    public void setOption1ClickListener(onOption1ClickListener obj){
        this._option1Click = obj;
    }

    public void setOption2ClickListener(onOption2ClickListener obj){
        this._option2Click = obj;
    }

    public void setOption3ClickListener(onOption3ClickListener obj){
        this._option3Click = obj;
    }

    public void setOption4ClickListener(onOption4ClickListener obj){
        this._option4Click = obj;
    }

    public void setOption5ClickListener(onOption5ClickListener obj){
        this._option5Click = obj;
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

    public MyAdapter2(Context ctx, ArrayList<String> myDataset) {
        this._ctx = ctx;
        mDataset = myDataset;
    }

    @Override
    public MyAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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



                listHolder.imgOption1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        _option1Click.onOption1Click(position);
                    }
                });


                listHolder.imgOption2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        _option2Click.onOption2Click(position);
                    }
                });


                listHolder.imgOption3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        _option3Click.onOption3Click(position);
                    }
                });


                listHolder.imgOption4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        _option4Click.onOption4Click(position);
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