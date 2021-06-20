package com.violetfreesia.carnation.support;

import com.violetfreesia.carnation.exception.NotAllowedNullException;
import com.violetfreesia.carnation.util.CarnationAssert;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author violetfreesia
 * @date 2021-06-18
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcludeUrlPatterns {

    @Getter
    private final Set<String> excludeUrlPatterns = new HashSet<>(2);

    public ExcludeUrlPatterns addExcludeUrlPatterns(String... excludeUrlPatterns) {
        CarnationAssert.notExistNull(Arrays.asList(excludeUrlPatterns),
                new NotAllowedNullException("路径列表中包含Null对象"));
        Collections.addAll(this.excludeUrlPatterns, excludeUrlPatterns);
        return this;
    }

    public static ExcludeUrlPatterns of(String... excludeUrlPatterns) {
        ExcludeUrlPatterns urlPatterns = new ExcludeUrlPatterns();
        return urlPatterns.addExcludeUrlPatterns(excludeUrlPatterns);
    }
}
