package apiResponse.chatDialogResponse;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
@Getter
public class Item {
    private String _id;
    private Date created_at;
    private Data data;
    private String last_message;
    private int last_message_date_sent;
    private String last_message_id;
    private int last_message_user_id;
    private String name;
    private ArrayList<Integer> occupants_ids;
    private Object photo;
    private int type;
    private Date updated_at;
    private int user_id;
    private String xmpp_room_jid;
    private int unread_messages_count;
}
