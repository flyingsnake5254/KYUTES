package ExamManager.Test;

import ExamManager.CreateExam;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateExamTest {

    @Test
    void dateCheck() {
        // 若兩個輸入時間接早於"現在時間"，也會回傳 false
        CreateExam createExam = new CreateExam();
        assertEquals(createExam.dateCheck("2021/12/31 23:59","2022/01/01 01:01"),false);
        assertEquals(createExam.dateCheck("2021/12/31 23:59","2021/12/31 23:59"),false);
        assertEquals(createExam.dateCheck("2021/12/31 23:59","2021/12/31 23:58"),false);
        assertEquals(createExam.dateCheck("2021/12/31 23:58","2021/12/31 23:59"),false);

        assertEquals(createExam.dateCheck("2022/01/05 23:59","2022/01/05 23:28"),false);
        assertEquals(createExam.dateCheck("2022/01/04 23:00","2022/01/04 23:28"),false);
        assertEquals(createExam.dateCheck("2022/01/04 20:32","2022/01/04 20:33"),false);
        assertEquals(createExam.dateCheck("2022/01/04 23:28","2022/01/04 23:30"),false);

        assertEquals(createExam.dateCheck("2023/01/05 23:59","2023/01/05 23:28"),false);
        assertEquals(createExam.dateCheck("2023/01/04 23:00","2023/01/04 23:28"),true);
        assertEquals(createExam.dateCheck("2023/01/04 20:32","2023/01/04 20:33"),true);
        assertEquals(createExam.dateCheck("2023/01/04 23:28","2023/01/04 23:30"),true);
    }
}