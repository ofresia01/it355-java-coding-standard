//File created by Tanner Davis

/*
 * Rule name: Minimize privileges before deserializing from a privilged context
 */


 import java.io.*;
 import java.security.*;


class SER08
{
    private static class CalendarAccessControlContext
    {
        private static final AccessControlContext INSTANCE;

        static
        {
            RuntimePermission permission = new RuntimePermission("accessClassInPackage.sun.util.calendar");
            PermissionCollection permissions = permission.newPermissionCollection();
            permissions.add(permission);
            INSTANCE = new AccessControlContext(new ProtectionDomain[]
            {
                new ProtectionDomain(null, permissions)
            });
        }
    }

    private static class ZoneInfo implements Serializable
    {
        private String zoneName;
        private int offset;

        /*
         * Constructs a ZoneInfo object with the given name and offset.
         *
         * @param zoneName The name of the time zone.
         * @param offset   The offset of the time zone.
         */
        public ZoneInfo(String zoneName, int offset)
        {
            this.zoneName = zoneName;
            this.offset = offset;
        }

       /*
         * Gets the name of the time zone.
         *
         * @return The name of the time zone.
         */
        public String getZoneName()
        {
           return zoneName;
        }

        /*
         * Gets the offset of the time zone.
         *
         * @return The offset of the time zone.
         */
        public int getOffset()
        {
            return offset;
        }


        /*
         * Returns a string representation of the ZoneInfo object.
         *
         * @return A string representation of the ZoneInfo object.
         */
        public String toString()
        {
            return "ZoneInfo{" + "zoneName='" + zoneName + '\'' + ", offset=" + offset + '}';
        }
    }

    public static void main(String[] args)
    {
        ZoneInfo zoneInfo = null;

        try
        {
            FileInputStream fileInputStream = new FileInputStream("serializedZoneInfo.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            zoneInfo = AccessController.doPrivileged(new PrivilegedExceptionAction<ZoneInfo>()
            {
                public ZoneInfo run() throws Exception
                {
                    return (ZoneInfo) objectInputStream.readObject();
                }
            }, CalendarAccessControlContext.INSTANCE);

            objectInputStream.close();
        } catch (IOException | PrivilegedActionException e)
        {
            e.printStackTrace();
        }

        if(zoneInfo != null)
        {
            // Process the deserialized ZoneInfo object
            System.out.println("Deserialized ZoneInfo:");
            System.out.println(zoneInfo);
        }
    }
}
