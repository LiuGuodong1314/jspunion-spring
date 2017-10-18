package com.jspunion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "没有找到")
public class NotFoundExceptoin extends ServerException {
}
