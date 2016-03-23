package fangcunjian.net.dragview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mcin on 16/3/23.
 */
public class OverlayActivity extends BaseActivity {

    private final static String TAG = OverlayActivity.class.getSimpleName();


    private RecyclerView mRecyclerView;
    private PullHeaderLayout mContentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay);

        initValue();
    }


    private void initValue() {

        mRecyclerView = (RecyclerView) findViewById(R.id.scroll);
        mContentLayout = (PullHeaderLayout) findViewById(R.id.scroll_wrapper);
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.example);
        PullHeaderLayout.LayoutParams lp = new PullHeaderLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 720);
        mContentLayout.setHeaderView(imageView, lp);
        createScrollable();

    }

    protected RecyclerView createScrollable() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new CustomAdapter(this, getDummyData()));
        return mRecyclerView;
    }


    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        private Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<String> mItems;

        public CustomAdapter(Context context, ArrayList<String> items) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mItems = items;
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mContext, mInflater.inflate(R.layout.list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.textView.setText(mItems.get(position));
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            Context context;

            public ViewHolder(Context context, View view) {
                super(view);
                this.context = context;
                this.textView = (TextView) view.findViewById(android.R.id.text1);
                this.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click(getPosition() + 1);
                    }
                });
            }

            private void click(int i) {
                Toast.makeText(context, "Button " + i + " is clicked", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
