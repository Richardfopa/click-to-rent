package com.orange.click_2_rent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Firebase.FireBaseUtils;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Service;

import java.util.List;

public class ListServiceAdapter extends RecyclerView.Adapter<ListServiceAdapter.ListSerViceViewHolder> {
    private List<Service> mService;
    private Context context;
    public ListServiceAdapter(Context context, List<Service> listService) {
        this.context = context;
        this.mService = listService;
    }

    @NonNull
    @Override
    public ListServiceAdapter.ListSerViceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_service_item, parent, false);
        return new ListSerViceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListServiceAdapter.ListSerViceViewHolder holder, int position) {

            Service service = mService.get(position);
            holder.list_item.setText(service.getTitle());
    }

    @Override
    public int getItemCount() {
        return mService.size();
    }

    public class ListSerViceViewHolder extends RecyclerView.ViewHolder {
        private TextView list_item;
        public ListSerViceViewHolder(@NonNull View itemView) {
            super(itemView);
            list_item = itemView.findViewById(R.id.list_item);
        }
    }
}
