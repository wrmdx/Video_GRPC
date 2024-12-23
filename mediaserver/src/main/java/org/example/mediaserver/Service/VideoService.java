package org.example.mediaserver.Service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.UploadVideoRequest;
import org.example.Video;
import org.example.VideoServiceGrpc;
import org.example.mediaserver.Entity.User;
import org.example.mediaserver.Repository.UserRepository;
import org.example.mediaserver.Repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import io.grpc.Status;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {
        try {
            System.out.println("Server : Received request from the client"+ request.getCreator().getId());
            System.out.println("Server : Looking for user with ID:"+ request.getCreator().getId());

            User user = userRepository.findById(request.getCreator().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            org.example.mediaserver.Entity.Video videoEntity = org.example.mediaserver.Entity.Video.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .url(request.getUrl())
                    .durationSeconds(request.getDurationSeconds())
                    .user(user)
                    .build();

            org.example.mediaserver.Entity.Video savedEntity = videoRepository.save(videoEntity);

            Video response = Video.newBuilder()
                    .setId(savedEntity.getId())
                    .setTitle(savedEntity.getTitle())
                    .setDescription(savedEntity.getDescription())
                    .setUrl(savedEntity.getUrl())
                    .setDurationSeconds(savedEntity.getDurationSeconds())
                    .setCreator(org.example.Creator.newBuilder()
                            .setId(savedEntity.getUser().getId())
                            .setName(savedEntity.getUser().getName())
                            .setEmail(savedEntity.getUser().getEmail())
                            .build())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("Video uploaded successfully: {}", savedEntity.getId());
        } catch (Exception e) {
            log.error("Error in uploadVideo: {}", e.getMessage(), e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}