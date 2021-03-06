package com.markme.mmapp.utils;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.markme.mmapp.R;
import com.markme.mmapp.data.Course;

import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>{

    private ArrayList<Course> courses;
    public SummaryAdapter(ArrayList<Course> courses){
        this.courses = courses;
    }


    @Override
    public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_layout,
                                                                                      parent,false);
        return new SummaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SummaryViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.courseNameView.setText(course.getCourseName());
        holder.courseIdView.setText(course.getCourseId());
        holder.lecturesAttendedView.setText(course.getLecturesAttended()+"");
        holder.lecturesEngagedView.setText(course.getLecturesEngaged()+"");
        if(course.getLecturesEngaged() > 0){
            double percentAttendance = ((double)course.getLecturesAttended()/course.getLecturesEngaged())*100;
            percentAttendance = ((double)Math.round(percentAttendance*100))/100;
            if(percentAttendance >= course.getMinAttendance()){
                holder.percentageAttendanceView.setTextColor(Color.GREEN);
            } else {
                holder.percentageAttendanceView.setTextColor(Color.RED);
            }
            holder.percentageAttendanceView.setText(percentAttendance+"%");
        } else {
            holder.percentageAttendanceView.setTextColor(Color.BLUE);
            holder.percentageAttendanceView.setText("N.A.");
        }

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    protected static class SummaryViewHolder extends RecyclerView.ViewHolder{

        public TextView courseNameView;
        public TextView courseIdView;
        public TextView lecturesEngagedView;
        public TextView lecturesAttendedView;
        public TextView percentageAttendanceView;
        public SummaryViewHolder(View itemView) {
            super(itemView);
            courseNameView = (TextView)itemView.findViewById(R.id.courseNameView);
            courseIdView = (TextView)itemView.findViewById(R.id.courseIdView);
            lecturesAttendedView = (TextView)itemView.findViewById(R.id.lecturesAttendedView);
            lecturesEngagedView = (TextView)itemView.findViewById(R.id.lecturesEngagedView);
            percentageAttendanceView =
                    (TextView)itemView.findViewById(R.id.percentageAttendanceView);
        }
    }
}
