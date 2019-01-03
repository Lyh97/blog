package com.web.Blog.domain.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * The type Es blog.
 */
@Document(indexName = "blog", type = "blog")
public class EsBlog implements Serializable{

    @Id //主键
    private String id;
    private String title;
    private String auth;
    private String summary;
    private String content;
    private String tag;

    /**
     * Instantiates a new Es blog.
     */
    public EsBlog(){

    }

    /**
     * Instantiates a new Es blog.
     *
     * @param title   the title
     * @param auth    the auth
     * @param summary the summary
     * @param content the content
     */
    public EsBlog(String title, String auth, String summary, String content, String tag) {
        this.id = id;
        this.title = title;
        this.auth = auth;
        this.summary = summary;
        this.content = content;
        this.tag = tag;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets auth.
     *
     * @return the auth
     */
    public String getAuth() {
        return auth;
    }

    /**
     * Sets auth.
     *
     * @param auth the auth
     */
    public void setAuth(String auth) {
        this.auth = auth;
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return String.format(
                "EsBlog[id='%s', auth='%s', title='%s', summary='%s', content='%s', tag='%s']",id, auth, title, summary, content, tag
        );
    }
}
