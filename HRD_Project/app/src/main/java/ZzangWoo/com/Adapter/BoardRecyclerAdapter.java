package ZzangWoo.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ZzangWoo.com.MemberDTO.BoardDTO;
import ZzangWoo.com.R;
import ZzangWoo.com.ShowBoardActivity;

public class BoardRecyclerAdapter extends RecyclerView.Adapter<BoardRecyclerAdapter.ItemViewHolder> {

    ArrayList<HashMap<String, String>> boardLists;

    public BoardRecyclerAdapter(ArrayList<HashMap<String, String>> lists) {
        this.boardLists = lists;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                                  .inflate(R.layout.recycler_view_row, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int i) {

        HashMap<String, String> boardItem = boardLists.get(i);

        itemViewHolder.bbsIDText.setText(boardItem.get("bbsID"));
        itemViewHolder.bbsTitleText.setText(boardItem.get("bbsTitle"));
        itemViewHolder.userIDText.setText(boardItem.get("userID"));
        itemViewHolder.bbsDateText.setText(boardItem.get("bbsDate"));

        /*************************** 게시글 중 하나 클릭 ******************************/
        itemViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, ShowBoardActivity.class);
                intent.putExtra("bbsID", boardLists.get(i).get("bbsID"));
                intent.putExtra("userID", boardLists.get(i).get("userID"));
                context.startActivity(intent);
            }
        });
        /*****************************************************************************/
    }

    @Override
    public int getItemCount() {
        return this.boardLists.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        View mView;
        TextView bbsIDText, bbsTitleText, userIDText, bbsDateText;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            bbsIDText = (TextView) itemView.findViewById(R.id.bbsIDText);
            bbsTitleText = (TextView) itemView.findViewById(R.id.bbsTitleText);
            userIDText = (TextView) itemView.findViewById(R.id.userIDText);
            bbsDateText = (TextView) itemView.findViewById(R.id.bbsDateText);
            mView = itemView;
        }
    }
}
