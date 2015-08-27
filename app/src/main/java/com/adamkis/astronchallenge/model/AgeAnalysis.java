package com.adamkis.astronchallenge.model;

import android.util.Log;

import com.adamkis.astronchallenge.common.Const;

import java.util.ArrayList;

/**
 * Created by adam on 8/28/15.
 */
public class AgeAnalysis {


    private final ArrayList<Attendee> attendees;

    private int numberOfStudents = 0;
    private int numberOfWorkers = 0;
    private int numberOfRetired = 0;

    public AgeAnalysis( ArrayList<Attendee> attendees ){
        this.attendees = attendees;
        analyseAgeGroups(attendees);
    }

    private void analyseAgeGroups( ArrayList<Attendee> attendees ){

        for( Attendee a : attendees ){
            Attendee.AgeGroup ageGroup = a.getAgeGroup();
            if( ageGroup == Attendee.AgeGroup.STUDENT ){
                numberOfStudents++;
            }else if( ageGroup == Attendee.AgeGroup.WORKER ){
                numberOfWorkers++;
            }else if( ageGroup == Attendee.AgeGroup.RETIRED ){
                numberOfRetired++;
            }
        }

    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public int getNumberOfRetired() {
        return numberOfRetired;
    }
}
