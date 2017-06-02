package com.commercetools.sync.commons.helpers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public abstract class BaseSyncStatistics {

    protected static final String REPORT_MESSAGE_TEMPLATE = "Summary: %d %s were processed in total "
        + "(%d created, %d updated, %d were up to date and %d failed to sync).";

    private int updated;
    private int created;
    private int failed;
    private int upToDate;
    private int processed;
    private long processingTimeInMillis;

    protected BaseSyncStatistics(final long processingTimeInMillis, final int created, final int updated,
                                 final int upToDate, final int failed) {
        this.processingTimeInMillis = processingTimeInMillis;
        this.processed = created + updated + upToDate + failed;
        this.created = created;
        this.updated = updated;
        this.upToDate = upToDate;
        this.failed = failed;
    }

    /**
     * Gets the total number of resources updated.
     *
     * @return total number of resources updated.
     */
    public int getUpdated() {
        return updated;
    }

    /**
     * Gets the total number of resources created.
     *
     * @return total number of resources created.
     */
    public int getCreated() {
        return created;
    }

    /**
     * Gets the total number of resources processed/synced.
     *
     * @return total number of resources processed/synced.
     */
    public int getProcessed() {
        return processed;
    }

    /**
     * Gets the total number of resources that failed to sync.
     *
     * @return total number of resources that failed to sync.
     */
    public int getFailed() {
        return failed;
    }

    /**
     * Gets the total number of resources that were already up to date.
     *
     * @return total number of resources that were already up to date.
     */
    public int getUpToDate() {
        return upToDate;
    }

    /**
     * Gets the number of milliseconds it took to process.
     *
     * @return number of milliseconds taken to process.
     */
    public long getProcessingTimeInMillis() {
        return processingTimeInMillis;
    }

    /**
     * Returns processing time formatted according to {@code format} string. Given {@code format} can contain following
     * tokens:
     * <table>
     *     <tr><td><strong>character</strong></td><td><strong>duration element</strong></td></tr>
     *     <tr><td>y</td><td>years</td></tr>
     *     <tr><td>M</td><td>months</td></tr>
     *     <tr><td>d</td><td>days</td></tr>
     *     <tr><td>H</td><td>hours</td></tr>
     *     <tr><td>m</td><td>minutes</td></tr>
     *     <tr><td>s</td><td>seconds</td></tr>
     *     <tr><td>S</td><td>milliseconds</td></tr>
     *     <tr><td>'text'</td><td>arbitrary text content</td></tr>
     * </table>
     *
     * @param format date formatter
     * @return formatted time taken to process
     */
    public String getFormattedProcessingTime(@Nonnull final String format) {
        return DurationFormatUtils.formatDuration(processingTimeInMillis, format);
    }

    /**
     * Gets a summary message of the statistics report.
     *
     * @return a summary message of the statistics report.
     */
    @JsonIgnore
    public abstract String getReportMessage();
}
