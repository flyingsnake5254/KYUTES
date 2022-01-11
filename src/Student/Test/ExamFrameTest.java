package Student.Test;

import DataClass.*;
import Student.ExamFrame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExamFrameTest {

    @Test
    void getExamGradeTest() {
        assertEquals(getExamGrade(5 , 5) , 100);
        assertEquals(getExamGrade(0 , 5) , 0);
        assertEquals(getExamGrade(3 , 5) , 60);
    }

    public double getExamGrade(int correct , int totalQuestionNum){
        if(correct == 0) return 0;
        if(correct == totalQuestionNum) return 100;
        return (100 * ((double) correct / totalQuestionNum));
    }
}