package com.reign.danapotplant.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.reign.danapotplant.R;
import com.reign.danapotplant.models.Exam;
import com.reign.danapotplant.models.Skill;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SkillsAdapter skillsdapter;
    private ExamsAdapter examsAdapter;

    public ProfileFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ProfileFragment newInstance(int sectionNumber) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView about = (TextView) rootView.findViewById(R.id.about);

        RecyclerView listSkills = (RecyclerView) rootView.findViewById(R.id.listSkills);
        skillsdapter = new SkillsAdapter(getContext(), new ArrayList<Skill>(), new SkillsAdapter.OnItemClickListener() {
            @Override
            public void onClick(Skill item) {
                Toast.makeText(getContext(), item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        listSkills.setAdapter(skillsdapter);

        RecyclerView listExams = (RecyclerView) rootView.findViewById(R.id.listExams);
        examsAdapter = new ExamsAdapter(getContext(), new ArrayList<Exam>(), new ExamsAdapter.OnItemClickListener() {
            @Override
            public void onClick(Exam item) {
                Toast.makeText(getContext(), item.getName(), Toast.LENGTH_SHORT).show();

            }
        });
        listExams.setAdapter(examsAdapter);

        return rootView;
    }

    public void setSkills(ArrayList<Skill> skills) {
        skillsdapter.setData(skills);
    }

    public void setExams(ArrayList<Exam> exams) {
        examsAdapter.setData(exams);
    }
}
