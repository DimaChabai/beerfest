package by.beerfest.constant;

public class Query {
    public static final String FIND_USER_BY_ID = "SELECT id_user, email, password, role_name, phone_number, avatar FROM user " +
            "join role " +
            "on role.id_role = user.id_role " +
            "WHERE id_user= ?";
    public static final String FIND_USER_BY_EMAIL = "SELECT id_user, email, password, role_name, phone_number, avatar FROM user " +
            "join role " +
            "on role.id_role = user.id_role " +
            "WHERE  email= ?";
    public static final String FIND_PLACE_BY_ID = "SELECT id_place, type,  seats FROM place join placetype on place.id_type = placetype.id_type WHERE place.id_place = ?";
    public static final String USER_INSERT = "INSERT INTO user( email, password,id_role, phone_number, avatar) VALUES(?, ?, (SELECT id_role from role WHERE  role_name = 'USER'), ?,?)";
    public static final String USER_UPDATE = "UPDATE user SET  email = ?,  password = ?,  phone_number = ?, id_role = (SELECT id_role FROM role WHERE  role_name = ?),  avatar = ? " +
            "WHERE id_user = ?";
    public static final String PLACE_INSERT = "INSERT INTO place(id_type, seats) VALUES((SELECT id_type FROM placetype where  type=?),?)";
    public static final String FIND_FREE_PLACE = "SELECT placetype.type as  type, id_place,  seats FROM place " +
            "join placetype " +
            "on place.id_type = placetype.id_type " +
            "WHERE id_place NOT IN (SELECT id_place from participant)";
    public static final String FIND_ALL_PARTICIPANT = "SELECT participant.id_user, confirmed, beer_type, name, email, password, phone_number, avatar, role_name,participant.id_place, seats,type FROM participant " +
            "            join place  " +
            "            on  participant.id_place = place.id_place " +
            "            join placetype p  " +
            "            on place.id_type = p.id_type " +
            "            join user  " +
            "            on user.id_user = participant.id_user" +
            "            join role" +
            "            on role.id_role = user.id_role" +
            "            join participant_beer" +
            "            on participant_beer.id_user = participant.id_user" +
            "            join beer" +
            "            on beer.id_beer = participant_beer.id_beer";
    public static final String FIND_UNCONFIRMED_PARTICIPANT = "SELECT participant.id_user, confirmed, beer_type,name, email, password, phone_number, avatar, role_name,participant.id_place, seats,type FROM participant " +
            "            join place " +
            "            on participant.id_place = place.id_place" +
            "            join placetype p " +
            "            on place.id_type = p.id_type" +
            "            join user " +
            "            on user.id_user = participant.id_user " +
            "            join role" +
            "            on role.id_role = user.id_role" +
            "            join participant_beer" +
            "            on participant_beer.id_user = participant.id_user" +
            "            join beer" +
            "            on beer.id_beer" + " = participant_beer.id_beer" +
            "            WHERE confirmed = false";
    public static final String FIND_CONFIRMED_PARTICIPANT_FROM_TO = "SELECT participant.id_user, confirmed, beer_type,name, email, password, phone_number, avatar, role_name,participant.id_place, seats,type FROM participant " +
            "            join place " +
            "            on participant.id_place = place.id_place" +
            "            join placetype p " +
            "            on place.id_type = p.id_type" +
            "            join user " +
            "            on user.id_user = participant.id_user " +
            "            join role" +
            "            on role.id_role = user.id_role" +
            "            join participant_beer" +
            "            on participant_beer.id_user = participant.id_user" +
            "            join beer" +
            "            on beer.id_beer" + " = participant_beer.id_beer" +
            "            WHERE confirmed = true" +
            "            limit ?,?";
    public static final String FIND_PARTICIPANT_BY_ID = "SELECT participant.id_user, confirmed, beer_type,name, email, password, phone_number, avatar, role_name,participant.id_place, seats,type FROM participant " +
            "            join user " +
            "            on participant.id_user = user.id_user " +
            "            join place" +
            "            on participant.id_place = place.id_place " +
            "            join placetype " +
            "            on place.id_type = placetype.id_type" +
            "            join role" +
            "            on user.id_role = role.id_role" +
            "            join participant_beer" +
            "            on participant_beer.id_user = participant.id_user" +
            "            join beer" +
            "            on beer.id_beer" + " = participant_beer.id_beer" +
            "            WHERE participant.id_user = ? ";
    public static final String PARTICIPANT_INSERT = "INSERT INTO PARTICIPANT(id_user,name,id_place, confirmed) VALUES(?, ?, ?, ?);";
    public static final String USER_TO_PARTICIPANT_UPDATE = "UPDATE user SET id_role = (SELECT id_role FROM role where  role_name = 'PARTICIPANT') WHERE id_user = ?;";
    public static final String DELETE_PARTICIPANT_BY_ID = "DELETE FROM PARTICIPANT WHERE id_user = ?";
    public static final String DELETE_PARTICIPANT_BEER_BY_ID = "DELETE FROM PARTICIPANT_BEER WHERE id_user = ?";
    public static final String PARTICIPANT_TO_USER_UPDATE = "UPDATE user SET id_role = (SELECT id_role FROM role where  role_name = 'USER')  WHERE id_user = ?;";
    public static final String PARTICIPANT_UPDATE = "UPDATE participant SET name = ?, id_place = ?, confirmed = ? WHERE id_user = ?;";
    public static final String PARTICIPANT_BEER_UPDATE = "UPDATE participant_beer SET id_beer" + " = (Select id_beer" + " from beer where beer_type = ?) WHERE  id_user  = ?";
    public static final String FIND_RESERVED_PLACE = "SELECT id_place, type, seats  FROM place" +
            "            join placetype " +
            "            on placetype.id_type = place.id_type " +
            "            WHERE place.id_place IN (SELECT participant.id_place FROM participant WHERE confirmed = true)";
    public static final String GUEST_INSERT = "INSERT INTO GUEST(id_user,default_ticket_number, medium_ticket_number, large_ticket_number) VALUES(?,?,?,?);";
    public static final String USER_TO_GUEST_UPDATE = "UPDATE user SET id_role = (SELECT id_role FROM role where  role_name = 'GUEST')  WHERE id_user = ?;";
    public static final String ADD_TICKET = "INSERT INTO guest_ticket(id_user,ticket_number, ticket_type) VALUES(?,?,?);";
    public static final String FIND_TICKET_GROUP_BY_TYPE = "SELECT SUM(ticket_number) as ticket_sum, ticket_type from guest_ticket " +
            "            GROUP BY  ticket_type";
    public static final String FIND_BEER_ALL = "Select beer_type from beer";
    public static final String INSERT_PARTICIPANT_BEER = "INSERT INTO participant_beer (id_user, id_beer" + ") " +
            "VALUES (?, (Select id_beer from beer where beer_type = ?));";
//@FIXME форматирование

    private Query() {
    }
}
