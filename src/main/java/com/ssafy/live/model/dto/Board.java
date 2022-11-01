package com.ssafy.live.model.dto;

import lombok.Data;

@Data
public class Board {
	private int articleNo;
    private String userId;
    private String userName;
    private String subject;
    private String content;
    private int hit;
    private String registerTime;
    private String bullet;
}
