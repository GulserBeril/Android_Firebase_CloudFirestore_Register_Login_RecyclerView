package com.gulser.android_firebase_cloudfirestore_register_login_recyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.gulser.android_firebase_cloudfirestore_register_login_recyclerview.Model.UserModel;
import com.gulser.android_firebase_cloudfirestore_register_login_recyclerview.R;

public class UserAdapter extends FirestoreRecyclerAdapter<UserModel, UserAdapter.UserHolder> {


    public UserAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserAdapter.UserHolder userHolder, int i, @NonNull UserModel userModel) {
        userHolder.student_number.setText(String.valueOf(userModel.getStudent_number()));
        userHolder.name.setText(String.valueOf(userModel.getName()));
        userHolder.surname.setText(String.valueOf(userModel.getSurname()));
    }

    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent,false);
        return new UserHolder(v);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        TextView student_number, name, surname;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            student_number = itemView.findViewById(R.id.student_number);
            name = itemView.findViewById(R.id.name);
            surname = itemView.findViewById(R.id.surname);
        }
    }
}
