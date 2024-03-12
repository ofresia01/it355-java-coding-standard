//File created by Tanner Davis

/*
    * Rule Name: Do not assume the remainder operator always returns a nonnegative result for integral operands
    * The remainder operation does not always return a nonnegative result
    * The remainder operation always returns a value with the same sign as its first argument
    * Always check if the result is negative if you want a positive number (hash tables)
*/

class NUM51J
{
    public static void main(String[] args) {
        int first = 10 % 3; //should result in 1
        int second = 10 % -3; //should result in 1
        int third = -10 % 3; //should result in -1
        int fourth = -10 % -3; //should result in -1

        System.out.println("The operation 10 % 3 results in: " + first);
        System.out.println("The operation 10 % -3 results in: " + second);
        System.out.println("The operation -10 % 3 results in: " + third);
        System.out.println("The operation -10 % -3 results in: " + fourth);
    }
    
}