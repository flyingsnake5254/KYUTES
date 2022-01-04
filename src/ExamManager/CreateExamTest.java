package ExamManager;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CreateExamTest {

    @Test
    void dataCheck() {
        CreateExam createExam = new CreateExam();
        assertEquals(createExam.dateCheck("2021/12/31 23:59","2022/01/01 1:1"),false);
        assertEquals(createExam.dateCheck("2021/12/31 23:59","2021/12/31 23:59"),false);
        assertEquals(createExam.dateCheck("2021/12/31 23:59","2021/12/31 23:58"),false);
        assertEquals(createExam.dateCheck("2021/12/31 23:58","2021/12/31 23:59"),false);

        assertEquals(createExam.dateCheck("2022/01/05 23:59","2022/01/05 23:28"),false);
        assertEquals(createExam.dateCheck("2022/01/04 23:00","2022/01/04 23:28"),true);
        assertEquals(createExam.dateCheck("2022/01/04 20:32","2022/01/04 20:33"),false);
        assertEquals(createExam.dateCheck("2022/01/04 23:28","2022/01/04 23:30"),true);

    }
}