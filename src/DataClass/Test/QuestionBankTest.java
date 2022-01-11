package DataClass.Test;

import DataClass.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionBankTest {

    @Test
    void getDegreeQuestion() {
        // 取得指定難易度題目
        Sujects sujects = new Sujects();
        Suject suject = sujects.getSuject("微積分");
        QuestionBank questionBank = suject.getQuestionBank("導函數");
        Question question = questionBank.getDegreeQuestion(Data.DEGREE_OF_DIFFICULTY_EASY);
        assertEquals(question.getDegreeOfDifficulty() , "簡單");
        question = questionBank.getDegreeQuestion(Data.DEGREE_OF_DIFFICULTY_MEDIUM);
        assertEquals(question.getDegreeOfDifficulty() , "中等");
        question = questionBank.getDegreeQuestion(Data.DEGREE_OF_DIFFICULTY_DIFFICULT);
        assertEquals(question.getDegreeOfDifficulty() , "困難");
    }
}