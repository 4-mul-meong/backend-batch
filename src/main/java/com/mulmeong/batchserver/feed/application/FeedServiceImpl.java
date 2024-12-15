package com.mulmeong.batchserver.feed.application;

import com.mulmeong.batchserver.feed.domain.document.FeedRead;
import com.mulmeong.batchserver.feed.infrastructure.repository.FeedReadRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.DislikesRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.LikesRepository;
import com.mulmeong.event.utility.consume.DislikeRenewCreateEvent;
import com.mulmeong.event.utility.consume.LikeRenewCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

    private final FeedReadRepository feedReadRepository;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;

    @Override
    public void likeCountRenew(LikeRenewCreateEvent message) {

        FeedRead feedReadUpdate = feedReadRepository.findByFeedUuid(message.getKindUuid()).orElseThrow();
        Long count = likesRepository.countByKindAndKindUuidAndStatus(message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        feedReadRepository.save(message.toFeedReadEntity(feedReadUpdate, count));

    }

    @Override
    public void dislikeCountRenew(DislikeRenewCreateEvent message) {
        FeedRead feedReadUpdate = feedReadRepository.findByFeedUuid(message.getKindUuid()).orElseThrow();
        Long count = dislikesRepository.countByKindAndKindUuidAndStatus(message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        feedReadRepository.save(message.toFeedReadEntity(feedReadUpdate, count));
    }
}
