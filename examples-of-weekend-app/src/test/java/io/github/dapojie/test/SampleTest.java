package io.github.dapojie.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @description: some desc
 * @author: ch-hui
 * @email: 413659846@qq.com
 * @date: 2025/1/5 14:19
 */
@SuppressWarnings("all")
public class SampleTest {


    /**
     * KMP一种高效的字符串匹配算法，用于在主串中查找子串出现的位置。
     * 核心思想是通过预处理模式串，构建一个next数组，从而在匹配失败时避免不必要的回溯，提高匹配效率。
     */
    @Test
    public void testKMP() {
        String text = "我们中间有很多人把自己的国籍从中国改为美国，此次不再是中国人";
        String pattern = "中国人";
        int sIndex = kmpSearch(text, pattern);
        System.out.println(sIndex);
        System.out.println(StringUtils.substring(text, sIndex));
    }


    private static int kmpSearch(String text, String pattern) {

        int m = text.length();
        int n = pattern.length();
        // 若模式串为空直接返回
        if (n == 0) {
            return 0;
        }
        // 获取next数组
        int next[] = getNext(pattern);
        // 模式串指针
        int j = 0;
        // 遍历主串
        for (int i = 0; i < m; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) { // 若不匹配，回退到上一个位置
                j = next[j - 1];
            }
            // 如果字符匹配j向后移动
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // 如果j等于模式串长度，说明匹配成功，返回主串中匹配的起始位置
            if (j == n) {
                return i - n + 1;
            }
        }
        // 未找到
        return -1;
    }


    private static int[] getNext(String pattern) {
        int n = pattern.length();
        int next[] = new int[n];
        next[0] = 0;

        int j = 0;
        // 从第二个字符串开始
        for (int i = 1; i < n; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) { // 如果不匹配，回退到上一个位置
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // 记录当前字符的next值
            next[i] = j;
        }
        return next;
    }
}
