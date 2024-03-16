//File created by Tanner Davis

/*
    Rule name: Use visually distinct identifiers
    Some alphanumeric characters are not easily distinguishable from one another
    [(2,Z) (O,0) (I,1) (5,S) (1,l) (0,D) (8,B) (n,h) (rn, m)]
    When naming variables, use characters that are easily distinguishable
*/
class DCL50
{
    public static void main(String[] args) {
        String I1l = "I1l";
        String O0 = "0O";
        String S5 = "S5";
        String Z2 = "Z2";

        System.out.println("Some alphanumeric characters are not easily distinguishable, such as:");
        System.out.println(I1l + ", " + O0 + ", " + S5 + ", " + Z2 + " etc.");
    }
   
}
