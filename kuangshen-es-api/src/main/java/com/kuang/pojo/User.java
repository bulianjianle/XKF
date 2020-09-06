package com.kuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * User
 *
 * @author danggui
 * @date 2020/8/23 22:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

    private String name;
    private int age;
}
