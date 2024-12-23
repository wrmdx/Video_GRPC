package example.Web.Request;

import lombok.Data;

@Data
public class VideoRequest {
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private CreatorRequest creator;
}