package apiResponse.chatDialogResponse;

import lombok.Getter;

@Getter
public class Data {
    private String buyerId;
    private String buyerName;
    private String buyerEmail;
    private Object buyerImageLink;
    private String propertyId;
    private String propertyType;
    private String sellerId;
    private String sellerProfileUrl;
    private String sellerName;
    private String clientGroupId;
    private String chatInitiator;
    private boolean isChatDisabled;
    private boolean isBuyerArchived;
    private boolean isSellerArchived;
    private String class_name;
}
