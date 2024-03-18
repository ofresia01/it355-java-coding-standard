public class Env05 {
    
    /**
     * Main Method for Env05
     * So long as remote monitoring isnt enabled through JMX or other means, code is compliant
     */
    public static void main(String[] args) {
        System.out.println("JMX remote monitoring is disabled by default.");
        System.out.println("If enabled during development, it should be disabled before deployment.");
    }
}
