package com.example.project_prm392_kidmanagement;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392_kidmanagement.DAO.TeacherDao;
import com.example.project_prm392_kidmanagement.Entity.Teacher;

import java.util.List;

public class TeacherListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeacherDao teacherDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teacher);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recyclerTeacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        teacherDao = new TeacherDao(this);
        List<Teacher> teacherList = teacherDao.getAll();

        // Adapter gộp trong Activity, không cần file riêng
        recyclerView.setAdapter(new RecyclerView.Adapter<TeacherViewHolder>() {
            @NonNull
            @Override
            public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView tv = new TextView(parent.getContext());
                tv.setPadding(32, 24, 32, 24);
                tv.setTextSize(16);
                return new TeacherViewHolder(tv);
            }

            @Override
            public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
                Teacher t = teacherList.get(position);
                holder.textView.setText(t.getFullName() + " - " + t.getPhone());
            }

            @Override
            public int getItemCount() {
                return teacherList.size();
            }
        });
    }

    static class TeacherViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
