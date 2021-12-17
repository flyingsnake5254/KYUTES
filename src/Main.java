public class Main {
    public static void main(String[] args){
        new LoginForm();
    }
    public void outputPath(){
        String path = this.getClass().getClassLoader().getResource(".").getPath();
        System.out.println(path);
    }
}
