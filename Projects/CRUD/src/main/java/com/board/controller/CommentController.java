package com.board.controller;

import com.board.adapter.GsonLocalDateTimeAdapter;
import com.board.domain.CommentDTO;
import com.board.service.CommentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = {"/comments", "/comments/{idx}"}, method = {RequestMethod.POST, RequestMethod.PATCH})
    public JsonObject registerComment(@PathVariable(value = "idx", required = false) Long idx,
                                      @RequestBody final CommentDTO params) {
        JsonObject jsonObject = new JsonObject();

        try {
            boolean isRegistered = commentService.registerComment(params);
            jsonObject.addProperty("result", isRegistered);
        } catch (DataAccessException e) {
            jsonObject.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");
        } catch (Exception e) {
            jsonObject.addProperty("message", "시스템에 문제가 발생 하였습니다.");
        }

        return jsonObject;
    }

    @GetMapping(value = "/comments/{boardIdx}")
    public JsonObject getCommentList(@PathVariable("boardIdx") Long boardIdx,
                                     @ModelAttribute("params") CommentDTO params) {
        JsonObject jsonObject = new JsonObject();

        List<CommentDTO> commentList = commentService.getCommentList(params);
        if (!CollectionUtils.isEmpty(commentList)) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
            JsonArray jsonArray = gson.toJsonTree(commentList).getAsJsonArray();
            jsonObject.add("commentList", jsonArray);
        }

        return jsonObject;
    }

    @DeleteMapping(value = "/comments/{idx}")
    public JsonObject deleteComment(@PathVariable("idx") final Long idx) {

        JsonObject jsonObject = new JsonObject();

        try {
            boolean isDeleted = commentService.deleteComment(idx);
            jsonObject.addProperty("result", isDeleted);

        } catch (DataAccessException e) {
            jsonObject.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

        } catch (Exception e) {
            jsonObject.addProperty("message", "시스템에 문제가 발생하였습니다.");
        }

        return jsonObject;
    }
}
