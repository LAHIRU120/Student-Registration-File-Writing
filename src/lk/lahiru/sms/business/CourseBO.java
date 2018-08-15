/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.lahiru.sms.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lk.lahiru.sms.dto.CourseDTO;

/**
 *
 * @author LAHIRU SASANKA
 */
public class CourseBO {
    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final File file;
    private static final LinkedList<String> idList = new LinkedList<>();

    public CourseBO() {
        this.file = new File("User/DataFile/Course.txt");
    }
    
    
    public boolean addCourse(CourseDTO courseDTO) throws Exception{
        boolean added=false;
        FileWriter fw= new FileWriter("User/DataFile/Course.txt",true);
        int id=courseDTO.getId();
        String name=courseDTO.getCourseName();
        double fee=courseDTO.getFee();
        String dour=courseDTO.getDuration();
        String rowLine = id+","+name+","+fee+","+dour+"\n";
        fw.write(rowLine);
        fw.flush();
        added= true;
        return added;
//            return true;
    }
    
    public static ArrayList<CourseDTO> getAllCourse() throws Exception{
        FileReader fr=new FileReader("User/DataFile/Course.txt");
        BufferedReader br = new BufferedReader(fr);
        ArrayList<CourseDTO> studentDTOs=new ArrayList<>();
        String rowLine;
        while((rowLine=br.readLine())!=null){
            String[] rowData=rowLine.split(",");
            int id=Integer.parseInt(rowData[0]);
            String name=rowData[1];
            double fee=Double.parseDouble(rowData[2]);
            String dour=rowData[3];
            CourseDTO sdto= new CourseDTO(id, name, fee, dour);
            studentDTOs.add(sdto);
        }
        return studentDTOs;
    }
    
    public static ArrayList<String> getAllCourseName() throws Exception{
        FileReader fr=new FileReader("User/DataFile/Course.txt");
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> studentDTOs=new ArrayList<>();
        String rowLine;
        while((rowLine=br.readLine())!=null){
            String[] rowData=rowLine.split(",");
            String name=rowData[1];
            
//            CourseDTO sdto= new CourseDTO(id, name, fee, dour);
            studentDTOs.add(name);
        }
        return studentDTOs;
    }
    
    public static CourseDTO serachCourse(int sid) throws Exception{
        FileReader fr=new FileReader("User/DataFile/Course.txt");
        BufferedReader br = new BufferedReader(fr);
        String rowLine;
        int id=000;
        String name = null;
        String address = null;
        double fee = 0.00;
        String dour = null;
        CourseDTO studentDTO=null ;
        while ((rowLine=br.readLine())!=null) {
            String[] rowData=rowLine.split(",");
            id=Integer.parseInt(rowData[0]);
            if (id==sid) {
                name=rowData[1];
                fee=Double.parseDouble(rowData[2]);
                dour=rowData[3];
                studentDTO= new CourseDTO(id, name, fee, dour);
            }
        }
        return studentDTO;
    }
    
    public boolean deleteCourse(String sid) throws Exception{
        BufferedReader bufferedReader =null;
        BufferedWriter bufferedWriter = null;
        boolean isDelete=false;
        FileReader fileReader = new FileReader("User/DataFile/Course.txt");
        bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> newData = new ArrayList<>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            if (!data[0].equals(sid)) {
                newData.add(line);
                isDelete=true;
            }
        }
        FileWriter fileWriter = new FileWriter("User/DataFile/Course.txt");
        bufferedWriter = new BufferedWriter(fileWriter);
        for (String string : newData) {
            bufferedWriter.write(string);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
        return isDelete;
            
    }
    
    public boolean updateCourse(CourseDTO player) throws Exception{
        BufferedReader bufferedReader =null;
        BufferedWriter bufferedWriter = null;
        boolean isDelete=true;
        FileReader fileReader = new FileReader("User/DataFile/Course.txt");
        bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> newData = new ArrayList<>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            if (!data[0].equals(player.getId())) {
                isDelete=false;
                newData.add(line);
            }else{
                String modifyData = "";
                System.out.println("afdsf "+modifyData);
                modifyData += player.getId() + ",";
                modifyData += player.getCourseName() + ",";
                modifyData += player.getFee() + ",";
                modifyData += player.getDuration();
                newData.add(modifyData);
                System.out.println("afdsf "+modifyData);
                isDelete=true;
            }
        }
            
        FileWriter fileWriter = new FileWriter("User/DataFile/Course.txt");
        bufferedWriter = new BufferedWriter(fileWriter);
        for (String string : newData) {
            bufferedWriter.write(string);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
        return isDelete;
    }
}
