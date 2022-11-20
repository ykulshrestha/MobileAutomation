package apiResponse.getBulkdetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatPropertyDetails {
    private String title;
    private String formatted_price;
    private String name;
    private String formatted_min_price;
    private String formatted_max_price;
    private String status;
    private String address;
    private String apartment_type;
    private String price;

    public void setAddress(String address){
        if (address == null)
            this.address = null;
        else
        this.address = address.substring(1, address.length()-1);
    }
}
