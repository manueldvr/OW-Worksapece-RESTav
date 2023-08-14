package com.openwebinars.rest.util.pagination;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Para cumplir con la especificacion RFC 5988, ver readme.
 */
@Component
public class PaginationLiksUtils {

    /**
     * Create link header method
     * @param page page
     * @param uriBuilder uri
     * @return String
     */
    public String createLinkHeader(Page<?> page, UriComponentsBuilder uriBuilder) {
        final StringBuilder linkHeader = new StringBuilder();
        linkHeader.append("");
        if (page.hasNext()) {
            String uri = constructUri(page.getNumber()+1, page.getSize(), uriBuilder);
            linkHeader.append(buildLinkHeader(uri, "next"));
        }

        if (page.hasPrevious()) {
            String uri = constructUri(page.getNumber()-1, page.getSize(), uriBuilder);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(buildLinkHeader(uri, "prev"));
        }

        if (!page.isFirst()) {
            String uri = constructUri(0, page.getSize(), uriBuilder);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(buildLinkHeader(uri, "first"));
        }

        if (!page.isLast()) {
            String uri = constructUri(page.getTotalPages()-1, page.getSize(), uriBuilder);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(buildLinkHeader(uri, "last"));
        }

        return linkHeader.toString();
    }

    private String buildLinkHeader(String uri, String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }

    private void appendCommaIfNecessary(StringBuilder linkHeader) {
        if (linkHeader.length() > 0) {
            linkHeader.append(", ");
        }
    }

    private String constructUri(int newPageNumber, int size, UriComponentsBuilder uriBuilder) {
        return uriBuilder.replaceQueryParam("page", newPageNumber).replaceQueryParam("size", size).build().encode().toUriString();
    }
}