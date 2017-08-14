package com.msk.common.data.jpa;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * javadoc here.
 *
 * @author liu_bing
 */
public class CustomPageRequest implements Pageable, Serializable {
    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    private static final long serialVersionUID = 8280485938848398236L;

    private int page;
    private int size;
    private Sort sort;

    /**
     * Creates a new {@link CustomPageRequest}. Pages are zero indexed, thus providing 0 for {@code page} will return the first
     * page.
     *
     * @param size
     * @param page
     */
    public CustomPageRequest(int page, int size) {

        this(page, size, null);
    }

    /**
     * Creates a new {@link CustomPageRequest} with sort parameters applied.
     *
     * @param page
     * @param size
     * @param direction
     * @param properties
     */
    public CustomPageRequest(int page, int size, Sort.Direction direction, String... properties) {

        this(page, size, new Sort(direction, properties));
    }

    /**
     * Creates a new {@link CustomPageRequest} with sort parameters applied.
     *
     * @param page
     * @param size
     * @param sort
     */
    public CustomPageRequest(int page, int size, Sort sort) {

        if (0 > page) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (0 >= size) {
            throw new IllegalArgumentException("Page size must not be less than or equal to zero!");
        }

        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    /*
             * (non-Javadoc)
             *
             * @see org.springframework.data.domain.Pageable#getPageSize()
             */
    public int getPageSize() {

        return size;
    }

    /*
             * (non-Javadoc)
             *
             * @see org.springframework.data.domain.Pageable#getPageNumber()
             */
    public int getPageNumber() {

        return page;
    }

    /*
             * (non-Javadoc)
             *
             * @see org.springframework.data.domain.Pageable#getFirstItem()
             */
    public int getOffset() {

        return page * size;
    }

    /*
             * (non-Javadoc)
             *
             * @see org.springframework.data.domain.Pageable#getSort()
             */
    public Sort getSort() {

        return sort;
    }

    /*
             * (non-Javadoc)
             *
             * @see java.lang.Object#equals(java.lang.Object)
             */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CustomPageRequest)) {
            return false;
        }

        CustomPageRequest that = (CustomPageRequest) obj;

        boolean pageEqual = this.page == that.page;
        boolean sizeEqual = this.size == that.size;

        boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);

        return pageEqual && sizeEqual && sortEqual;
    }

    /*
             * (non-Javadoc)
             *
             * @see java.lang.Object#hashCode()
             */
    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + page;
        result = 31 * result + size;
        result = 31 * result + (null == sort ? 0 : sort.hashCode());

        return result;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
