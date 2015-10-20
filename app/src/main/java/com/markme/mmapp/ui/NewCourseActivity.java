package com.markme.mmapp.ui;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.markme.mmapp.R;
import com.markme.mmapp.data.Course;
import com.markme.mmapp.db.DatabaseAPI;


public class NewCourseActivity extends AppCompatActivity {

    EditText courseId;
    EditText courseName;
    EditText engagedLectures;
    EditText attendedLectures;
    EditText maxLectures;
    EditText minAttendance;
    Button saveButton;
    DatabaseAPI dbApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.new_course_toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setTitle(R.string.title_activity_new_course);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(NewCourseActivity.this);
            }
        });

        dbApi = new DatabaseAPI(this);
        courseId = (EditText) findViewById(R.id.new_course_id);
        courseName = (EditText) findViewById(R.id.new_course_name);
        engagedLectures = (EditText) findViewById(R.id.new_course_engaged_lectures);
        attendedLectures = (EditText) findViewById(R.id. new_course_attended_lectures);
        maxLectures = (EditText) findViewById(R.id.new_course_max_lectures);
        minAttendance = (EditText) findViewById(R.id. new_course_min_attendance);
        saveButton = (Button) findViewById(R.id.save_new_course_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course newCourse = new Course(courseId.getText().toString(), courseName.getText().toString(), Integer.valueOf(maxLectures.getText().toString()),
                        Integer.valueOf(engagedLectures.getText().toString()), Integer.valueOf(attendedLectures.getText().toString()), Double.valueOf(minAttendance.getText().toString()));
                dbApi.addCourse(newCourse);
                NewCourseActivity.this.finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }
}
