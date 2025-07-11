package com.example.project_prm392_kidmanagement;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392_kidmanagement.DAO.TeacherDao;
import com.example.project_prm392_kidmanagement.Entity.Teacher;
import com.example.project_prm392_kidmanagement.R;

public class AddTeacherActivity extends AppCompatActivity {

    private EditText edtTeacherId, edtFullName, edtAddress, edtPhone, edtDob;
    private Button btnSaveTeacher;

    private TeacherDao teacherDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish()); // Quay lại màn hình trước

        Button btnViewTeachers = findViewById(R.id.btnViewTeachers);
        btnViewTeachers.setOnClickListener(v -> {
            Intent intent = new Intent(AddTeacherActivity.this, TeacherListActivity.class);
            startActivity(intent);
        });

        // Ánh xạ view
        edtTeacherId = findViewById(R.id.edtTeacherId);
        edtFullName = findViewById(R.id.edtFullName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        edtDob = findViewById(R.id.edtDob);
        btnSaveTeacher = findViewById(R.id.btnSaveTeacher);

        // Khởi tạo DAO
        teacherDao = new TeacherDao(this);

        // Sự kiện nút lưu
        btnSaveTeacher.setOnClickListener(v -> saveTeacher());
    }

    private void saveTeacher() {
        String id = edtTeacherId.getText().toString().trim();
        String name = edtFullName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String dob = edtDob.getText().toString().trim();

        // Kiểm tra dữ liệu
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ Mã giáo viên và Họ tên.", Toast.LENGTH_SHORT).show();
            return;
        }

        Teacher teacher = new Teacher(id, name, address, phone, dob);
        long result = teacherDao.insert(teacher);

        if (result != -1) {
            Toast.makeText(this, "Thêm giáo viên thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại màn hình trước
        } else {
            Toast.makeText(this, "Thêm thất bại! Mã giáo viên có thể đã tồn tại.", Toast.LENGTH_SHORT).show();
        }
    }
}
