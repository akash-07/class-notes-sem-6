package Interfaces;

/**
 * Created by @kash on 1/28/2018.
 */
public class Result extends Test implements Sports{
    @Override
    public int putSportWeightage() {
        return sportWeight;
    }

    void showTotalAndDisplayStudentInfo()   {
        System.out.println("Roll No: " +  getRoll());
        int total = getMarks() + putSportWeightage();
        System.out.println("Subject marks = " + getMarks());
        System.out.println("Sport weight = " + putSportWeightage());
        System.out.println("Total = " + total);
    }
}
