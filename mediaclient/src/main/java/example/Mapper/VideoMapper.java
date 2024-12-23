package example.Mapper;

import example.DTO.VideoDTO;
import org.example.Video;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    ModelMapper mapper = new ModelMapper() ;

    public VideoDTO fromVideoProtoTovideoDto(Video video) {

        return mapper.map(video, VideoDTO.class);
    }
}
