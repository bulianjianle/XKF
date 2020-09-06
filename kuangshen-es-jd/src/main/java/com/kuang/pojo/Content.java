package com.kuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Content
 *
 * @author danggui
 * @date 2020/9/6 18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {

    private String title;
    private String img;
    private String price;

}
