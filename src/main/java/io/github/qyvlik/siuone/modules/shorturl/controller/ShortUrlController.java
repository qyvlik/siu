package io.github.qyvlik.siuone.modules.shorturl.controller;

import io.github.qyvlik.siuone.common.utils.Base62;
import io.github.qyvlik.siuone.common.utils.ServletUtils;
import io.github.qyvlik.siuone.common.wapper.BaseService;
import io.github.qyvlik.siuone.common.wapper.ResponseObject;
import io.github.qyvlik.siuone.modules.shorturl.entity.ShortUrlView;
import io.github.qyvlik.siuone.modules.shorturl.service.ShortUrlService;
import io.github.qyvlik.siuone.modules.statistics.ShortUrlViewProducer;
import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShortUrlController extends BaseService {

    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private ShortUrlViewProducer shortUrlViewProducer;

    @ResponseBody
    @RequestMapping(value = "api/v1/short-url/create",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseObject<String> createShortUrl(String originUrl) {
        if (StringUtils.isBlank(originUrl)) {
            return new ResponseObject<>(20400, "originUrl must not be blank");
        }

        try {
            String shortUrl = shortUrlService.createShortUrl(originUrl);
            return new ResponseObject<>(shortUrl);
        } catch (Exception e) {
            return new ResponseObject<>(20500, e.getMessage());
        }
    }

    @RequestMapping(value = "{shortUrl}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity shortUrl(@PathVariable("shortUrl") String shortUrl, HttpServletRequest request) {
        UrlOrigin originUrl = shortUrlService.getOriginUrl(shortUrl);

        if (originUrl == null || StringUtils.isBlank(originUrl.getOriginUrl())) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.LOCATION, originUrl.getOriginUrl());

        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        String referrer = request.getHeader(HttpHeaders.REFERER);

        ShortUrlView view = new ShortUrlView();
        view.setShortUrlId(Base62.toBase10(shortUrl));
        view.setOriginUrlId(originUrl.getId());
        view.setIpAddress(ServletUtils.getIpFromRequest(request));
        view.setUserAgent(userAgent);
        view.setReferrerUrl(referrer);
        shortUrlViewProducer.send(view);

        return new ResponseEntity(httpHeaders, HttpStatus.FOUND);
    }
}
