package com.mybatisdemos.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String patternLock;

}
