//File created by Tanner Davis

/*
 * Rule name: Do not use reflection to increase accessibility of classes, methods, or fields
 */


import java.lang.reflect.Field;

class SEC05J
{
    private int int1;
 
    /*
    * Allows users to remove any information held within the variables if necessary
    */
    public void zeroI() {
        this.int1 = 0;
    }

    public static void main(String[] args)
    {
        SEC05J sec05J = new SEC05J();

        //Demonstrate how reflection can be used to access and change private variables
        try
        {
            Field fieldInt1 = SEC05J.class.getDeclaredField("int1");
            fieldInt1.setAccessible(true);
            fieldInt1.setInt(sec05J, 42); //Changing the value using reflection

            System.out.println("Modified int1 using reflection: " + sec05J.int1);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            System.out.println("SecurityException: " + e.getMessage());
        }
    }
}