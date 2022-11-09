package apiResponse.messageResponse;

import lombok.Getter;

import java.util.List;

@Getter
public class MessageResponse {
    private int skip;
    private int limit;
    private List<Item> items;
}
