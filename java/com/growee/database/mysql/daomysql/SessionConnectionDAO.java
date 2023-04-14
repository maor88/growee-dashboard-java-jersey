package com.growee.database.mysql.daomysql;

public class SessionConnectionDAO extends DAOBaseClass {

    private static final String tableName = "connection_session";
    private static final String primaryKey = "user_id";

    public static void increaseDeviceIdOpenSession(String userId) {

        String query = "insert into " + tableName + " (" +primaryKey +") values('"+ userId +"') on duplicate key update "+ SessionConnectionColumn.ConnectionNumber.getColumnName() + "= "
                + SessionConnectionColumn.ConnectionNumber.getColumnName() + "+1" ;
        try {
            connect();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        finally {
            disconnect();
        }

    }

    public static void decreaseDeviceIdOpenSession(String userId) {

        String query = "insert into " + tableName + " (" +primaryKey +","+ SessionConnectionColumn.ConnectionNumber.getColumnName()+") values('"+ userId +"',0) on duplicate key update "+ SessionConnectionColumn.ConnectionNumber.getColumnName() + "= "
                + SessionConnectionColumn.ConnectionNumber.getColumnName() + "-1 and "+SessionConnectionColumn.ConnectionNumber.getColumnName()+" > 0" ;
        try {
            connect();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        finally {
            disconnect();
        }

    }

    public static boolean isNoSessionForUser(String userId) {
        String query = "select * from "+tableName + " where " + SessionConnectionColumn.DeviceId.getColumnName() + "= '" + userId + "'";
        boolean res = false;
        try {
            connect();
            rs = st.executeQuery(query);
            if (rs.next() && !rs.wasNull()) {
                if(rs.getString(SessionConnectionColumn.ConnectionNumber.getColumnName()).equals("0")) {
                    res = true;
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return res;
    }


    public enum SessionConnectionColumn {
        DeviceId("user_id"), ConnectionNumber("connection_number");

        private String column;

        SessionConnectionColumn(String column) {
            this.column = column;
        }

        public String getColumnName() {
            return column;
        }
    }
}
