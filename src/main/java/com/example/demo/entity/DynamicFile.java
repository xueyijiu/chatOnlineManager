package com.example.demo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zjc
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DynamicFile implements Serializable {

//    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 图片id
     */
    private String filePath;

    /**
     * 动态id
     */
    private Long dynamicId;

    /**
     * 图片名称
     */
    private String imageName;


}
