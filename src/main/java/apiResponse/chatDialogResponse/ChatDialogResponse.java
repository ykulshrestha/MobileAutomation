package apiResponse.chatDialogResponse;

import lombok.Getter;

import java.util.List;

@Getter
public class ChatDialogResponse {
        public int total_entries;
        public int skip;
        public int limit;
        public List<Item> items;

}
