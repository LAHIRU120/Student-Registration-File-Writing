/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.lahiru.sms.dto;

import java.io.Serializable;

/**
 *
 * @author LAHIRU SASANKA
 */
public class CourseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String courseName;
    private double fee;
    private String duration;

    public CourseDTO() {
    }

    public CourseDTO(int id, String courseName, double fee, String duration) {
        this.id = id;
        this.courseName = courseName;
        this.fee = fee;
        this.duration = duration;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the fee
     */
    public double getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CourseDTO{" + "id=" + id + ", courseName=" + courseName + ", fee=" + fee + ", duration=" + duration + '}';
    }
        
}
