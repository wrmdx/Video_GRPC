package example.Service;


import example.DTO.VideoDTO;
import example.Mapper.VideoMapper;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.UploadVideoRequest;
import org.example.Video;
import org.example.VideoServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VideoServiceClient {
    @GrpcClient("mediaserver")
    private VideoServiceGrpc.VideoServiceBlockingStub stub;

    @Autowired
    private VideoMapper mapper;

    public VideoDTO uploadVideo(UploadVideoRequest videoRequest) {
        try {
            Video video = stub.uploadVideo(videoRequest);
            return mapper.fromVideoProtoTovideoDto(video);
        } catch (StatusRuntimeException e) {
            log.error("Error uploading video: ", e);
            throw new RuntimeException("Failed to upload video: " + e.getMessage());
        }
    }
}