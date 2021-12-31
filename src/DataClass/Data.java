package DataClass;

public class Data {
    public static String DB_PASSWORD = "Holmes5254";
    public static String DB_NAME = "kyutes";
    public static String USER_IDENTITY_STUDENT = "student";
    public static String USER_IDENTITY_EXAM_MANAGER = "examManager";
    public static String USER_IDENTITY_SYSTEM_MANAGER = "systemManager";
    public static String USER_IDENTITY_STUDENT_CN = "學生";
    public static String USER_IDENTITY_EXAM_MANAGER_CN = "評量管理者";
    public static String USER_IDENTITY_SYSTEM_MANAGER_CN = "系統管理者";
    public static String[] USER_IDENTITIES = {
            USER_IDENTITY_STUDENT,
            USER_IDENTITY_EXAM_MANAGER,
            USER_IDENTITY_SYSTEM_MANAGER
    };
    public static String[] USER_IDENTITIES_CN = {
            USER_IDENTITY_STUDENT_CN,
            USER_IDENTITY_EXAM_MANAGER_CN,
            USER_IDENTITY_SYSTEM_MANAGER_CN
    };
    public static String[] STUDENT_GRADES = {"一年級","二年級","三年級","四年級"};
    public static String[] SUBJECTS = {
            "教育學系","特殊教育學系","體育學系","事業經營學系","國文學系"
            ,"英語學系","地理學系","語言與文化學士原住民專班","音樂學系"
            ,"美術學系","視覺設計學系","數學系(數學組)","數學系(應用數學組)","化學系"
            ,"生物科技學系","物理學系","工業科技教育學系","工業設計學系"
            ,"電子工程學系","電機工程學系","軟體工程與管理學系"
    };
    public static String DEGREE_OF_DIFFICULTY_EASY = "簡單";
    public static String DEGREE_OF_DIFFICULTY_MEDIUM = "中等";
    public static String DEGREE_OF_DIFFICULTY_DIFFICULT = "困難";
    public static String QUESTION_TYPE_RIGHT_OR_WRONG = "是非題";
    public static String QUESTION_TYPE_MULTIPLE_CHOICE = "選擇題";
    public static String QUESTION_TYPE_FILL_IN_THE_QUESTION = "填充題";
    public static String[] QUESTION_TYPES = {
            QUESTION_TYPE_RIGHT_OR_WRONG,
            QUESTION_TYPE_MULTIPLE_CHOICE,
            QUESTION_TYPE_FILL_IN_THE_QUESTION
    };
    public static String[] DEGREE_OF_DIFFICULTY = {
            DEGREE_OF_DIFFICULTY_EASY,
            DEGREE_OF_DIFFICULTY_MEDIUM,
            DEGREE_OF_DIFFICULTY_DIFFICULT
    };
}
