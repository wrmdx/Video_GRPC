package example.Web.Request;

import lombok.Data;

@Data
public class CreatorRequest {
    private Long id;
    private String name;
    private String email;
}