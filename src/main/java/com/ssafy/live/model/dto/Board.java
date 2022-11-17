package com.ssafy.live.model.dto;

import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
public class Board {
	private int articleNo;
    private String userId;
    private String userName;
    private String subject;
    private byte[] content;
    private int hit;
    private String registerTime;
    private String bullet;
    private List<FileInfo> fileInfos;
}
