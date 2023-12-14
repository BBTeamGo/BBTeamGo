package com.example.bbteamgo;



import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    public    String email = "";
    private ArrayList<Chat> mDataset;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;


        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View

            textView = v.findViewById(R.id.textView2);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (mDataset.get(position).email.equals(email)) {
            return 1;
        } else {
            return 2;
        }
    }


    public  ChatAdapter(ArrayList<Chat> myDataSet,String email) {
        ArrayList<Chat> mDataSet = myDataSet;
        mDataSet= myDataSet;
        this.email =email;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_text_view, viewGroup, false);
if(viewType==1){
    view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_chat_text_view,viewGroup,false);

}
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.textView.setText(mDataset.get(position).getText());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
