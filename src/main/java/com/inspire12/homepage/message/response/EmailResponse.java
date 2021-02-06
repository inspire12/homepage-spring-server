package com.inspire12.homepage.message.response;


import lombok.Data;
import lombok.Setter;

@Data(staticConstructor = "of")
@Setter
public class EmailResponse {
    String valid;
}
