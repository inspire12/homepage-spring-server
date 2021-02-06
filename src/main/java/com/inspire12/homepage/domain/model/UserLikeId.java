package com.inspire12.homepage.domain.model;

import com.inspire12.homepage.common.LikeType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLikeId implements Serializable {
    private static final long serialVersionUID = -4144658576676492364L;
    Long targetId;
    Long userId;
    LikeType likeType;
}
