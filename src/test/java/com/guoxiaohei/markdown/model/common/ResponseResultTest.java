package com.guoxiaohei.markdown.model.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseResultTest {

    private ResponseResult<String> successResult;
    private ResponseResult<String> errorResult;

    @BeforeEach
    void setUp() {
        successResult = new ResponseResult<>(200, "操作成功", "test data");
        errorResult = new ResponseResult<>(500, "操作失败");
    }

    @Test
    void testConstructors() {
        ResponseResult<String> empty = new ResponseResult<>();
        assertNotNull(empty);

        ResponseResult<String> codeMessage = new ResponseResult<>(404, "Not Found");
        assertEquals(404, codeMessage.getCode());
        assertEquals("Not Found", codeMessage.getMessage());
        assertNull(codeMessage.getData());
    }

    @Test
    void testGettersAndSetters() {
        ResponseResult<String> result = new ResponseResult<>();
        result.setCode(201);
        result.setMessage("Created");
        result.setData("new data");

        assertEquals(201, result.getCode());
        assertEquals("Created", result.getMessage());
        assertEquals("new data", result.getData());
    }

    @Test
    void testSuccessResult() {
        assertEquals(200, successResult.getCode());
        assertEquals("操作成功", successResult.getMessage());
        assertEquals("test data", successResult.getData());
    }

    @Test
    void testErrorResult() {
        assertEquals(500, errorResult.getCode());
        assertTrue(successResult.toString().contains("ResponseResult"));
        assertEquals("操作失败", errorResult.getMessage());
        assertNull(errorResult.getData());
    }
}