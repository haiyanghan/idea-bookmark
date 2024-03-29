package io.github.hhy.bookmark.manager;

import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.BuildNumber;
import io.github.hhy.bookmark.element.Element;

import java.util.Collections;
import java.util.List;

public interface MyBookmarkManager {

    static MyBookmarkManager getBookmarkManager() {
        BuildNumber build = ApplicationInfo.getInstance().getBuild();
        if (build.getBaselineVersion() >= 231) {
            return new HighVersionBookManager();
        }
        return new LowVersionBookManager();
    }

    /**
     * get all
     *
     * @return
     */
    public List<Element> getAllBookmarks(Project project);

    /**
     * Add
     *
     * @param project
     * @param elements
     */
    public void addBookmarks(Project project, List<Element> elements);

    /**
     * @param fileDescription
     * @param lineNumber
     */
    Element getBookmark(Project project, String fileDescription, int lineNumber);

    /**
     * 删除无效的书签
     *
     * @param project
     */
    default List<Element> removeInvalid(Project project) {
        return Collections.emptyList();
    }
}
