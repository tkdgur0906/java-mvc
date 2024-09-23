package com.interface21.webmvc.servlet.mvc.tobe;

import com.interface21.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HandlerExecutionsTest {

    @DisplayName("요청에 해당하는 HandlerExecution을 정상적으로 반환")
    @Test
    void findHandler() {
        //given
        HandlerExecutions handlerExecutions = new HandlerExecutions();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HandlerExecution handlerExecution = mock(HandlerExecution.class);
        when(request.getRequestURI()).thenReturn("/get");
        when(request.getMethod()).thenReturn("GET");

        //when
        handlerExecutions.add(new HandlerKey("/get", RequestMethod.GET), handlerExecution);

        //then
        assertThat(handlerExecutions.findHandler(request)).isEqualTo(Optional.of(handlerExecution));
    }

    @DisplayName("요청에 해당하는 HandlerExecution이 없을 때 빈 Optional 반환")
    @Test
    void getHandle_notExist() {
        //given & when
        HandlerExecutions handlerExecutions = new HandlerExecutions();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/get");
        when(request.getMethod()).thenReturn("GET");

        //then
        assertThat(handlerExecutions.findHandler(request)).isEqualTo(Optional.empty());
    }
}
