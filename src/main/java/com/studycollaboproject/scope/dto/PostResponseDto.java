package com.studycollaboproject.scope.dto;

import com.studycollaboproject.scope.model.Post;
import com.studycollaboproject.scope.model.Team;
import com.studycollaboproject.scope.model.Tech;
import com.studycollaboproject.scope.model.TechStack;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Schema(description = "프로젝트 상세")
public class PostResponseDto {
    @Schema(description = "프로젝트 ID")
    private Long postId;
    @Schema(description = "프로젝트 제목")
    private String title;
    @Schema(description = "프로젝트 내용")
    private String contents;
    @Schema(description = "기술 스택")
    private List<String> techStack = new ArrayList<>();
    @Schema(description = "총 인원")
    private int totalMember;
    @Schema(description = "현재 인원")
    private int recruitmentMember;
    @Schema(description = "프로젝트 상태")
    private String projectStatus;
    @Schema(description = "생성일")
    private LocalDate startDate;
    @Schema(description = "수정일")
    private LocalDate endDate;
    @Schema(description = "북마크 체크여부")
    private boolean bookmarkChecked;
    @Schema(description = "프로젝트 프론트앤드 Repository URL")
    private String frontUrl;
    @Schema(description = "프로젝트 백앤드 Repository URL")
    private String backUrl;
    @Schema(description = "작성자 성향 정보")
    private String propensityType;
    @Schema(description = "작성자 닉네임")
    private String nickname;
    @Schema(description = "작성자 아이디")
    private Long userId;
    @Schema(description = "채팅 url")
    private String chatUrl;
    @Schema(description = "현재 멤버 정보")
    private List<Long> memberId = new ArrayList<>();
    @Schema(description = "작성자 / 유저 체크")
    private boolean writerEquals;

    public PostResponseDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.techStack = new ArrayList<>();
        List<TechStack> techStackList = post.getTechStackList();
        for (TechStack stack : techStackList) {
            this.techStack.add(stack.getTech().getTech());
        }
        this.totalMember = post.getTotalMember();
        this.recruitmentMember = post.getRecruitmentMember();
        this.endDate = post.getEndDate().toLocalDate();
        this.startDate = post.getStartDate().toLocalDate();
        this.projectStatus = post.getProjectStatus().getProjectStatus();
        this.frontUrl = post.getFrontUrl();
        this.backUrl = post.getBackUrl();
        this.propensityType = post.getUser().getUserPropensityType();
        this.nickname = post.getUser().getNickname();
        this.userId = post.getUser().getId();
        this.bookmarkChecked = false;
    }

    public PostResponseDto(Post post, boolean bookmarkChecked) {
        this.chatUrl = post.getChatUrl();
        this.postId = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.techStack = new ArrayList<>();
        List<TechStack> techStackList = post.getTechStackList();
        for (TechStack stack : techStackList) {
                this.techStack.add(stack.getTech().getTech());
        }
        this.totalMember = post.getTotalMember();
        this.recruitmentMember = post.getRecruitmentMember();
        this.bookmarkChecked = bookmarkChecked;
        this.endDate = post.getEndDate().toLocalDate();
        this.startDate = post.getStartDate().toLocalDate();
        this.projectStatus = post.getProjectStatus().getProjectStatus();
        this.frontUrl = post.getFrontUrl();
        this.backUrl = post.getBackUrl();
        this.propensityType = post.getUser().getUserPropensityType();
        this.nickname = post.getUser().getNickname();
        this.userId = post.getUser().getId();
    }

    public PostResponseDto(Post post, boolean bookmarkChecked, String snsId) {
        this.chatUrl = post.getChatUrl();
        this.postId = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.techStack = new ArrayList<>();
        List<TechStack> techStackList = post.getTechStackList();
        for (TechStack stack : techStackList) {
            this.techStack.add(stack.getTech().getTech());
        }
        this.totalMember = post.getTotalMember();
        this.recruitmentMember = post.getRecruitmentMember();
        this.bookmarkChecked = bookmarkChecked;
        this.endDate = post.getEndDate().toLocalDate();
        this.startDate = post.getStartDate().toLocalDate();
        this.projectStatus = post.getProjectStatus().getProjectStatus();
        this.frontUrl = post.getFrontUrl();
        this.backUrl = post.getBackUrl();
        this.propensityType = post.getUser().getUserPropensityType();
        this.nickname = post.getUser().getNickname();
        this.userId = post.getUser().getId();
        this.memberId = new ArrayList<>();
        Set<Team> memberIdList = post.getTeamList();
        for(Team member : memberIdList){
            this.memberId.add(member.getUser().getId());
        }
        this.writerEquals = post.getUser().getSnsId().equals(snsId);
    }
}
