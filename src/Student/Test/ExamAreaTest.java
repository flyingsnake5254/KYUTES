package Student.Test;

import DataClass.User;
import DataClass.Users;
import Student.ExamArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamAreaTest {

    @Test
    void dateCheck() {
        // 查看"現在時間" 使否在考試期間
        Users users = new Users();
        User user = null;
        for(User u : users.getUsers())
            if(!u.getStudentGroup().equals(""))
                user = u;
        ExamArea examArea = new ExamArea(user);
        assertEquals(examArea.dateCheck("2022/01/05 16:00" , "2022/01/05 16:50") , false);
        assertEquals(examArea.dateCheck("2022/01/05 10:00" , "2022/01/05 11:50") , false);
        assertEquals(examArea.dateCheck("2022/01/05 15:00" , "2022/01/05 16:50") , true);
    }
}