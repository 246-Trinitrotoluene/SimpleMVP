package org.diordna.simplemvp.interfaces;

/**
 * 异步结果
 * @author DiorDNA
 */

public interface Result {
    void success(Object... args);
    void fail(String errmsg);
}
