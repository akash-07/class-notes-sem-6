package Interfaces;

/**
 * Created by @kash on 1/28/2018.
 */
public class ExecutableInterface {
    public static void main(String[] args)  {
        Result result = new Result();
        result.putRoll(31);
        result.putMarks(20);
        result.showTotalAndDisplayStudentInfo();
    }
}
