package by.beerfest.constant;

import static by.beerfest.constant.ColumnName.*;

public class Query {
//@TODO Заменил константами даже не в селектах
    public static final String FIND_USER_BY_ID = "SELECT " + COL_ID_USER + "," + COL_EMAIL + "," + COL_PASSWORD + "," + COL_ROLE_NAME + "," + COL_PHONE_NUMBER + "," + COL_AVATAR + " FROM user " +
            "join role " +
            "on role.id_role = user.id_role " +
            "WHERE " + COL_ID_USER + "= ?";
    public static final String FIND_USER_BY_EMAIL = "SELECT " + COL_ID_USER + "," + COL_EMAIL + "," + COL_PASSWORD + "," + COL_ROLE_NAME + "," + COL_PHONE_NUMBER + "," + COL_AVATAR + " FROM user " +
            "join role " +
            "on role.id_role = user.id_role " +
            "WHERE " + COL_EMAIL + "= ?";
    public static final String FIND_PLACE_BY_ID = "SELECT " + COL_ID_PLACE + "," + COL_TYPE + ", " + COL_SEATS + " FROM place join placetype on place.id_type = placetype.id_type WHERE place." + COL_ID_PLACE + " = ?";
    public static final String USER_INSERT = "INSERT INTO user(" + COL_EMAIL + "," + COL_PASSWORD + ",id_role," + COL_PHONE_NUMBER + "," + COL_AVATAR + ") VALUES(?, ?, (SELECT id_role from role WHERE " + COL_ROLE_NAME + " = 'USER'), ?,?)";
    public static final String USER_UPDATE = "UPDATE user SET " + COL_EMAIL + " = ?, " + COL_PASSWORD + " = ?, " + COL_PHONE_NUMBER + " = ?, id_role = (SELECT id_role FROM role WHERE " + COL_ROLE_NAME + " = ?), " + COL_AVATAR + " = ? " +
            "WHERE " + COL_ID_USER + " = ?";
    public static final String PLACE_INSERT = "INSERT INTO place(id_type," + COL_SEATS + ") VALUES((SELECT id_type FROM placetype where " + COL_TYPE + "=?),?)";
    public static final String FIND_FREE_PLACE = "SELECT placetype.type as "+ COL_TYPE +", " + COL_ID_PLACE + ", " + COL_SEATS + " FROM place\n" +
            "join placetype\n" +
            "on place.id_type = placetype.id_type\n" +
            "WHERE " + COL_ID_PLACE + " NOT IN (SELECT " + COL_ID_PLACE + " from participant)";
    public static final String FIND_ALL_PARTICIPANT = "SELECT participant." + COL_ID_USER + ", " + COL_CONFIRMED + ", " + COL_NAME + "," + COL_EMAIL + "," + COL_PASSWORD + "," + COL_PHONE_NUMBER + "," + COL_AVATAR + "," + COL_ROLE_NAME + ",participant." + COL_ID_PLACE + "," + COL_SEATS + ",type FROM participant\n" +
            "            join place \n" +
            "            on participant.id as " + COL_ID_PLACE + " = place.id as " + COL_ID_PLACE + " \n" +
            "            join placetype p \n" +
            "            on place.id_type = p.id_type\n" +
            "            join user \n" +
            "            on user." + COL_ID_USER + " = participant." + COL_ID_USER + "" +
            "            join role" +
            "            on role.id_role = user.id_role";
    public static final String FIND_UNCONFIRMED_PARTICIPANT = "SELECT participant." + COL_ID_USER + "," + COL_CONFIRMED + "," + COL_NAME + "," + COL_EMAIL + "," + COL_PASSWORD + "," + COL_PHONE_NUMBER + "," + COL_AVATAR + "," + COL_ROLE_NAME + ",participant." + COL_ID_PLACE + "," + COL_SEATS + ",type FROM participant\n" +
            "            join place \n" +
            "            on participant." + COL_ID_PLACE + " = place." + COL_ID_PLACE + " \n" +
            "            join placetype p \n" +
            "            on place.id_type = p.id_type\n" +
            "            join user \n" +
            "            on user." + COL_ID_USER + " = participant." + COL_ID_USER + " " +
            "            join role" +
            "            on role.id_role = user.id_role" +
            "            WHERE " + COL_CONFIRMED + " = false";
    public static final String FIND_PARTICIPANT_BY_ID = "SELECT participant." + COL_ID_USER + "," + COL_CONFIRMED + "," + COL_NAME + "," + COL_EMAIL + "," + COL_PASSWORD + "," + COL_PHONE_NUMBER + "," + COL_AVATAR + "," + COL_ROLE_NAME + ",participant." + COL_ID_PLACE + "," + COL_SEATS + ",type FROM participant " +
            " join user " +
            " on participant." + COL_ID_USER + " = user." + COL_ID_USER + " " +
            " join place" +
            " on participant." + COL_ID_PLACE + " = place." + COL_ID_PLACE + " " +
            " join placetype " +
            " on place.id_type = placetype.id_type" +
            " join role" +
            " on user.id_role = role.id_role" +
            " WHERE participant." + COL_ID_USER + " = ? ";
    public static final String PARTICIPANT_INSERT = "INSERT INTO PARTICIPANT(" + COL_ID_USER + "," + COL_NAME + "," + COL_ID_PLACE + "," + COL_CONFIRMED + ") VALUES(?, ?, ?, ?);";
    public static final String USER_TO_PARTICIPANT_UPDATE = "UPDATE user SET id_role = (SELECT id_role FROM role where " + COL_ROLE_NAME + " = 'PARTICIPANT') WHERE " + COL_ID_USER + " = ?;";
    public static final String DELETE_PARTICIPANT_BY_ID = "DELETE FROM PARTICIPANT WHERE " + COL_ID_USER + " = ?";
    public static final String PARTICIPANT_TO_USER_UPDATE = "UPDATE user SET id_role = (SELECT id_role FROM role where " + COL_ROLE_NAME + " = 'USER')  WHERE " + COL_ID_USER + " = ?;";
    public static final String PARTICIPANT_UPDATE = "UPDATE participant SET " + COL_NAME + " = ?, " + COL_ID_PLACE + " = ?, " + COL_CONFIRMED + " = ? WHERE " + COL_ID_USER + " = ?;";
    public static final String FIND_RESERVED_PLACE = "SELECT " + COL_ID_PLACE + "," + COL_TYPE + "," + COL_SEATS + "  FROM place" +
            " join placetype " +
            "on placetype.id_type = place.id_type " +
            "WHERE place." + COL_ID_PLACE + " IN (SELECT participant." + COL_ID_PLACE + " FROM participant WHERE " + COL_CONFIRMED + " = true)";
    public static final String GUEST_INSERT = "INSERT INTO GUEST(" + COL_ID_USER + ",default_ticket_number, medium_ticket_number, large_ticket_number) VALUES(?,?,?,?);";
    public static final String USER_TO_GUEST_UPDATE = "UPDATE user SET id_role = (SELECT id_role FROM role where " + COL_ROLE_NAME + " = 'GUEST')  WHERE " + COL_ID_USER + " = ?;";
    public static final String ADD_TICKET = "INSERT INTO guest_ticket(" + COL_ID_USER + ",ticket_number," + COL_TICKET_TYPE + ") VALUES(?,?,?);";
    public static final String FIND_TICKET_GROUP_BY_TYPE = "SELECT SUM(ticket_number) as " + TICKET_SUM + "," + COL_TICKET_TYPE + " from guest_ticket " +
            "GROUP BY " + COL_TICKET_TYPE + "";



    private Query() {
    }
}
