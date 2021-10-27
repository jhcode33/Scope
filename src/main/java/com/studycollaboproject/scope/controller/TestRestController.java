package com.studycollaboproject.scope.controller;

import com.studycollaboproject.scope.dto.ResponseDto;
import com.studycollaboproject.scope.dto.TestRequestDto;
import com.studycollaboproject.scope.dto.TestResultDto;
import com.studycollaboproject.scope.exception.ErrorCode;
import com.studycollaboproject.scope.exception.RestApiException;
import com.studycollaboproject.scope.security.UserDetailsImpl;
import com.studycollaboproject.scope.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Test Controller", description = "성향 테스트")
public class TestRestController {
    private final TestService testService;

    @Operation(summary = "성향 테스트 업데이트")
    @PostMapping("/api/test")
    public ResponseDto updatePropensity(@RequestBody TestRequestDto requestDto,
                                        @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("POST, [{}], /api/test, userPropensity={}, memberPropensity={}", MDC.get("UUID"), requestDto.getUserPropensityType().toString(), requestDto.getMemberPropensityType().toString());

        if (userDetails == null) {  //로그인 정보 확인
            throw new RestApiException(ErrorCode.NO_AUTHENTICATION_ERROR);
        }

        TestResultDto resultDto = testService.updatePropensityType(userDetails.getNickname(), requestDto.getUserPropensityType(), requestDto.getMemberPropensityType());
        return new ResponseDto("200", "", resultDto);
    }

    @Operation(summary = "성향 테스트 조회")
    @GetMapping("/api/test")
    public ResponseDto getPropensity(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("GET, [{}], /api/test", MDC.get("UUID"));

        if (userDetails == null) {  //로그인 정보 확인
            throw new RestApiException(ErrorCode.NO_AUTHENTICATION_ERROR);
        }
        TestResultDto resultDto = testService.getPropensityType(userDetails.getNickname());

        return new ResponseDto("200", "", resultDto);
    }
}