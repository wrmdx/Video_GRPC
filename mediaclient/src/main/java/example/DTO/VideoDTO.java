package example.DTO;


import lombok.Builder;
import lombok.Data;

@Data
public class VideoDTO {
    private int id;
    private String title;
    private String description;
    private String url;
    private int duration;
    private CreatorDTO creator;
}

