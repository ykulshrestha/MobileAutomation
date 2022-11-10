package apiResponse.messageResponse;

import lombok.Getter;

import java.util.List;

@Getter
public class Item {
    private String _id;
    private boolean all_read;
    private List<Attachment> attachments;
    private String chat_dialog_id;
    private String created_at;
    private int date_sent;
    private List<Integer> delivered_ids;
    private String message;
    private List<Integer> read_ids;
    private int recipient_id;
    private int sender_id;
    private String updated_at;
    private int read;
}
