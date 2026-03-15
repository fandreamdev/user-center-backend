package com.fandream.usercenter.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * PageResult
 *
 * @author fandream
 * @date 2026-03-14 10:30:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private List<T> data;

    private boolean success;

    private Integer total;



    public static<T> PageResult<T> emptyPage() {
        return new PageResult<>(new ArrayList<>(), true, 0);
    }

    public static <T> PageResult<T> successWithDate(List<T> data) {
        return new PageResult<>(data, true, data.size());
    }

    public static <T> PageResult<T> error() {
        return new PageResult<>(null, false, 0);
    }
}
