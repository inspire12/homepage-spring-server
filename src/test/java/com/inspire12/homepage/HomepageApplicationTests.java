package com.inspire12.homepage;

import com.inspire12.homepage.common.LikeType;
import com.inspire12.homepage.domain.model.UserLikeId;
import com.inspire12.homepage.domain.repository.UserLikeRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;


@SpringBootTest
@Nested
@DisplayName("should run all assertions even if on fails")
public class HomepageApplicationTests {

	@MockBean
	UserLikeRepository userLikeRepository;

	@org.junit.Test
	public void contextLoads() {
	}

	@Test
	public void saveTest(){
		UserLikeId userLikeId = new UserLikeId(1L, 1L, LikeType.COMMENT);

		given(userLikeRepository.findById(userLikeId))
				.willReturn(Optional.empty());


	}
}

