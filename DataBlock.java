package client;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.crypto.Data;

public class DataBlock {
    String Header = "Header";
    int NumberOfRows = 0;
    int FreeSpacePointer;
    ArrayList<DataRow> DataRowData = new ArrayList<>();
    ArrayList<String> offsetAdress = new ArrayList<>();
    String FinalString;
    String StudentIdExists = "Student Id exists";
    DataRow datarow;

    public void insert(int studentid, String FirstName, String LastName) {
        this.NumberOfRows += 1;
        datarow = new DataRow();
        datarow.FirstName = FirstName;
        String string = datarow.LastName = LastName;
        datarow.StudentId = studentid;
        datarow.sizeofFirstName = FirstName.length();
        datarow.sizeofStudentID = Integer.toString(studentid).length();
        datarow.SizeOfLastName = LastName.length();
        DataRowData.add(datarow);
    }

    public String update(int studentid, String FirstName, String LastName) {
        String str = Integer.toString(studentid);
        String x = "Student Id Does not Exist";
        for (int i = 0; i < DataRowData.size(); i++) {
            if (studentid == DataRowData.get(i).StudentId) {
                x = "Student Id exists";
                DataRowData.get(i).FirstName = FirstName;
                DataRowData.get(i).LastName = LastName;
                DataRowData.get(i).sizeofFirstName = FirstName.length();
                DataRowData.get(i).sizeofStudentID = Integer.toString(studentid).length();
                DataRowData.get(i).SizeOfLastName = LastName.length();
            }
        }
        return x;
    }

 
    public String DisplayData() {

        String rowsData = "";
        for (DataRow row : this.DataRowData) {
            rowsData += row.GetRowData();
        }
        String finalStirng = "";
        finalStirng = "Header" + String.format("%03d", NumberOfRows) + "{#}" + GetOffsetAdress(NumberOfRows) + rowsData;
        int freeSpacePointer = finalStirng.length();
        finalStirng = finalStirng.replaceFirst("\\{#\\}", String.valueOf(String.format("%03d", freeSpacePointer)));
        return finalStirng;
        
    }

    public String GetOffsetAdress(int NumOfRows) {
        int currentLocation = 6 + 3 + 3 + 3 * this.DataRowData.size();
        String s = "";
        for (DataRow dataRow : this.DataRowData) {
            dataRow.location = currentLocation;
            currentLocation = currentLocation + dataRow.GetRowData().length();
            s += String.format("%03d", dataRow.location);
            offsetAdress.add(s);
        }
        return s;
    }

    public LinkedList Select(int Studentid) {
        LinkedList<String> Names = new LinkedList<>();
        for (int i = 0; i < DataRowData.size(); i++) {
            if (Studentid == DataRowData.get(i).StudentId) {
                Names.add(DataRowData.get(i).FirstName);
                Names.add(DataRowData.get(i).LastName);
                Names.add(StudentIdExists);
            }
        }
        return Names;
    }

    public boolean Delete(int StudentId) {

        boolean deleted = false;
        for (int i = 0; i < DataRowData.size(); i++) {
            if (StudentId == DataRowData.get(i).StudentId) {

                DataRowData.remove(i);
                NumberOfRows = NumberOfRows - 1;
                offsetAdress.set(i, "000");
                deleted = true;
            }
        }
        return deleted;
    }

    public boolean StudentIdExist(int studentID) {
        boolean x = false;
        for (int i = 0; i < DataRowData.size(); i++) {
            if (studentID == DataRowData.get(i).StudentId) {
                x = true;
            }
        }
        return x;
    }

    public String DisplaySelectQuery(int StudentID) {
        String RowData = "";
        for (int i = 0; i < DataRowData.size(); i++) {
            RowData =
                RowData + String.format("%03d", DataRowData.get(i).sizeofStudentID) + DataRowData.get(i).StudentId +
                String.format("%03d", DataRowData.get(i).sizeofFirstName) + DataRowData.get(i).FirstName +
                String.format("%03d", DataRowData.get(i).SizeOfLastName) + DataRowData.get(i).LastName;
        }
        return RowData;
    }
}
