package com.hm.user.service.clients;

import com.hm.user.service.entities.Rating;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RATING-SERVICE")
public interface RatingClient {

    @GetMapping("/rating/user/{userId}")
    List<Rating> getRatingByUserId(@PathVariable("userId") String userId);
}

