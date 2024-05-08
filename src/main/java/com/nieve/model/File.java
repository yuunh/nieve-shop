package com.nieve.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class File {

    private int fileNo;
    private String originName;
    private String changeName;
    private String filePath;
    private LocalDateTime uploadDate;
    private String status;
}
