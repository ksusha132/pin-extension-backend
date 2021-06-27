package com.pinext.backend.pinextensionbackend.service;

import com.paymentwall.java.Pingback;
import com.paymentwall.java.Product;
import com.paymentwall.java.ProductBuilder;
import com.paymentwall.java.Widget;
import com.paymentwall.java.WidgetBuilder;
import com.pinext.backend.pinextensionbackend.dto.PingBackDto;
import com.pinext.backend.pinextensionbackend.request.PaymentUrlRequest;
import com.pinext.backend.pinextensionbackend.response.PaymentUrlResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import static com.pinext.backend.pinextensionbackend.consts.Constants.ALL;
import static com.pinext.backend.pinextensionbackend.consts.Constants.EMAIL;
import static com.pinext.backend.pinextensionbackend.consts.Constants.EVALUATION;
import static com.pinext.backend.pinextensionbackend.consts.Constants.HISTORY_REGISTRATION_DATE;
import static com.pinext.backend.pinextensionbackend.consts.Constants.PERIOD;
import static com.pinext.backend.pinextensionbackend.consts.Constants.PERIOD_TYPE_NAME;
import static com.pinext.backend.pinextensionbackend.consts.Constants.PS;
import static com.pinext.backend.pinextensionbackend.consts.Constants.REGISTRATION_DATE;
import static com.pinext.backend.pinextensionbackend.consts.Constants.USD;
import static com.pinext.backend.pinextensionbackend.consts.Constants.USER_ID;

@Slf4j
@Service
public class PingBackServiceImpl implements PingBackService {

    @Override
    public PaymentUrlResponse generatePaymentUrl(PaymentUrlRequest request) {
        Widget widget = buildWidget(request);
        log.info("Widget created: {}", widget);
        PaymentUrlResponse paymentUrlResponse = new PaymentUrlResponse();
        paymentUrlResponse.setUrl(widget.getUrl());
        paymentUrlResponse.setDateTime(LocalDateTime.now());
        return paymentUrlResponse;
    }

    private Widget buildWidget(PaymentUrlRequest request) {
        WidgetBuilder widgetBuilder = getWidgetBuilder(request);
        fillInWidgetBuilder(request, widgetBuilder);
        return widgetBuilder.build();
    }

    private void fillInWidgetBuilder(PaymentUrlRequest request, WidgetBuilder widgetBuilder) {
        widgetBuilder.setExtraParams(new LinkedHashMap<String, String>() {
            {
                put(EMAIL, request.getEmail());
                put(PS, ALL);
                put(EVALUATION, PERIOD); // after success check - delete
            }
        });
    }

    private WidgetBuilder getWidgetBuilder(PaymentUrlRequest request) {
        WidgetBuilder widgetBuilder = new WidgetBuilder(request.getEmail(), request.getWidgetCode());
        widgetBuilder.setProduct(
                new ProductBuilder(request.getProductName()) {
                    {
                        setAmount(request.getAmount());
                        setCurrencyCode(USD);
                        setName(request.getVersionDescription());
                        setProductType(Product.TYPE_SUBSCRIPTION);
                        setPeriodType(PERIOD_TYPE_NAME);
                        setPeriodLength(Integer.parseInt(PERIOD));
                        setRecurring(true);
                    }
                }.build());
        return widgetBuilder;
    }

    @Override
    public String acceptPingBack(PingBackDto pingBack, HttpServletRequest request) {
        Pingback pingback = new Pingback(request.getParameterMap(), request.getRemoteAddr());
        if (pingback.validate(true)) {
            String goods = pingback.getProductId();
            String userId = pingback.getUserId();
            if (pingback.isDeliverable()) {
                // deliver Product to user with userId
            } else if (pingback.isCancelable()) {
                // withdraw Product from user with userId
            }
            return "OK";
        } else {
            return pingback.getErrorSummary();
        }
    }
}
