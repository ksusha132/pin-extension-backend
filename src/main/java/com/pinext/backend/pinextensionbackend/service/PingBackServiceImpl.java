package com.pinext.backend.pinextensionbackend.service;

import com.paymentwall.java.Pingback;
import com.paymentwall.java.Product;
import com.paymentwall.java.ProductBuilder;
import com.paymentwall.java.Widget;
import com.paymentwall.java.WidgetBuilder;
import com.pinext.backend.pinextensionbackend.dto.PingBackDto;
import com.pinext.backend.pinextensionbackend.entity.Person;
import com.pinext.backend.pinextensionbackend.entity.Subscription;
import com.pinext.backend.pinextensionbackend.repository.PersonRepository;
import com.pinext.backend.pinextensionbackend.repository.SubscriptionRepository;
import com.pinext.backend.pinextensionbackend.request.PaymentUrlRequest;
import com.pinext.backend.pinextensionbackend.response.PaymentUrlResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pinext.backend.pinextensionbackend.consts.Constants.ALL;
import static com.pinext.backend.pinextensionbackend.consts.Constants.EMAIL;
import static com.pinext.backend.pinextensionbackend.consts.Constants.EVALUATION;
import static com.pinext.backend.pinextensionbackend.consts.Constants.PERIOD;
import static com.pinext.backend.pinextensionbackend.consts.Constants.PERIOD_TYPE_NAME;
import static com.pinext.backend.pinextensionbackend.consts.Constants.PS;
import static com.pinext.backend.pinextensionbackend.consts.Constants.USD;

@Slf4j
@Service
public class PingBackServiceImpl implements PingBackService {
    private final Set<Integer> deniedStatuses = Set.of(203, 202, 14, 13, 12, 2);
    private final Set<Integer> approvedStatuses = Set.of(201, 0);

    private final PersonRepository personRepository;
    private final SubscriptionRepository subscriptionRepository;

    public PingBackServiceImpl(PersonRepository personRepository,
                               SubscriptionRepository subscriptionRepository) {
        this.personRepository = personRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public PaymentUrlResponse generatePaymentUrl(PaymentUrlRequest request) {
        Widget widget = buildWidget(request);
        log.info("Widget created: {}", widget);
        PaymentUrlResponse paymentUrlResponse = new PaymentUrlResponse();
        paymentUrlResponse.setUrl(widget.getUrl());
        paymentUrlResponse.setDateTime(LocalDateTime.now());
        paymentUrlResponse.setEmail(request.getEmail());
        log.info("Payment url response: {}", paymentUrlResponse);
        return paymentUrlResponse;
    }

    private Widget buildWidget(PaymentUrlRequest request) {
        WidgetBuilder widgetBuilder = getWidgetBuilder(request);
        fillInWidgetBuilder(request, widgetBuilder);
        return widgetBuilder.build();
    }

    private void fillInWidgetBuilder(PaymentUrlRequest request, WidgetBuilder widgetBuilder) {
        widgetBuilder.setExtraParams(new LinkedHashMap<>() {
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

    @SneakyThrows
    @Override
    public String acceptPingBack(PingBackDto pingBack, HttpServletRequest request) {
        log.info("Request body: {} Pingback body {}", request, pingBack);
        Pingback pingback = new Pingback(request.getParameterMap(), request.getRemoteAddr());
        String extensionType = pingback.getProductId();
        String email = pingback.getUserId();
        log.info("User email {} and user extension came {}", email, extensionType);
        Integer pingBackType = pingback.getType();
        Person person = personRepository.findAllByEmail(email);
        log.info("Person to update subscription status");
        Subscription desiredSubs = getSuitableSubscription(extensionType, person);

        if (deniedStatuses.contains(pingBackType) && Objects.nonNull(desiredSubs)) {
            setActive(desiredSubs, false);
            return "OK";
        } else if (approvedStatuses.contains(pingBackType)) {
            if (Objects.isNull(desiredSubs)) {
                desiredSubs = new Subscription();
            }
            fillInData(desiredSubs, extensionType, person);
            return "OK";
        } else {
            log.info("Unprocessed status came. Do nothing pingBack type {}", pingBackType);
        }
        return "OK";
    }

    private void fillInData(Subscription subscription,
                            String extensionType,
                            Person person) {
        subscription.setFrom(LocalDate.now());
        subscription.setTo(LocalDate.now().plusMonths(1));
        subscription.setType(extensionType);
        subscription.setPerson(person);
        setActive(subscription, true);
    }

    private void setActive(Subscription subscription,
                           Boolean active) {
        subscription.setActive(active);
        subscriptionRepository.saveAndFlush(subscription);
    }

    private Subscription getSuitableSubscription(String extensionType, Person person) {
        List<Subscription> subscriptions = Optional.of(person)
                .map(Person::getSubscriptions)
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("User's subscriptions: {}", subscriptions);

        Subscription desiredSubs = subscriptions.stream()
                .filter(subs -> subs.getType().equalsIgnoreCase(extensionType))
                .findFirst()
                .orElse(null);
        log.info("Desired subscription : {}", desiredSubs);
        return desiredSubs;
    }
}
