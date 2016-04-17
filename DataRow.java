package client;

public class DataRow {
    int location;
    String NoOfColumn = "003";
    int NumberOfColumn;
    int ColumnLength;
    int ColumnValue;
    int StudentId;
    String FirstName;
    String LastName;
    int sizeofStudentID;
    int sizeofFirstName;
    int SizeOfLastName;
    Boolean DeleteStatus = false;

    public String GetRowData() {
        return NoOfColumn + String.format("%03d", String.valueOf(StudentId).length()) + StudentId +
               String.format("%03d", FirstName.length()) + FirstName + String.format("%03d", LastName.length()) +
               LastName;
    }
}

