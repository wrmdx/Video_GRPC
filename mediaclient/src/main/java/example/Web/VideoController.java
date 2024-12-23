package example.Web;

import example.DTO.VideoDTO;
import example.Service.VideoServiceClient;
import example.Web.Request.VideoRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.Creator;
import org.example.UploadVideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoServiceClient videoService;

    @Autowired
    public VideoController(VideoServiceClient videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<VideoDTO> uploadVideo(@RequestBody VideoRequest request) {
        System.out.println("Received upload request from :"+ request);
        Creator creator = Creator.newBuilder()
                .setId(request.getCreator().getId())
                .setName(request.getCreator().getName())
                .setEmail(request.getCreator().getEmail())
                .build();

        UploadVideoRequest grpcRequest = UploadVideoRequest.newBuilder()
                .setCreator(creator)
                .setTitle(request.getTitle())
                .setDescription(request.getDescription())
                .setUrl(request.getUrl())
                .setDurationSeconds(request.getDurationSeconds())
                .build();

        VideoDTO videoDTO = videoService.uploadVideo(grpcRequest);
        return ResponseEntity.ok(videoDTO);
    }
}

