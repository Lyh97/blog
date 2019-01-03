package com.web.Blog.Controller;

import com.alibaba.fastjson.JSON;
import com.web.Blog.domain.es.EsBlog;
import com.web.Blog.repository.es.EsBlogRespository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * The type Blog controller.
 */
@RestController
public class BlogController {
    @Autowired
    private EsBlogRespository esBlogRepository;

    /**
     * List list.
     * <p>
     * Search Blog By title and summary and content, Paging by a specific number
     *
     * @param info      the info
     * @param pageIndex the page index
     * @param pageSize  the page size
     * @return the list
     */
    @RequestMapping(name = "/searchArticle", method = GET)
    public List<EsBlog> list(String info, int pageIndex, int pageSize) {

        Pageable pageable = new PageRequest(0, 20);

        Page<EsBlog> page = esBlogRepository.findDistinctEsBlogByTitleOrSummaryOrContent(info, info, info, pageable);

        for (EsBlog blog : page.getContent()) {
            System.out.println(blog.toString());
        }
        return page.getContent();
    }

    /**
     * Add json object.
     * <p>
     * Add a Article to Elasticsearch
     *
     * @param info the info
     * @return the json object
     */
    @RequestMapping(value = "/addArticle", method = POST)
    public JSONObject add( @RequestBody String info ) {
        JSONObject list=new JSONObject();

        EsBlog blog = new EsBlog();
        blog = JSON.parseObject(info, EsBlog.class);

        try {
            esBlogRepository.save(blog);
        }catch(Exception e){
            // // If add fail ,return custom status 400、message and Exeption messages
            list.put("code", 401);
            list.put("message",e.toString());
            list.put("data","");
            return list;
        }
        // Encapsulated as JSONObject data and returned to the front end
        list.put("code", 200);
        list.put("message","添加成功");
        list.put("data","");
        return list;
    }

    /**
     * Delete article by id json object.
     *
     * delete the Article By Id in the ElasticSearch
     *
     * @return the json object
     */
    @RequestMapping(value = "/deleteArticle", method = POST)
    public JSONObject deleteArticleById(@RequestBody String articleId) {
        JSONObject mes = new JSONObject();

        System.out.println("ArticleId: " + JSON.parseObject(articleId).get("articleId").toString());

        try {
            esBlogRepository.deleteById(JSON.parseObject(articleId).get("articleId").toString());
        } catch (Exception e) {
            mes.put("code", 401);
            mes.put("massage", e.getMessage());
            mes.put("data", "");
        }
        mes.put("code", 200);
        mes.put("massage", "delete success");
        mes.put("data", "");
        return mes;
    }

    /**
     * Delete article by id json object.
     *
     * update the Article By Id in the ElasticSearch
     *
     * @return the json object
     */
    @RequestMapping(value = "/updateArticle", method = POST)
    public JSONObject updateArticle(@RequestBody String articleInfo) {
        JSONObject mes = new JSONObject();

        EsBlog article = JSON.parseObject(articleInfo, EsBlog.class);
        try {
            esBlogRepository.save(article);
        } catch (Exception e) {
            mes.put("code", 401);
            mes.put("massage", e.getMessage());
            mes.put("data", "");
        }
        mes.put("code", 200);
        mes.put("massage", "update success");
        mes.put("data", "");
        return mes;
    }
}