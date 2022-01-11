package Student.Test;

import DataClass.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PracticeFrameTest {

    @Test
    void adjustQuestionTest() {
        // 調整題目難易度機制
        assertEquals(adjustQuestion(5 , true) , 6);
        assertEquals(adjustQuestion(1 , false) , 1);
        assertEquals(adjustQuestion(10 , true) , 10);

        // 根據 value 篩選題目難易度
        assertEquals(adjustDegree(1) , Data.DEGREE_OF_DIFFICULTY_EASY);
        assertEquals(adjustDegree(3) , Data.DEGREE_OF_DIFFICULTY_EASY);
        assertEquals(adjustDegree(4) , Data.DEGREE_OF_DIFFICULTY_MEDIUM);
        assertEquals(adjustDegree(7) , Data.DEGREE_OF_DIFFICULTY_MEDIUM);
        assertEquals(adjustDegree(10) , Data.DEGREE_OF_DIFFICULTY_DIFFICULT);
    }

    @Test
    void adjustDegreeTest() {

    }

    public int adjustQuestion(int value , boolean b){
        if(b){
            if(value < 10) value ++;
        }
        else{
            if(value > 1) value --;
        }
        return value;
    }

    public String adjustDegree(int value){
        if(value >= 1 && value <= 3) return Data.DEGREE_OF_DIFFICULTY_EASY;
        else if(value >= 4 && value <= 7) return Data.DEGREE_OF_DIFFICULTY_MEDIUM;
        else return Data.DEGREE_OF_DIFFICULTY_DIFFICULT;
    }
}